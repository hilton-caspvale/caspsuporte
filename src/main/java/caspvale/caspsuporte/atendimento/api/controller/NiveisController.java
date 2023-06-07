package caspvale.caspsuporte.atendimento.api.controller;

import caspvale.caspsuporte.atendimento.api.assembler.NiveisAssembler;
import caspvale.caspsuporte.atendimento.api.model.NiveisModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspNiveis;
import caspvale.caspsuporte.atendimento.domain.service.NiveisService;
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
@RequestMapping("/atendimento/niveis")
public class NiveisController {
    private final NiveisAssembler niveisAssembler;
    private final NiveisService niveisService;
    private final Permissoes permissoes;

    public NiveisController(NiveisAssembler niveisAssembler, NiveisService niveisService, Permissoes permissoes) {
        this.niveisAssembler = niveisAssembler;
        this.niveisService = niveisService;
        this.permissoes = permissoes;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(niveisAssembler.toCollectionModel(niveisService.listar()));
    }

    @GetMapping("/{iNivel}")
    public ResponseEntity<?> nivel(@PathVariable @Valid Integer iNivel) {        
        return ResponseEntity.ok(niveisAssembler.toModel(niveisService.buscarOuFalhar(iNivel)));
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody NiveisModel model) {
        permissoes.exclusivoAdministrador();
        if(model.getINivel()!= null){
            throw new NegocioException("O código do nível não pode ser informado para novos cadastros!");
        }
        CaspNiveis novoNivel = niveisService.novo(niveisAssembler.toEntity(model));
        return ResponseEntity.status(HttpStatus.CREATED).body(niveisAssembler.toModel(novoNivel));
    }

    @PutMapping("/{iNivel}")
    public ResponseEntity editar(@Valid @RequestBody NiveisModel model, @PathVariable Integer iNivel) {
        permissoes.exclusivoAdministrador();
        CaspNiveis caspNivelAtual = niveisService.buscarOuFalhar(iNivel);
        if (model.getINivel()== null || caspNivelAtual.getINivel()!= iNivel) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspNiveis caspNivelEditado = niveisAssembler.toEntity(model);
        NiveisModel nivelEditado = niveisAssembler.toModel(niveisService.editar(caspNivelEditado));
        return ResponseEntity.ok(nivelEditado);
    }

    @DeleteMapping("/{iNivel}")
    public ResponseEntity deletar(@PathVariable Integer iNivel) {
        permissoes.exclusivoAdministrador();
        niveisService.deletar(iNivel);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(niveisAssembler.toModel(new CaspNiveis()));
    }
}
