package caspvale.caspsuporte.modulos.atendimento.api.controller;

import caspvale.caspsuporte.modulos.atendimento.api.assembler.AnexosAssembler;
import caspvale.caspsuporte.modulos.atendimento.api.assembler.ChamadosAssembler;
import caspvale.caspsuporte.modulos.atendimento.api.assembler.ChamadosInputAssembler;
import caspvale.caspsuporte.modulos.atendimento.api.model.AnexosModel;
import caspvale.caspsuporte.modulos.atendimento.api.model.ChamadosInputModel;
import caspvale.caspsuporte.modulos.atendimento.api.model.ChamadosModel;
import caspvale.caspsuporte.modulos.atendimento.api.model.QuantidadeChamados;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspChamados;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.modulos.atendimento.domain.service.ChamadosService;
import caspvale.caspsuporte.modulos.atendimento.common.Permissoes;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspAnexos;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspArquivos;
import caspvale.caspsuporte.modulos.atendimento.domain.service.AnexosService;
import caspvale.caspsuporte.modulos.atendimento.domain.service.ArquivosService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Hilton
 */
@RestController
@RequestMapping("/atendimento/chamados")
public class ChamadosController {
    
    //private static final Logger logger = LoggerFactory.getLogger(ChamadosController.class);

    private final ChamadosAssembler chamadosAssembler;
    private final ChamadosInputAssembler chamadosInputAssembler;
    private final AnexosAssembler anexosAssembler;
    private final ChamadosService chamadosService;
    private final Permissoes permissoes;
    private final AnexosService anexosService;
    private final ArquivosService arquivosService;

    public ChamadosController(ChamadosService chamadosService,
            Permissoes permissoes, ChamadosAssembler chamadosAssembler,
            AnexosAssembler anexosAssembler,
            ChamadosInputAssembler chamadosInputAssembler,
            AnexosService anexosService,
            ArquivosService arquivosService) {
        this.chamadosService = chamadosService;
        this.permissoes = permissoes;
        this.chamadosAssembler = chamadosAssembler;
        this.anexosAssembler = anexosAssembler;
        this.chamadosInputAssembler = chamadosInputAssembler;
        this.anexosService = anexosService;
        this.arquivosService = arquivosService;
    }

    @GetMapping()
    public ResponseEntity<?> listarChamados() {
        return ResponseEntity.ok(chamadosAssembler.toCollectionModel(chamadosService.listar()));
    }

    @GetMapping("aguardando")
    public ResponseEntity<?> aguardando() {
        return ResponseEntity.ok(chamadosAssembler.toCollectionModel(chamadosService.aguardando(permissoes.caspUsuarioLogado())));
    }

    @GetMapping("em-analise")
    public ResponseEntity<?> emAnalise() {
        return ResponseEntity.ok(chamadosAssembler.toCollectionModel(chamadosService.emAnalise(permissoes.caspUsuarioLogado())));
    }

    @GetMapping("finalizados")
    public ResponseEntity<?> finalizados() {
        return ResponseEntity.ok(chamadosAssembler.toCollectionModel(chamadosService.finalizados(permissoes.caspUsuarioLogado())));
    }

    @GetMapping("quantidade")
    public ResponseEntity<?> quantidadeChamados() {
        QuantidadeChamados quantidade = new QuantidadeChamados();
        CaspUsuarios usuarioLogado = permissoes.caspUsuarioLogado();
        quantidade.setTotalAguardando(chamadosService.aguardandoSize(usuarioLogado));
        quantidade.setTotalEmAtendimento(chamadosService.emAnaliseSize(usuarioLogado));
        quantidade.setTotalEncerrado(chamadosService.finalizadosSize(usuarioLogado));
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        CaspUsuarios caspUsuarioLogado = permissoes.caspUsuarioLogado();
        CaspChamados caspChamado = chamadosService.chamadoLocalizadoPermitidoParaUsuario(id, caspUsuarioLogado);
        ChamadosModel chamadoLocalizado = chamadosAssembler.toModel(caspChamado);
        //logger.info("maxFileSize applied: {}", 9999);
        return ResponseEntity.ok(chamadoLocalizado);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarNovoChamado(@Valid @RequestBody ChamadosInputModel chamadosInputModel) {
        ChamadosModel chamadoModel = chamadosInputAssembler.toEntity(chamadosInputModel);
        permissoes.permissoesAberturaUsuarioLogado(chamadoModel);
        CaspChamados chamado = chamadosAssembler.toEntity(chamadoModel);
        CaspChamados novoChamado = chamadosService.novoChamado(chamado);
        ChamadosModel novoChamadoModel = chamadosAssembler.toModel(novoChamado);
        return ResponseEntity.ok(novoChamadoModel);
    }

    @PutMapping
    public ResponseEntity<?> editarChamado(@Valid @RequestBody ChamadosInputModel chamadosInputModel) {
        ChamadosModel chamadoModel = chamadosInputAssembler.toEntity(chamadosInputModel);
        permissoes.permissoesAberturaUsuarioLogado(chamadoModel);
        CaspChamados chamado = chamadosAssembler.toEntity(chamadoModel);
        CaspChamados chamadoEditado = chamadosService.editarChamado(chamado);
        ChamadosModel chamadoEditadoRetorno = chamadosAssembler.toModel(chamadoEditado);
        return ResponseEntity.ok(chamadoEditadoRetorno);
    }

    @PatchMapping("/{id}/movimentar")
    public ResponseEntity<?> movimentarChamado(@PathVariable Integer id,
            @RequestBody(required = false) String comentario,
            @RequestParam(value = "acao", required = true) String acao,
            @RequestParam(value = "agenda", required = false) String agenda,
            @RequestParam(value = "usuario-encaminhar", required = false) Integer usuarioEncaminhar) {        
        CaspUsuarios caspUsuarioLogado = permissoes.caspUsuarioLogado();
        CaspChamados caspChamado = chamadosService.chamadoLocalizadoPermitidoParaUsuario(id, caspUsuarioLogado);
        ChamadosModel chamadoAtualizado = chamadosAssembler.toModel(chamadosService.movimentarChamado(caspChamado, acao, comentario, caspUsuarioLogado, agenda, usuarioEncaminhar));
        return ResponseEntity.ok(chamadoAtualizado);
    }

    @PatchMapping("/{id}/encerrar")
    public ResponseEntity<?> encerrarChamado(@PathVariable Integer id,
            @RequestBody(required = true) Object requestBody) {
        CaspUsuarios caspUsuarioLogado = permissoes.caspUsuarioLogado();
        CaspChamados caspChamadoLocalizado = chamadosService.chamadoLocalizadoPermitidoParaUsuario(id, caspUsuarioLogado);
        if (requestBody instanceof Map) {
            Map<String, Object> jsonMap = (Map<String, Object>) requestBody;
            String descricaoProblema = (String) jsonMap.get("descricaoProblema");
            String descricaoSolucao = (String) jsonMap.get("descricaoSolucao");
            caspChamadoLocalizado.setDescricaoProblema(descricaoProblema);
            caspChamadoLocalizado.setDescricaoSolucao(descricaoSolucao);
            ChamadosModel chamadoModelEncerrado = chamadosAssembler.toModel(chamadosService.movimentarChamado(caspChamadoLocalizado, "encerrar", null, caspUsuarioLogado, null, null));
            return ResponseEntity.ok(chamadoModelEncerrado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conteúdo indevido!");
        }
    }

    @PatchMapping(path = "/{id}/anexar"/*, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}*/)
    public ResponseEntity<?> pathAnexos(@PathVariable Integer id,
            @RequestParam("file") List<MultipartFile> file,
            @RequestParam(value = "comentarioAnexo", required = false) String comentarioAnexo) {
        CaspUsuarios caspUsuarioLogado = permissoes.caspUsuarioLogado();
        CaspChamados caspChamado = chamadosService.chamadoLocalizadoPermitidoParaUsuario(id, caspUsuarioLogado);
        anexosService.adicionarArquivo(caspChamado, caspUsuarioLogado, file, comentarioAnexo);
        return ResponseEntity.ok("Anexo gravado com sucesso!");
    }

    @DeleteMapping("/{id}/anexos/{comentario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deletarAnexo(@PathVariable Integer id, @PathVariable Integer comentario) {
        chamadosService.chamadoLocalizadoPermitidoParaUsuario(id, permissoes.caspUsuarioLogado());
        CaspAnexos caspAnexo = anexosService.buscarOuFalhar(comentario);
        anexosService.permiteExcluirAnexo(caspAnexo, permissoes.caspUsuarioLogado().getIUsuario());
        if (anexosService.deletarAnexo(caspAnexo)) {
            return ResponseEntity.ok("Registro excluído com sucesso!");
        } else {
            return ResponseEntity.ok("Não foi possível excluir o registro!");
        }
    }

    @GetMapping("/{id}/anexos/{ianexo}")
    public HttpEntity<byte[]> download(@PathVariable("id") Integer id,
            @PathVariable("ianexo") Integer ianexo) {
        chamadosService.chamadoLocalizadoPermitidoParaUsuario(id, permissoes.caspUsuarioLogado());
        AnexosModel anexosModel = anexosAssembler.toModel(anexosService.buscarOuFalhar(ianexo));
        CaspArquivos caspArquivos = arquivosService.buscarOuFalhar(anexosModel.getIArquivo());
        byte[] bytes = caspArquivos.getArquivo();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=\"" + anexosModel.getDescricaoArquivo() + "\"");
        HttpEntity<byte[]> entity = new HttpEntity<>(bytes, httpHeaders);
        return entity;
    }

    public void debug(CaspChamados caspChamados) {
        System.out.println("getCasoBetha: " + caspChamados.getCasoBetha());
        System.out.println("getComentarioChamado: " + caspChamados.getComentarioChamado());
        System.out.println("getContatoSolicitante: " + caspChamados.getContatoSolicitante());
        System.out.println("getDescricaoChamado: " + caspChamados.getDescricaoChamado());
        System.out.println("getDescricaoProblema: " + caspChamados.getDescricaoProblema());
        System.out.println("getDescricaoSolucao: " + caspChamados.getDescricaoSolucao());
        System.out.println("getEmailSolicitante: " + caspChamados.getEmailSolicitante());
        System.out.println("getHistoricoEmail: " + caspChamados.getHistoricoEmail());
        System.out.println("getHostoricoChat: " + caspChamados.getHostoricoChat());
        System.out.println("getResumoChamado: " + caspChamados.getResumoChamado());
        System.out.println("getSituacaoChamado: " + caspChamados.getSituacaoChamado());
        System.out.println("getUsuarioAlteracao: " + caspChamados.getUsuarioAlteracao());
        //System.out.println("sit: "+caspChamados.getCaspAnexosList());
        System.out.println("getCaspAreasList: " + caspChamados.getCaspAreasList());
        System.out.println("getCaspEntidadesList: " + caspChamados.getCaspEntidadesList());
        System.out.println("getCaspProblemasList: " + caspChamados.getCaspProblemasList());
        System.out.println("getCaspSistemasList: " + caspChamados.getCaspSistemasList());
        System.out.println("getCaspUsuariosList: " + caspChamados.getCaspUsuariosList());
        System.out.println("getDataAbertura: " + caspChamados.getDataAbertura());
        System.out.println("getDataAgendamento: " + caspChamados.getDataAgendamento());
        System.out.println("getDataEncerramento: " + caspChamados.getDataEncerramento());
        System.out.println("getDtAlteracao: " + caspChamados.getDtAlteracao());
        System.out.println("getIChamado: " + caspChamados.getIChamado());
        System.out.println("getINivel: " + caspChamados.getINivel());
        System.out.println("getISituacao: " + caspChamados.getISituacao());
        System.out.println("getIOrigemChamado: " + caspChamados.getIOrigemChamado());
        System.out.println("getIPrioridade: " + caspChamados.getIPrioridade());
        System.out.println("getISituacao: " + caspChamados.getISituacao());
        System.out.println("getIUsuarioAbertura: " + caspChamados.getIUsuarioAbertura());
        System.out.println("getIUsuarioAtendimento: " + caspChamados.getIUsuarioAtendimento());
        System.out.println("getIUsuarioEncaminhamento: " + caspChamados.getIUsuarioEncaminhamento());
        System.out.println("getIUsuarioEncerramento: " + caspChamados.getIUsuarioEncerramento());
    }

}
