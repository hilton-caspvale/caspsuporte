package caspvale.caspsuporte.atendimento.api.controller;

import caspvale.caspsuporte.atendimento.api.assembler.TiposArquivosAssembler;
import caspvale.caspsuporte.atendimento.api.model.TiposArquivosModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspTiposArquivos;
import caspvale.caspsuporte.atendimento.domain.service.TiposArquivosService;
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
@RequestMapping("/atendimento/tipos-arquivos")
public class TiposArquivosController {
 private final TiposArquivosAssembler tiposArquivosAssembler;
    private final TiposArquivosService tiposArquivosService;
    private final Permissoes permissoes;

    public TiposArquivosController(TiposArquivosAssembler tiposArquivosAssembler, TiposArquivosService tiposArquivosService,Permissoes permissoes) {
        this.tiposArquivosAssembler = tiposArquivosAssembler;
        this.tiposArquivosService = tiposArquivosService;
        this.permissoes = permissoes;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(tiposArquivosAssembler.toCollectionModel(tiposArquivosService.listar()));
    }

    @GetMapping("/{iTipoArquivo}")
    public ResponseEntity<?> tipoArquivo(@PathVariable @Valid Integer iTipoArquivo) {        
        return ResponseEntity.ok(tiposArquivosAssembler.toModel(tiposArquivosService.buscarOuFalhar(iTipoArquivo)));
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody TiposArquivosModel model) {
        permissoes.restritoAoCliente();
        if(model.getITipoArquivo()!= null){
            throw new NegocioException("O código do tipo do arquivo não pode ser informado para novos cadastros!");
        }
        CaspTiposArquivos tiposArquivoNovo = tiposArquivosService.novo(tiposArquivosAssembler.toEntity(model));
        return ResponseEntity.status(HttpStatus.CREATED).body(tiposArquivosAssembler.toModel(tiposArquivoNovo));
    }

    @PutMapping("/{iTipoArquivo}")
    public ResponseEntity editar(@Valid @RequestBody TiposArquivosModel mudel, @PathVariable Integer iTipoArquivo) {
        permissoes.restritoAoCliente();
        CaspTiposArquivos caspTiposArquivosAtual = tiposArquivosService.buscarOuFalhar(iTipoArquivo);
        if (mudel.getITipoArquivo()== null || caspTiposArquivosAtual.getITipoArquivo()!= iTipoArquivo) {
            throw new NegocioException("Conteúdo difere do item selecionado!");
        }
        CaspTiposArquivos caspTiposArquivosEditado = tiposArquivosAssembler.toEntity(mudel);
        TiposArquivosModel modelEditado = tiposArquivosAssembler.toModel(tiposArquivosService.editar(caspTiposArquivosEditado));
        return ResponseEntity.ok(modelEditado);
    }

    @DeleteMapping("/{iTipoArquivo}")
    public ResponseEntity deletar(@PathVariable Integer iTipoArquivo) {
        permissoes.restritoAoCliente();
        tiposArquivosService.deletar(iTipoArquivo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tiposArquivosAssembler.toModel(new CaspTiposArquivos()));
    }
}
