package caspvale.caspsuporte.atendimento.api.controller;

import caspvale.caspsuporte.atendimento.api.assembler.PrioridadesAssembler;
import caspvale.caspsuporte.atendimento.api.model.PrioridadesModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspPrioridades;
import caspvale.caspsuporte.atendimento.domain.service.PrioridadesService;
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
@RequestMapping("/atendimento/prioridades")
public class PrioridadesController {
    private final PrioridadesAssembler prioridadesAssembler;
    private final PrioridadesService prioridadesService;
    private final Permissoes permissoes;

    public PrioridadesController(PrioridadesAssembler prioridadesAssembler, PrioridadesService prioridadesService, Permissoes permissoes) {
        this.prioridadesAssembler = prioridadesAssembler;
        this.prioridadesService = prioridadesService;
        this.permissoes = permissoes;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(prioridadesAssembler.toCollectionModel(prioridadesService.listar()));
    }

    @GetMapping("/{iPrioridade}")
    public ResponseEntity<?> prioridade(@PathVariable @Valid Integer iPrioridade) {        
        return ResponseEntity.ok(prioridadesAssembler.toModel(prioridadesService.buscarOuFalhar(iPrioridade)));
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody PrioridadesModel prioridadesModel) {
        permissoes.exclusivoAdministrador();
        if(prioridadesModel.getIPrioridade()!= null){
            throw new NegocioException("O código do prioridade não pode ser informado para novos cadastros!");
        }
        CaspPrioridades prioridadeNovo = prioridadesService.novo(prioridadesAssembler.toEntity(prioridadesModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(prioridadesAssembler.toModel(prioridadeNovo));
    }

    @PutMapping("/{iPrioridade}")
    public ResponseEntity editar(@Valid @RequestBody PrioridadesModel prioridadesModel, @PathVariable Integer iPrioridade) {
        permissoes.exclusivoAdministrador();
        CaspPrioridades caspPrioridadeAtual = prioridadesService.buscarOuFalhar(iPrioridade);
        if (prioridadesModel.getIPrioridade()== null || caspPrioridadeAtual.getIPrioridade()!= iPrioridade) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspPrioridades caspPrioridadeEditado = prioridadesAssembler.toEntity(prioridadesModel);
        PrioridadesModel prioridadeEditado = prioridadesAssembler.toModel(prioridadesService.editar(caspPrioridadeEditado));
        return ResponseEntity.ok(prioridadeEditado);
    }

    @DeleteMapping("/{iPrioridade}")
    public ResponseEntity deletar(@PathVariable Integer iPrioridade) {
        permissoes.exclusivoAdministrador();
        prioridadesService.deletar(iPrioridade);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(prioridadesAssembler.toModel(new CaspPrioridades()));
    }
}
