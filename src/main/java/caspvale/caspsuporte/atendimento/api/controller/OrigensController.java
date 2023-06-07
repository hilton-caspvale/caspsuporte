package caspvale.caspsuporte.atendimento.api.controller;

import caspvale.caspsuporte.atendimento.api.assembler.OrigensAssembler;
import caspvale.caspsuporte.atendimento.api.model.OrigensModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspOrigens;
import caspvale.caspsuporte.atendimento.domain.service.OrigensService;
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
@RequestMapping("/atendimento/origens")
public class OrigensController {
    private final OrigensAssembler origensAssembler;
    private final OrigensService origensService;
    private final Permissoes permissoes;

    public OrigensController(OrigensAssembler origensAssembler, OrigensService origensService, Permissoes permissoes) {
        this.origensAssembler = origensAssembler;
        this.origensService = origensService;
        this.permissoes = permissoes;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(origensAssembler.toCollectionModel(origensService.listar()));
    }

    @GetMapping("/{iOrigemChamado}")
    public ResponseEntity<?> origem(@PathVariable @Valid Integer iOrigemChamado) {        
        return ResponseEntity.ok(origensAssembler.toModel(origensService.buscarOuFalhar(iOrigemChamado)));
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody OrigensModel model) {
        permissoes.restritoAoCliente();
        if(model.getIOrigemChamado()!= null){
            throw new NegocioException("O código do nível não pode ser informado para novos cadastros!");
        }
        CaspOrigens novoOrigem = origensService.novo(origensAssembler.toEntity(model));
        return ResponseEntity.status(HttpStatus.CREATED).body(origensAssembler.toModel(novoOrigem));
    }

    @PutMapping("/{iOrigemChamado}")
    public ResponseEntity editar(@Valid @RequestBody OrigensModel model, @PathVariable Integer iOrigemChamado) {
        permissoes.restritoAoCliente();
        CaspOrigens caspOrigemAtual = origensService.buscarOuFalhar(iOrigemChamado);
        if (model.getIOrigemChamado()== null || caspOrigemAtual.getIOrigemChamado()!= iOrigemChamado) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspOrigens caspOrigemEditado = origensAssembler.toEntity(model);
        OrigensModel origemEditado = origensAssembler.toModel(origensService.editar(caspOrigemEditado));
        return ResponseEntity.ok(origemEditado);
    }

    @DeleteMapping("/{iOrigemChamado}")
    public ResponseEntity deletar(@PathVariable Integer iOrigemChamado) {
        permissoes.restritoAoCliente();
        origensService.deletar(iOrigemChamado);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(origensAssembler.toModel(new CaspOrigens()));
    }
}
