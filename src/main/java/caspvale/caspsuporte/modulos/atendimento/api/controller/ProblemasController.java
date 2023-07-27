package caspvale.caspsuporte.modulos.atendimento.api.controller;

import caspvale.caspsuporte.modulos.atendimento.api.assembler.ProblemasAssembler;
import caspvale.caspsuporte.modulos.atendimento.api.model.ProblemasModel;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspProblemas;
import caspvale.caspsuporte.modulos.atendimento.domain.service.ProblemasService;
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
@RequestMapping("/atendimento/problemas")
public class ProblemasController {

    private final ProblemasAssembler problemasAssembler;
    private final ProblemasService problemasService;
    private final Permissoes permissoes;

    public ProblemasController(ProblemasAssembler problemasAssembler, ProblemasService problemasService, Permissoes permissoes) {
        this.problemasAssembler = problemasAssembler;
        this.problemasService = problemasService;
        this.permissoes = permissoes;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(problemasAssembler.toCollectionModel(problemasService.listar()));
    }

    /*@GetMapping("/s")
    public ResponseEntity<?> listarAtivos() {
        return ResponseEntity.ok(problemasAssembler.toCollectionModel(problemasService.listarPorSituacao("A")));
    }*/
    @GetMapping("/{iProblema}")
    public ResponseEntity<?> problema(@PathVariable @Valid Integer iProblema) {
        return ResponseEntity.ok(problemasAssembler.toModel(problemasService.buscarOuFalhar(iProblema)));
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody ProblemasModel model) {
        permissoes.restritoAoCliente();
        if (model.getIProblema() != null) {
            throw new NegocioException("O código do problema não pode ser informado para novos cadastros!");
        }
        CaspProblemas problemaNovo = problemasService.novo(problemasAssembler.toEntity(model));
        return ResponseEntity.status(HttpStatus.CREATED).body(problemasAssembler.toModel(problemaNovo));
    }

    @PutMapping("/{iSistema}")
    public ResponseEntity editar(@Valid @RequestBody ProblemasModel problemaModel, @PathVariable Integer iSistema) {
        permissoes.restritoAoCliente();
        CaspProblemas caspProblemaAtual = problemasService.buscarOuFalhar(iSistema);
        if (problemaModel.getIProblema() == null || caspProblemaAtual.getIProblema() != iSistema) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspProblemas caspProblemaEditado = problemasAssembler.toEntity(problemaModel);
        ProblemasModel modelEditado = problemasAssembler.toModel(problemasService.editar(caspProblemaEditado));
        return ResponseEntity.ok(modelEditado);
    }

    @DeleteMapping("/{iProblema}")
    public ResponseEntity deletar(@PathVariable Integer iProblema) {
        permissoes.restritoAoCliente();
        problemasService.deletar(iProblema);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(problemasAssembler.toModel(new CaspProblemas()));
    }
}
