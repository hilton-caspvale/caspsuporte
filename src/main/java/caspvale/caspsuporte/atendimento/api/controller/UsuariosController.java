package caspvale.caspsuporte.atendimento.api.controller;

import caspvale.caspsuporte.atendimento.api.assembler.UsuariosAberturaAssembler;
import caspvale.caspsuporte.atendimento.api.assembler.UsuariosAssembler;
import caspvale.caspsuporte.atendimento.api.model.UsuariosInputModel;
import caspvale.caspsuporte.atendimento.api.model.UsuariosModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.atendimento.domain.service.EntidadesService;
import caspvale.caspsuporte.atendimento.domain.service.UsuariosService;
import caspvale.caspsuporte.domain.exception.NegocioException;
import caspvale.caspsuporte.atendimento.common.Permissoes;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Hilton
 */
@RestController
@RequestMapping("/atendimento/usuarios")
public class UsuariosController {

    private final UsuariosAssembler usuariosAssembler;
    private final UsuariosAberturaAssembler usuariosAbetruraAssembler;
    private final UsuariosService usuariosService;
    //private final EntidadesService entidadesService;
    private final Permissoes permissoes;

    public UsuariosController(UsuariosAssembler usuariosAssembler, UsuariosAberturaAssembler usuariosAbetruraAssembler,
            UsuariosService usuariosService, /*EntidadesService entidadesService,*/ Permissoes permissoes) {
        this.usuariosAssembler = usuariosAssembler;
        this.usuariosAbetruraAssembler = usuariosAbetruraAssembler;
        this.usuariosService = usuariosService;
        //this.entidadesService = entidadesService;
        this.permissoes = permissoes;
    }

    @GetMapping("consulta-login")
    public ResponseEntity<?> consultaLogin(@RequestParam(value = "nlogin", required = true) String nlogin) {
        UsuariosModel usuariosModel = usuariosAssembler.toModel(usuariosService.buscarUsuarioPorLogin(nlogin));
        return ResponseEntity.ok(new UsuariosInputModel(usuariosModel.getIUsuario(), usuariosModel.getNlogin(), usuariosModel.getNomeUsuario()));
    }

    @PatchMapping("/{nLogin}/trocar-senha")
    public ResponseEntity<?> trocarSenha(@PathVariable @Valid String nLogin, @RequestBody String senha) {
        CaspUsuarios usuario = usuariosService.buscarUsuarioPorLogin(nLogin);
        usuariosService.trocarSenha(usuario, senha);
        return ResponseEntity.ok("Senha atualizada");
    }

    @GetMapping("/{iUsuario}")
    public ResponseEntity<?> usuario(@PathVariable @Valid Integer iUsuario) {
        permissoes.restritoAoCliente();
        return ResponseEntity.ok(usuariosAssembler.toModel(usuariosService.buscarOuFalhar(iUsuario)));
    }

    @GetMapping("/buscar-login/{login}")
    public ResponseEntity<?> buscarPorLogin(@PathVariable String login) {
        permissoes.restritoAoCliente();
        CaspUsuarios usuario = usuariosService.buscarUsuarioPorLogin(login);
        if (usuario == null) {
            throw new NegocioException("Nao foi possivel localizar o usuario!");
        }
        return ResponseEntity.ok(usuariosAssembler.toModel(usuariosService.buscarUsuarioPorLogin(login)));
    }
    
    @GetMapping
    public ResponseEntity<?> listarUsuariosFiltros(@RequestParam(value = "entidades", required = false, defaultValue = "0") int entidades,
            @RequestParam(value = "areas", required = false, defaultValue = "0") int areas,
            @RequestParam(value = "sistemas", required = false, defaultValue = "0") int sistemas,
            @RequestParam(value = "exibir", required = false, defaultValue = "false") String exibir) {
        permissoes.exclusivoAdministrador();
        return ResponseEntity.ok(usuariosAssembler.toCollectionModel(usuariosService.usuariosPorFilstros(entidades, areas, sistemas, exibir)));
    }
    
    @GetMapping("usuarios-abetura")
    public ResponseEntity<?> listarUsuariosAbertura(@RequestParam(value = "entidades", required = false, defaultValue = "0") int entidades,
            @RequestParam(value = "areas", required = false, defaultValue = "0") int areas,
            @RequestParam(value = "sistemas", required = false, defaultValue = "0") int sistemas,
            @RequestParam(value = "exibir", required = false, defaultValue = "false") String exibir,
            @RequestParam(value = "somenteClientes", required = false, defaultValue = "false") boolean somenteClientes) {
        permissoes.restritoAoCliente();
        return ResponseEntity.ok(usuariosAbetruraAssembler.toCollectionModel(usuariosService.usuariosAbertura(somenteClientes,entidades, areas, sistemas, exibir)));
    }

    @PostMapping
    public ResponseEntity<?> adicionarUsuario(@Valid @RequestBody UsuariosModel usuariosModel) {
        permissoes.exclusivoAdministrador();
        if (usuariosModel.getIUsuario() != null) {
            throw new NegocioException("O código do usuário não pode ser informado para novos cadastros!");
        }
        CaspUsuarios usuarioNovo = usuariosService.novoUsuario(usuariosAssembler.toEntity(usuariosModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosAssembler.toModel(usuarioNovo));
    }

    @PutMapping("/{iUsuario}")
    public ResponseEntity editarUsuario(@Valid @RequestBody UsuariosModel usuariosModel, @PathVariable Integer iUsuario) {        
        permissoes.restritoAoCliente();
        CaspUsuarios caspUsuariosAtual = usuariosService.buscarOuFalhar(iUsuario);
        if(!permissoes.login().equals(caspUsuariosAtual.getNlogin())){
            permissoes.exclusivoAdministrador();
        }        
        if (usuariosModel.getIUsuario() == null || caspUsuariosAtual.getIUsuario() != iUsuario) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspUsuarios usuarioParaEditar = usuariosAssembler.toEntity(usuariosModel);
        CaspUsuarios caspUsuariosEditado = usuariosService.editarUsuario(usuarioParaEditar, caspUsuariosAtual);        
        UsuariosModel clienteEditado = usuariosAssembler.toModel(caspUsuariosEditado);
        return ResponseEntity.ok(clienteEditado);
    }
    /*
    @GetMapping("/{iEntidade}")
    public ResponseEntity<?> entidade(@PathVariable @Valid Integer iEntidade) {        
        return ResponseEntity.ok(usuariosAssembler.toModel(usuariosService.buscarOuFalhar(iEntidade)));
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody UsuariosModel usuariosModel) {
        if(usuariosModel.getIUsuario()!= null){
            throw new NegocioException("O código do usuário não pode ser informado para novos cadastros!");
        }
        CaspUsuarios usuarioNovo = usuariosService.novo(usuariosAssembler.toEntity(usuariosModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosAssembler.toModel(usuarioNovo));
    }

    @PutMapping("/{iEntidade}")
    public ResponseEntity editar(@Valid @RequestBody UsuariosModel usuariosModel, @PathVariable Integer iEntidade) {
        CaspUsuarios caspUsuariosAtual = usuariosService.buscarOuFalhar(iEntidade);
        if (usuariosModel.getIUsuario()== null || caspUsuariosAtual.getIUsuario()!= iEntidade) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspUsuarios caspUsuariosEditado = usuariosAssembler.toEntity(usuariosModel);
        UsuariosModel usuarioEditado = usuariosAssembler.toModel(usuariosService.editar(caspUsuariosEditado));
        return ResponseEntity.ok(usuarioEditado);
    }

    @DeleteMapping("/{iEntidade}")
    public ResponseEntity deletar(@PathVariable Integer iEntidade) {
        usuariosService.deletar(iEntidade);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuariosAssembler.toModel(new CaspUsuarios()));
    }*/
}
