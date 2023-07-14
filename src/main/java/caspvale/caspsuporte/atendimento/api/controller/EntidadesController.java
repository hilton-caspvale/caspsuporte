package caspvale.caspsuporte.atendimento.api.controller;

import caspvale.caspsuporte.atendimento.api.assembler.EntidadesAssembler;
import caspvale.caspsuporte.atendimento.api.model.EntidadesModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspEntidades;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.atendimento.domain.service.EntidadesService;
import caspvale.caspsuporte.atendimento.domain.service.UsuariosService;
import caspvale.caspsuporte.domain.exception.NegocioException;
import caspvale.caspsuporte.atendimento.common.Permissoes;
import java.util.List;
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
@RequestMapping("/atendimento/entidades")
public class EntidadesController {

    private final EntidadesAssembler entidadesAssembler;
    private final EntidadesService entidadesService;
    private final UsuariosService usuariosService;
    private final Permissoes permissoes;

    public EntidadesController(EntidadesAssembler entidadesAssembler, EntidadesService entidadesService,
            UsuariosService usuariosService, Permissoes permissoes) {
        this.entidadesAssembler = entidadesAssembler;
        this.entidadesService = entidadesService;
        this.usuariosService = usuariosService;
        this.permissoes = permissoes;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        permissoes.restritoAoCliente();
        return ResponseEntity.ok(entidadesAssembler.toCollectionModel(entidadesService.listar()));
    }

    @GetMapping("/minhas-entidades")
    public ResponseEntity<?> minhasEntidades(
            @RequestParam(value = "situacao", required = false, defaultValue = "") String situacao) {
        CaspUsuarios usuario = usuariosService.buscarUsuarioPorLogin(permissoes.login());
        List<CaspEntidades> entidades = entidadesService.entidadesDoUsuario(situacao, usuario);
        return ResponseEntity.ok(entidadesAssembler.toCollectionModel(entidades));
    }

    @GetMapping("/entidades-do-usuario")
    public ResponseEntity<?> entidadesDoUsuario(
            @RequestParam(value = "situacao", required = false, defaultValue = "") String situacao,
            @RequestParam(value = "login", required = true) String login) {
        permissoes.restritoAoCliente();
        CaspUsuarios usuario = usuariosService.buscarUsuarioPorLogin(login);
        return ResponseEntity.ok(entidadesAssembler.toCollectionModel(entidadesService.entidadesDoUsuario(situacao, usuario)));
    }

    @GetMapping("/{iEntidade}")
    public ResponseEntity<?> entidade(@PathVariable @Valid Integer iEntidade) {
        permissoes.restritoAoCliente();
        return ResponseEntity.ok(entidadesAssembler.toModel(entidadesService.buscarOuFalhar(iEntidade)));
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody EntidadesModel entidadesModel) {
        permissoes.restritoAoCliente();
        if (entidadesModel.getIEntidade() != null) {
            throw new NegocioException("O código da Entidade não pode ser informado para novos cadastros!");
        }
        CaspEntidades entidadesNovo = entidadesService.novo(entidadesAssembler.toEntity(entidadesModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(entidadesAssembler.toModel(entidadesNovo));
    }

    @PutMapping("/{iEntidade}")
    public ResponseEntity editar(@Valid @RequestBody EntidadesModel entidadesModel, @PathVariable Integer iEntidade) {
        permissoes.restritoAoCliente();
        CaspEntidades caspEntidadesAtual = entidadesService.buscarOuFalhar(iEntidade);
        if (entidadesModel.getIEntidade() == null || caspEntidadesAtual.getIEntidade() != iEntidade) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspEntidades caspEntidadesEditado = entidadesAssembler.toEntity(entidadesModel);
        EntidadesModel tiposEntidadesEditado = entidadesAssembler.toModel(entidadesService.editar(caspEntidadesEditado, caspEntidadesAtual));
        return ResponseEntity.ok(tiposEntidadesEditado);
    }

    @DeleteMapping("/{iEntidade}")
    public ResponseEntity deletar(@PathVariable Integer iEntidade) {
        permissoes.restritoAoCliente();
        entidadesService.deletar(iEntidade);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(entidadesAssembler.toModel(new CaspEntidades()));
    }

}
