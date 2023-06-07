package caspvale.caspsuporte.atendimento.api.controller;

import caspvale.caspsuporte.atendimento.api.assembler.TiposUsuariosAssembler;
import caspvale.caspsuporte.atendimento.api.model.TiposUsuariosModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspTiposUsuarios;
import caspvale.caspsuporte.atendimento.domain.service.TiposUsuariosService;
import caspvale.caspsuporte.domain.exception.NegocioException;
import caspvale.caspsuporte.atendimento.common.Permissoes;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Hilton
 */
@RestController
@RequestMapping("/atendimento/tipos-usuarios")
public class TiposUsuariosController {
    private final TiposUsuariosAssembler tiposUsuariosAssembler;
    private final TiposUsuariosService tiposUsuariosService;
    private final Permissoes permissoes;

    public TiposUsuariosController(TiposUsuariosAssembler tiposUsuariosAssembler, TiposUsuariosService tiposUsuariosService, Permissoes permissoes) {
        this.tiposUsuariosAssembler = tiposUsuariosAssembler;
        this.tiposUsuariosService = tiposUsuariosService;
        this.permissoes = permissoes;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        permissoes.exclusivoAdministrador();
        return ResponseEntity.ok(tiposUsuariosAssembler.toCollectionModel(tiposUsuariosService.listar()));
    }
    
    @GetMapping("tipo-cliente")
    public ResponseEntity<?> tipoCliente() {
        permissoes.exclusivoAdministrador();
        return ResponseEntity.ok(tiposUsuariosAssembler.toModel(tiposUsuariosService.buscarTiposClientes()));
    }
    
    @GetMapping("nao-cliente")
    public ResponseEntity<?> naoClientes() {
        return ResponseEntity.ok(tiposUsuariosAssembler.toCollectionModel(tiposUsuariosService.buscarTiposNaoClientes()));
    }

    @GetMapping("/{iTipoUsuario}")
    public ResponseEntity<?> tiposUsuarios(@PathVariable @Valid Integer iTipoUsuario) {      
        permissoes.exclusivoAdministrador();
        return ResponseEntity.ok(tiposUsuariosAssembler.toModel(tiposUsuariosService.buscarOuFalhar(iTipoUsuario)));
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody TiposUsuariosModel tiposUsuariosModel) {
        permissoes.exclusivoAdministrador();
        if(tiposUsuariosModel.getITipoUsuario()!= null){
            throw new NegocioException("O código do Tipos do Usuário não pode ser informado para novos cadastros!");
        }
        CaspTiposUsuarios tiposUsuariosNovo = tiposUsuariosService.novo(tiposUsuariosAssembler.toEntity(tiposUsuariosModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(tiposUsuariosAssembler.toModel(tiposUsuariosNovo));
    }

    @PutMapping("/{iTipoUsuario}")
    public ResponseEntity editar(@Valid @RequestBody TiposUsuariosModel tiposUsuariosModel, @PathVariable Integer iTipoUsuario) {
        permissoes.exclusivoAdministrador();
        CaspTiposUsuarios caspTiposUsuariosAtual = tiposUsuariosService.buscarOuFalhar(iTipoUsuario);
        if (tiposUsuariosModel.getITipoUsuario()== null || caspTiposUsuariosAtual.getITipoUsuario()!= iTipoUsuario) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspTiposUsuarios caspTiposUsuariosEditado = tiposUsuariosAssembler.toEntity(tiposUsuariosModel);
        TiposUsuariosModel tiposUsuariosEditado = tiposUsuariosAssembler.toModel(tiposUsuariosService.editar(caspTiposUsuariosEditado));
        return ResponseEntity.ok(tiposUsuariosEditado);
    }

    @DeleteMapping("/{iTipoUsuario}")
    public ResponseEntity deletar(@PathVariable Integer iTipoUsuario) {
        permissoes.exclusivoAdministrador();
        tiposUsuariosService.deletar(iTipoUsuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tiposUsuariosAssembler.toModel(new CaspTiposUsuarios()));
    }
}
