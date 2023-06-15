package caspvale.caspsuporte.atendimento.api.controller;

import caspvale.caspsuporte.atendimento.api.assembler.ChamadosAssembler;
import caspvale.caspsuporte.atendimento.api.assembler.ChamadosInputAssembler;
import caspvale.caspsuporte.atendimento.api.assembler.UsuariosAssembler;
import caspvale.caspsuporte.atendimento.api.model.ChamadosInputModel;
import caspvale.caspsuporte.atendimento.api.model.ChamadosModel;
import caspvale.caspsuporte.atendimento.api.model.QuantidadeChamados;
import caspvale.caspsuporte.atendimento.common.ManipulaArquivos;
import caspvale.caspsuporte.atendimento.domain.model.CaspChamados;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.atendimento.domain.service.ChamadosService;
import caspvale.caspsuporte.atendimento.domain.service.UsuariosService;
import caspvale.caspsuporte.atendimento.common.Permissoes;
import caspvale.caspsuporte.atendimento.domain.model.CaspAnexos;
import caspvale.caspsuporte.atendimento.domain.service.AnexosService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Hilton
 */
@RestController
@RequestMapping("/atendimento/chamados")
public class ChamadosController {

    private final ChamadosAssembler chamadosAssembler;
    private final ChamadosInputAssembler chamadosInputAssembler;
    private final ChamadosService chamadosService;
    private final Permissoes permissoes;
    private final AnexosService anexosService;

    public ChamadosController(ChamadosService chamadosService,
            Permissoes permissoes, ChamadosAssembler chamadosAssembler,
            ChamadosInputAssembler chamadosInputAssembler,
            AnexosService anexosService) {
        this.chamadosService = chamadosService;
        this.permissoes = permissoes;
        this.chamadosAssembler = chamadosAssembler;
        this.chamadosInputAssembler = chamadosInputAssembler;
        this.anexosService = anexosService;
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

    @PatchMapping("/{id}/movimentar")
    public ResponseEntity<?> movimentarChamado(@PathVariable Integer id,
            @RequestBody(required = false) String comentario,
            @RequestParam(value = "acao", required = true) String acao,
            @RequestParam(value = "agenda", required = false) String agenda,
            @RequestParam(value = "usuario-encaminhar", required = false) Integer usuarioEncaminhar) {
        CaspUsuarios caspUsuarioLogado = permissoes.caspUsuarioLogado();
        CaspChamados caspChamado = chamadosService.chamadoLocalizadoPermitidoParaUsuario(id, caspUsuarioLogado);
        return ResponseEntity.ok(chamadosService.movimentarChamado(caspChamado, acao, comentario, caspUsuarioLogado, agenda, usuarioEncaminhar));
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
    public ResponseEntity<?> deletarComentario(@PathVariable Integer id, @PathVariable Integer comentario) {
        chamadosService.chamadoLocalizadoPermitidoParaUsuario(id, permissoes.caspUsuarioLogado());
        CaspAnexos caspAnexo = anexosService.buscarOuFalhar(comentario);
        anexosService.permiteExcluirAnexo(caspAnexo, permissoes.caspUsuarioLogado().getIUsuario());
        if (anexosService.deletarAnexo(caspAnexo)) {
            return ResponseEntity.ok("Registro excluído com sucesso!");
        } else {
            return ResponseEntity.ok("Não foi possível excluir o registro!");
        }
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
