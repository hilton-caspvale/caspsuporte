package caspvale.caspsuporte.modulos.atendimento.api.controller;

import caspvale.caspsuporte.modulos.atendimento.api.assembler.SistemasAssembler;
import caspvale.caspsuporte.modulos.atendimento.api.model.SistemasModel;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspSistemas;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.modulos.atendimento.domain.service.SistemasService;
import caspvale.caspsuporte.modulos.atendimento.domain.service.UsuariosService;
import caspvale.caspsuporte.modulos.atendimento.exception.NegocioException;
import caspvale.caspsuporte.modulos.atendimento.common.Permissoes;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Hilton
 */
@RestController
@RequestMapping("/atendimento/sistemas")
public class SistemasController {

    private final SistemasAssembler sistemasAssembler;
    private final SistemasService sistemasService;
    private final UsuariosService usuariosService;
    private final Permissoes permissoes;

    public SistemasController(SistemasAssembler sistemasAssembler, SistemasService sistemasService,
            UsuariosService usuariosService, Permissoes permissoes) {
        this.sistemasAssembler = sistemasAssembler;
        this.sistemasService = sistemasService;
        this.usuariosService = usuariosService;
        this.permissoes = permissoes;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        permissoes.restritoAoCliente();
        return ResponseEntity.ok(sistemasAssembler.toCollectionModel(sistemasService.listar()));
    }

    @GetMapping("/meus-sistemas")
    public ResponseEntity<?> meusSistemas(
            @RequestParam(value = "situacao", required = false, defaultValue = "") String situacao) {
        CaspUsuarios usuario = usuariosService.buscarUsuarioPorLogin(permissoes.login());
        return ResponseEntity.ok(sistemasAssembler.toCollectionModel(sistemasService.sistemasDoUsuario(situacao, usuario)));
    }

    @GetMapping("/sistemas-do-usuario")
    public ResponseEntity<?> sistemasDoUsuario(
            @RequestParam(value = "login", required = true) String login,
            @RequestParam(value = "situacao", required = false, defaultValue = "") String situacao) {
        permissoes.restritoAoCliente();
        CaspUsuarios usuario = usuariosService.buscarUsuarioPorLogin(login);        
        return ResponseEntity.ok(sistemasAssembler.toCollectionModel(sistemasService.sistemasDoUsuario(situacao, usuario)));
    }

    @GetMapping("/{iSistema}")
    public ResponseEntity<SistemasModel> sistema(@PathVariable @Valid Integer iSistema) {
        permissoes.restritoAoCliente();
        return ResponseEntity.ok(sistemasAssembler.toModel(sistemasService.buscarOuFalhar(iSistema)));
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody SistemasModel sistemasModel) {
        permissoes.restritoAoCliente();
        if (sistemasModel.getISistema() != null) {
            throw new NegocioException("O código do sistema não pode ser informado para novos cadastros!");
        }
        CaspSistemas sistemaNovo = sistemasService.novo(sistemasAssembler.toEntity(sistemasModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(sistemasAssembler.toModel(sistemaNovo));
    }

    @PutMapping("/{iSistema}")
    public ResponseEntity editar(@Valid @RequestBody SistemasModel sistemasModel, @PathVariable Integer iSistema) {
        permissoes.restritoAoCliente();
        CaspSistemas caspSistemaAtual = sistemasService.buscarOuFalhar(iSistema);
        if (sistemasModel.getISistema() == null || caspSistemaAtual.getISistema() != iSistema) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspSistemas caspSistemaEditado = sistemasAssembler.toEntity(sistemasModel);
        SistemasModel sistemaEditado = sistemasAssembler.toModel(sistemasService.editar(caspSistemaEditado, caspSistemaAtual));
        return ResponseEntity.ok(sistemaEditado);
    }

    @DeleteMapping("/{iSistema}")
    public ResponseEntity deletar(@PathVariable Integer iSistema) {
        permissoes.restritoAoCliente();
        sistemasService.deletar(iSistema);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
