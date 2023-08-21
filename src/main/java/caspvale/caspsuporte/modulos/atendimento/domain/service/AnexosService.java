/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.domain.service;

import caspvale.caspsuporte.modulos.atendimento.common.ManipulaArquivos;
import caspvale.caspsuporte.modulos.atendimento.common.OperacoesTexto;
import caspvale.caspsuporte.modulos.atendimento.common.Permissoes;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspAnexos;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspArquivos;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspChamados;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspTiposArquivos;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.AnexosRepository;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.ArquivosRepository;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.TiposArquivosRepository;
import caspvale.caspsuporte.modulos.atendimento.exception.AnexoNaoEncontradoException;
import caspvale.caspsuporte.modulos.atendimento.exception.NegocioException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Hilton
 */
@Service
public class AnexosService {
    
    private final AnexosRepository anexosRepository;
    private final ArquivosRepository arquivosRepository;
    private final TiposArquivosRepository tiposArquivosRepository;
    private final ManipulaArquivos manipulaArquivos;
    
    public AnexosService(AnexosRepository anexosRepository,
            ArquivosRepository arquivosRepository,
            TiposArquivosRepository tiposArquivosRepository,
            ManipulaArquivos manipulaArquivos) {
        this.anexosRepository = anexosRepository;
        this.arquivosRepository = arquivosRepository;
        this.tiposArquivosRepository = tiposArquivosRepository;
        this.manipulaArquivos = manipulaArquivos;
    }
    
    public CaspAnexos buscarOuFalhar(Integer id) {
        CaspAnexos caspAnexo = anexosRepository.findById(id).orElseThrow(()
                -> new AnexoNaoEncontradoException("Comentário ou arquivo não localizado!")
        );
        return caspAnexo;
    }
    
    public boolean permiteExcluirAnexo(CaspAnexos caspAnexo, Integer iUsuarioLogado) {
        return Objects.equals(caspAnexo.getIUsuario().getIUsuario(), iUsuarioLogado);
    }
    
    @Transactional
    public boolean deletarAnexo(CaspAnexos caspAnexo) {
        try {
            if (caspAnexo.getIArquivo() != null) {
                arquivosRepository.deleteById(caspAnexo.getIArquivo());
            }
            anexosRepository.deleteById(caspAnexo.getIAnexo());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Transactional
    private CaspAnexos gravar(CaspAnexos caspAnexo) {
        return anexosRepository.saveAndFlush(caspAnexo);
    }
    
    @Transactional
    public void adicionarArquivo(CaspChamados caspChamado, CaspUsuarios caspUsuariologado, List<MultipartFile> file, String comentarioAnexo) {
        manipulaArquivos.tamanhoPermitido(file);
        gravarArquivosAnexados(caspChamado, file, caspUsuariologado);
        OperacoesTexto operacoesTexto = new OperacoesTexto();
        if (!operacoesTexto.textoVazio(comentarioAnexo)) {
            adicionarComentario(caspChamado, caspUsuariologado, operacoesTexto.replaceHtml(comentarioAnexo));
        }
    }
    
    @Transactional
    public CaspAnexos adicionarComentario(CaspChamados caspchamado, CaspUsuarios caspUsuariologado, String comentario) {
        CaspAnexos caspAnexos = new CaspAnexos();
        caspAnexos.setIChamado(caspchamado);
        caspAnexos.setIUsuario(caspUsuariologado);
        caspAnexos.setComentarioArquivo(comentario);
        caspAnexos.setDataArquivo(LocalDateTime.now());
        return gravar(caspAnexos);
    }
    
    @Transactional
    private void gravarArquivosAnexados(CaspChamados caspChamados, List<MultipartFile> file, CaspUsuarios usuarioLogado) {
        List<CaspAnexos> listaAnexos = new ArrayList<>();
        file.forEach(arqui -> {
            if (!arqui.getOriginalFilename().equals("")) {
                try {
                    CaspArquivos novoArquivo = new CaspArquivos();
                    CaspAnexos novoAnexo = new CaspAnexos();
                    novoArquivo.setArquivo(arqui.getBytes());
                    arquivosRepository.save(novoArquivo);
                    novoAnexo.setIArquivo(novoArquivo.getIArquivo());
                    novoAnexo.setArquivo(null);
                    novoAnexo.setDataArquivo(LocalDateTime.now());
                    novoAnexo.setDescricaoArquivo(arqui.getOriginalFilename());
                    novoAnexo.setITipoArquivo(tiposArquivos(arqui.getContentType()));
                    novoAnexo.setIUsuario(usuarioLogado);
                    novoAnexo.setSituacaoArquivo("A");
                    novoAnexo.setComentarioArquivo(formataTamanhoArquivoEmMB(tamanhoDoArquivoEmKB(arqui)));
                    novoAnexo.setDiretorioArquivo(arqui.getContentType());
                    anexosRepository.save(novoAnexo);
                    listaAnexos.add(novoAnexo);
                    caspChamados.getCaspAnexosList().add(novoAnexo);
                } catch (IOException ex) {
                    throw new NegocioException("Erro ao gravar anexo", ex);
                }
            }
        });
        listaAnexos.forEach(anexo -> {
            anexo.setIChamado(caspChamados);
        });
        anexosRepository.saveAll(listaAnexos);
    }
    
    public CaspTiposArquivos tiposArquivos(String contentType) {
        if (contentType.contains("image")) {
            return tiposArquivosRepository.findById(3).get();
        } else {
            return tiposArquivosRepository.findById(1).get();
        }
    }
    
    public double tamanhoDoArquivoEmKB(MultipartFile file) {
        double total = 0;
        long mb = 1024L;
        total = file.getSize();
        total = total / mb;
        return Math.round(total * 100.0) / 100.0;
    }
    
    public String formataTamanhoArquivoEmMB(double tamanho) {
        return String.valueOf(tamanho).replace(".", ",") + " KB";
    }
    
}
