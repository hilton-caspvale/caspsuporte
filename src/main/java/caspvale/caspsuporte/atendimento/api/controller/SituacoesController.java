package caspvale.caspsuporte.atendimento.api.controller;

import caspvale.caspsuporte.atendimento.api.assembler.SituacoesAssembler;
import caspvale.caspsuporte.atendimento.api.model.SituacoesModel;
import caspvale.caspsuporte.atendimento.domain.service.SituacoesService;
import caspvale.caspsuporte.atendimento.common.Permissoes;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Hilton
 */
@RestController
@RequestMapping("/atendimento/situacoes")
public class SituacoesController {
    private final SituacoesAssembler situacoesAssembler;
    private final SituacoesService situacoesService;
    private final Permissoes permissoes;

    public SituacoesController(SituacoesAssembler situacoesAssembler, SituacoesService situacoesService, Permissoes permissoes) {
        this.situacoesAssembler = situacoesAssembler;
        this.situacoesService = situacoesService;
        this.permissoes = permissoes;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(situacoesAssembler.toCollectionModel(situacoesService.listar()));
    }
    
    @GetMapping("/{iSituacao}")
    public ResponseEntity<SituacoesModel> buscarSituacao(@PathVariable @Valid Integer iSituacao){
        return ResponseEntity.ok(situacoesAssembler.toModel(situacoesService.buscarOuFalhar(iSituacao)));
    }

    
}
