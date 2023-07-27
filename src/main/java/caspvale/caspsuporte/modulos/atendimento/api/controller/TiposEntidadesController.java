package caspvale.caspsuporte.modulos.atendimento.api.controller;

import caspvale.caspsuporte.modulos.atendimento.api.assembler.TiposEntidadesAssembler;
import caspvale.caspsuporte.modulos.atendimento.api.model.TiposEntidadesModel;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspTiposEntidades;
import caspvale.caspsuporte.modulos.atendimento.domain.service.TiposEntidadesService;
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

/**
 *
 * @author Hilton
 */
@RestController
@RequestMapping("/atendimento/tipos-entidades")
public class TiposEntidadesController {
    private final TiposEntidadesAssembler tiposEntidadesAssembler;
    private final TiposEntidadesService tiposEntidadesService;
    private final Permissoes permissoes;

    public TiposEntidadesController(TiposEntidadesAssembler tiposEntidadesAssembler, TiposEntidadesService tiposEntidadesService, Permissoes permissoes) {
        this.tiposEntidadesAssembler = tiposEntidadesAssembler;
        this.tiposEntidadesService = tiposEntidadesService;
        this.permissoes = permissoes;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(tiposEntidadesAssembler.toCollectionModel(tiposEntidadesService.listar()));
    }

    @GetMapping("/{iTiposEntidade}")
    public ResponseEntity<?> tiposEntidade(@PathVariable @Valid Integer iTiposEntidade) {        
        return ResponseEntity.ok(tiposEntidadesAssembler.toModel(tiposEntidadesService.buscarOuFalhar(iTiposEntidade)));
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody TiposEntidadesModel tiposEntidadesModel) {
        permissoes.restritoAoCliente();
        if(tiposEntidadesModel.getITiposEntidade()!= null){
            throw new NegocioException("O código do Tipos da entidades não pode ser informado para novos cadastros!");
        }
        CaspTiposEntidades tiposEntidadesNovo = tiposEntidadesService.novo(tiposEntidadesAssembler.toEntity(tiposEntidadesModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(tiposEntidadesAssembler.toModel(tiposEntidadesNovo));
    }

    @PutMapping("/{iTiposEntidade}")
    public ResponseEntity editar(@Valid @RequestBody TiposEntidadesModel tiposEntidadesModel, @PathVariable Integer iTiposEntidade) {
        permissoes.restritoAoCliente();
        CaspTiposEntidades caspTiposEntidadesAtual = tiposEntidadesService.buscarOuFalhar(iTiposEntidade);
        if (tiposEntidadesModel.getITiposEntidade()== null || caspTiposEntidadesAtual.getITiposEntidade()!= iTiposEntidade) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspTiposEntidades caspTiposEntidadesEditado = tiposEntidadesAssembler.toEntity(tiposEntidadesModel);
        TiposEntidadesModel tiposEntidadesEditado = tiposEntidadesAssembler.toModel(tiposEntidadesService.editar(caspTiposEntidadesEditado));
        return ResponseEntity.ok(tiposEntidadesEditado);
    }

    @DeleteMapping("/{iTiposEntidade}")
    public ResponseEntity deletar(@PathVariable Integer iTiposEntidade) {
        permissoes.restritoAoCliente();
        tiposEntidadesService.deletar(iTiposEntidade);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tiposEntidadesAssembler.toModel(new CaspTiposEntidades()));
    }
}
