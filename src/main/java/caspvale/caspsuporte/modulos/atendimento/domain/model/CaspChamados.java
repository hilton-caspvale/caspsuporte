/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "casp_chamados",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspChamados.findAll", query = "SELECT c FROM CaspChamados c"),
    @NamedQuery(name = "CaspChamados.findByIChamado", query = "SELECT c FROM CaspChamados c WHERE c.iChamado = :iChamado"),
    @NamedQuery(name = "CaspChamados.findByDataAbertura", query = "SELECT c FROM CaspChamados c WHERE c.dataAbertura = :dataAbertura"),
    @NamedQuery(name = "CaspChamados.findByDataAtendimento", query = "SELECT c FROM CaspChamados c WHERE c.dataAtendimento = :dataAtendimento"),
    @NamedQuery(name = "CaspChamados.findByDataAgendamento", query = "SELECT c FROM CaspChamados c WHERE c.dataAgendamento = :dataAgendamento"),
    @NamedQuery(name = "CaspChamados.findByDataEncerramento", query = "SELECT c FROM CaspChamados c WHERE c.dataEncerramento = :dataEncerramento"),
    @NamedQuery(name = "CaspChamados.findByTempoAtendimento", query = "SELECT c FROM CaspChamados c WHERE c.tempoAtendimento = :tempoAtendimento"),
    @NamedQuery(name = "CaspChamados.findByTempoSolucao", query = "SELECT c FROM CaspChamados c WHERE c.tempoSolucao = :tempoSolucao"),
    @NamedQuery(name = "CaspChamados.findByTempoTotal", query = "SELECT c FROM CaspChamados c WHERE c.tempoTotal = :tempoTotal"),
    @NamedQuery(name = "CaspChamados.findByCasoBetha", query = "SELECT c FROM CaspChamados c WHERE c.casoBetha = :casoBetha"),
    @NamedQuery(name = "CaspChamados.findByResumoChamado", query = "SELECT c FROM CaspChamados c WHERE c.resumoChamado = :resumoChamado"),
    @NamedQuery(name = "CaspChamados.findByDescricaoChamado", query = "SELECT c FROM CaspChamados c WHERE c.descricaoChamado = :descricaoChamado"),
    @NamedQuery(name = "CaspChamados.findByComentarioChamado", query = "SELECT c FROM CaspChamados c WHERE c.comentarioChamado = :comentarioChamado"),
    @NamedQuery(name = "CaspChamados.findByDescricaoProblema", query = "SELECT c FROM CaspChamados c WHERE c.descricaoProblema = :descricaoProblema"),
    @NamedQuery(name = "CaspChamados.findByDescricaoSolucao", query = "SELECT c FROM CaspChamados c WHERE c.descricaoSolucao = :descricaoSolucao"),
    @NamedQuery(name = "CaspChamados.findByContatoSolicitante", query = "SELECT c FROM CaspChamados c WHERE c.contatoSolicitante = :contatoSolicitante"),
    @NamedQuery(name = "CaspChamados.findByEmailSolicitante", query = "SELECT c FROM CaspChamados c WHERE c.emailSolicitante = :emailSolicitante"),
    @NamedQuery(name = "CaspChamados.findByHostoricoChat", query = "SELECT c FROM CaspChamados c WHERE c.hostoricoChat = :hostoricoChat"),
    @NamedQuery(name = "CaspChamados.findByHistoricoEmail", query = "SELECT c FROM CaspChamados c WHERE c.historicoEmail = :historicoEmail"),
    @NamedQuery(name = "CaspChamados.findBySituacaoChamado", query = "SELECT c FROM CaspChamados c WHERE c.situacaoChamado = :situacaoChamado"),
    @NamedQuery(name = "CaspChamados.findByUsuarioAlteracao", query = "SELECT c FROM CaspChamados c WHERE c.usuarioAlteracao = :usuarioAlteracao"),
    @NamedQuery(name = "CaspChamados.findByDtAlteracao", query = "SELECT c FROM CaspChamados c WHERE c.dtAlteracao = :dtAlteracao")})
public class CaspChamados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_chamado")
    private Integer iChamado;
    @Column(name = "data_abertura")
//    @Temporal(TemporalType.TIMESTAMP)
//    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @Column(name = "data_atendimento")
    ////@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAtendimento;
    @Column(name = "data_agendamento")
    ////@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAgendamento;
    @Column(name = "data_encerramento")
    ////@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataEncerramento;
    @Column(name = "tempo_atendimento")
    //@Temporal(TemporalType.TIME)
    private LocalTime tempoAtendimento;
    @Column(name = "tempo_solucao")
  //  @Temporal(TemporalType.TIME)
    private LocalTime tempoSolucao;
    @Column(name = "tempo_total")
  //  @Temporal(TemporalType.TIME)
    private LocalTime tempoTotal;
    @Size(max = 2147483647)
    @Column(name = "caso_betha")
    private String casoBetha;
    @Size(max = 2147483647)
    @Column(name = "resumo_chamado")
    private String resumoChamado;
    @Size(max = 2147483647)
    @Column(name = "descricao_chamado")
    private String descricaoChamado;
    @Size(max = 2147483647)
    @Column(name = "comentario_chamado")
    private String comentarioChamado;
    @Size(max = 2147483647)
    @Column(name = "descricao_problema")
    private String descricaoProblema;
    @Size(max = 2147483647)
    @Column(name = "descricao_solucao")
    private String descricaoSolucao;
    @Size(max = 2147483647)
    @Column(name = "contato_solicitante")
    private String contatoSolicitante;
    @Size(max = 2147483647)
    @Column(name = "email_solicitante")
    private String emailSolicitante;
    @Size(max = 2147483647)
    @Column(name = "hostorico_chat")
    private String hostoricoChat;
    @Size(max = 2147483647)
    @Column(name = "historico_email")
    private String historicoEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "situacao_chamado")
    private String situacaoChamado;
    @Size(max = 2147483647)
    @Column(name = "usuario_alteracao")
    private String usuarioAlteracao;
    @Column(name = "dt_alteracao")
    ////@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dtAlteracao;
    @JoinTable(name = "casp_chamados_areas",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_chamado", referencedColumnName = "i_chamado")}, inverseJoinColumns = {
        @JoinColumn(name = "i_area", referencedColumnName = "i_area")})
    @ManyToMany
    private List<CaspAreas> caspAreasList;
    @JoinTable(name = "casp_chamados_entidades",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_chamado", referencedColumnName = "i_chamado")}, inverseJoinColumns = {
        @JoinColumn(name = "i_entidades", referencedColumnName = "i_entidade")})
    @ManyToMany
    private List<CaspEntidades> caspEntidadesList;
    @JoinTable(name = "casp_chamados_sistemas",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_chamado", referencedColumnName = "i_chamado")}, inverseJoinColumns = {
        @JoinColumn(name = "i_sistema", referencedColumnName = "i_sistema")})
    @ManyToMany
    private List<CaspSistemas> caspSistemasList;
    @JoinTable(name = "casp_chamados_problemas",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_chamado", referencedColumnName = "i_chamado")}, inverseJoinColumns = {
        @JoinColumn(name = "i_problema", referencedColumnName = "i_problema")})
    @ManyToMany
    private List<CaspProblemas> caspProblemasList;
    @JoinTable(name = "casp_chamados_usuarios",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_chamado", referencedColumnName = "i_chamado")}, inverseJoinColumns = {
        @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")})
    @ManyToMany
    private List<CaspUsuarios> caspUsuariosList;
    @JoinColumn(name = "i_nivel", referencedColumnName = "i_nivel")
    @ManyToOne(optional = false)
    private CaspNiveis iNivel;
    @JoinColumn(name = "i_origem_chamado", referencedColumnName = "i_origem_chamado")
    @ManyToOne(optional = false)
    private CaspOrigens iOrigemChamado;
    @JoinColumn(name = "i_prioridade", referencedColumnName = "i_prioridade")
    @ManyToOne(optional = false)
    private CaspPrioridades iPrioridade;
    @JoinColumn(name = "i_situacao", referencedColumnName = "i_situacao")
    @ManyToOne(optional = false)
    private CaspSituacoes iSituacao;
    @JoinColumn(name = "i_usuario_abertura", referencedColumnName = "i_usuario")
    @ManyToOne
    private CaspUsuarios iUsuarioAbertura;
    @JoinColumn(name = "i_usuario_atendimento", referencedColumnName = "i_usuario")
    @ManyToOne
    private CaspUsuarios iUsuarioAtendimento;
    @JoinColumn(name = "i_usuario_encaminhamento", referencedColumnName = "i_usuario")
    @ManyToOne
    private CaspUsuarios iUsuarioEncaminhamento;
    @JoinColumn(name = "i_usuario_encerramento", referencedColumnName = "i_usuario")
    @ManyToOne
    private CaspUsuarios iUsuarioEncerramento;
    @OneToMany(mappedBy = "iChamado")
    private List<CaspAnexos> caspAnexosList;

    public CaspChamados() {
    }

    public CaspChamados(Integer iChamado) {
        this.iChamado = iChamado;
    }

    public CaspChamados(Integer iChamado, String situacaoChamado) {
        this.iChamado = iChamado;
        this.situacaoChamado = situacaoChamado;
    }

    public Integer getIChamado() {
        return iChamado;
    }

    public void setIChamado(Integer iChamado) {
        this.iChamado = iChamado;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDateTime dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public LocalTime getTempoAtendimento() {
        return tempoAtendimento;
    }

    public void setTempoAtendimento(LocalTime tempoAtendimento) {
        this.tempoAtendimento = tempoAtendimento;
    }

    public LocalTime getTempoSolucao() {
        return tempoSolucao;
    }

    public void setTempoSolucao(LocalTime tempoSolucao) {
        this.tempoSolucao = tempoSolucao;
    }

    public LocalTime getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(LocalTime tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public String getCasoBetha() {
        return casoBetha;
    }

    public void setCasoBetha(String casoBetha) {
        this.casoBetha = casoBetha;
    }

    public String getResumoChamado() {
        return resumoChamado;
    }

    public void setResumoChamado(String resumoChamado) {
        this.resumoChamado = resumoChamado;
    }

    public String getDescricaoChamado() {
        return descricaoChamado;
    }

    public void setDescricaoChamado(String descricaoChamado) {
        this.descricaoChamado = descricaoChamado;
    }

    public String getComentarioChamado() {
        return comentarioChamado;
    }

    public void setComentarioChamado(String comentarioChamado) {
        this.comentarioChamado = comentarioChamado;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getDescricaoSolucao() {
        return descricaoSolucao;
    }

    public void setDescricaoSolucao(String descricaoSolucao) {
        this.descricaoSolucao = descricaoSolucao;
    }

    public String getContatoSolicitante() {
        return contatoSolicitante;
    }

    public void setContatoSolicitante(String contatoSolicitante) {
        this.contatoSolicitante = contatoSolicitante;
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

    public String getHostoricoChat() {
        return hostoricoChat;
    }

    public void setHostoricoChat(String hostoricoChat) {
        this.hostoricoChat = hostoricoChat;
    }

    public String getHistoricoEmail() {
        return historicoEmail;
    }

    public void setHistoricoEmail(String historicoEmail) {
        this.historicoEmail = historicoEmail;
    }

    public String getSituacaoChamado() {
        return situacaoChamado;
    }

    public void setSituacaoChamado(String situacaoChamado) {
        this.situacaoChamado = situacaoChamado;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public LocalDateTime getDtAlteracao() {
        return dtAlteracao;
    }

    public void setDtAlteracao(LocalDateTime dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    @XmlTransient
    public List<CaspAreas> getCaspAreasList() {
        return caspAreasList;
    }

    public void setCaspAreasList(List<CaspAreas> caspAreasList) {
        this.caspAreasList = caspAreasList;
    }

    @XmlTransient
    public List<CaspEntidades> getCaspEntidadesList() {
        return caspEntidadesList;
    }

    public void setCaspEntidadesList(List<CaspEntidades> caspEntidadesList) {
        this.caspEntidadesList = caspEntidadesList;
    }

    @XmlTransient
    public List<CaspSistemas> getCaspSistemasList() {
        return caspSistemasList;
    }

    public void setCaspSistemasList(List<CaspSistemas> caspSistemasList) {
        this.caspSistemasList = caspSistemasList;
    }

    @XmlTransient
    public List<CaspProblemas> getCaspProblemasList() {
        return caspProblemasList;
    }

    public void setCaspProblemasList(List<CaspProblemas> caspProblemasList) {
        this.caspProblemasList = caspProblemasList;
    }

    @XmlTransient
    public List<CaspUsuarios> getCaspUsuariosList() {
        return caspUsuariosList;
    }

    public void setCaspUsuariosList(List<CaspUsuarios> caspUsuariosList) {
        this.caspUsuariosList = caspUsuariosList;
    }

    public CaspNiveis getINivel() {
        return iNivel;
    }

    public void setINivel(CaspNiveis iNivel) {
        this.iNivel = iNivel;
    }

    public CaspOrigens getIOrigemChamado() {
        return iOrigemChamado;
    }

    public void setIOrigemChamado(CaspOrigens iOrigemChamado) {
        this.iOrigemChamado = iOrigemChamado;
    }

    public CaspPrioridades getIPrioridade() {
        return iPrioridade;
    }

    public void setIPrioridade(CaspPrioridades iPrioridade) {
        this.iPrioridade = iPrioridade;
    }

    public CaspSituacoes getISituacao() {
        return iSituacao;
    }

    public void setISituacao(CaspSituacoes iSituacao) {
        this.iSituacao = iSituacao;
    }

    public CaspUsuarios getIUsuarioAbertura() {
        return iUsuarioAbertura;
    }

    public void setIUsuarioAbertura(CaspUsuarios iUsuarioAbertura) {
        this.iUsuarioAbertura = iUsuarioAbertura;
    }

    public CaspUsuarios getIUsuarioAtendimento() {
        return iUsuarioAtendimento;
    }

    public void setIUsuarioAtendimento(CaspUsuarios iUsuarioAtendimento) {
        this.iUsuarioAtendimento = iUsuarioAtendimento;
    }

    public CaspUsuarios getIUsuarioEncaminhamento() {
        return iUsuarioEncaminhamento;
    }

    public void setIUsuarioEncaminhamento(CaspUsuarios iUsuarioEncaminhamento) {
        this.iUsuarioEncaminhamento = iUsuarioEncaminhamento;
    }

    public CaspUsuarios getIUsuarioEncerramento() {
        return iUsuarioEncerramento;
    }

    public void setIUsuarioEncerramento(CaspUsuarios iUsuarioEncerramento) {
        this.iUsuarioEncerramento = iUsuarioEncerramento;
    }

    @XmlTransient
    public List<CaspAnexos> getCaspAnexosList() {
        return caspAnexosList;
    }

    public void setCaspAnexosList(List<CaspAnexos> caspAnexosList) {
        this.caspAnexosList = caspAnexosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iChamado != null ? iChamado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspChamados)) {
            return false;
        }
        CaspChamados other = (CaspChamados) object;
        if ((this.iChamado == null && other.iChamado != null) || (this.iChamado != null && !this.iChamado.equals(other.iChamado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspChamados[ iChamado=" + iChamado + " ]";
    }
    
}
