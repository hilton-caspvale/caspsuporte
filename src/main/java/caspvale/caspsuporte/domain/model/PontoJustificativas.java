/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.domain.model;

import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "ponto_justificativas",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoJustificativas.findAll", query = "SELECT p FROM PontoJustificativas p"),
    @NamedQuery(name = "PontoJustificativas.findByIJustificativa", query = "SELECT p FROM PontoJustificativas p WHERE p.iJustificativa = :iJustificativa"),
    @NamedQuery(name = "PontoJustificativas.findByDataInicial", query = "SELECT p FROM PontoJustificativas p WHERE p.dataInicial = :dataInicial"),
    @NamedQuery(name = "PontoJustificativas.findByDataFinal", query = "SELECT p FROM PontoJustificativas p WHERE p.dataFinal = :dataFinal"),
    @NamedQuery(name = "PontoJustificativas.findByTempo", query = "SELECT p FROM PontoJustificativas p WHERE p.tempo = :tempo"),
    @NamedQuery(name = "PontoJustificativas.findByDocumento", query = "SELECT p FROM PontoJustificativas p WHERE p.documento = :documento"),
    @NamedQuery(name = "PontoJustificativas.findByTipo", query = "SELECT p FROM PontoJustificativas p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PontoJustificativas.findByAbonar", query = "SELECT p FROM PontoJustificativas p WHERE p.abonar = :abonar"),
    @NamedQuery(name = "PontoJustificativas.findByDescricaoJustificativa", query = "SELECT p FROM PontoJustificativas p WHERE p.descricaoJustificativa = :descricaoJustificativa"),
    @NamedQuery(name = "PontoJustificativas.findBySituacao", query = "SELECT p FROM PontoJustificativas p WHERE p.situacao = :situacao"),
    @NamedQuery(name = "PontoJustificativas.findByParecer", query = "SELECT p FROM PontoJustificativas p WHERE p.parecer = :parecer"),
    @NamedQuery(name = "PontoJustificativas.findByHoraInicio", query = "SELECT p FROM PontoJustificativas p WHERE p.horaInicio = :horaInicio"),
    @NamedQuery(name = "PontoJustificativas.findByHoraFim", query = "SELECT p FROM PontoJustificativas p WHERE p.horaFim = :horaFim"),
    @NamedQuery(name = "PontoJustificativas.findByTempoLong", query = "SELECT p FROM PontoJustificativas p WHERE p.tempoLong = :tempoLong")})
public class PontoJustificativas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_justificativa")
    private Integer iJustificativa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_inicial")
    //@Temporal(TemporalType.DATE)
    private LocalDate dataInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_final")
    //@Temporal(TemporalType.DATE)
    private LocalDate dataFinal;
    @Column(name = "tempo")
    //@Temporal(TemporalType.TIME)
    private LocalTime tempo;
    @Size(max = 2147483647)
    @Column(name = "documento")
    private String documento;
    @Lob
    @Column(name = "arquivo")
    private byte[] arquivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abonar")
    private boolean abonar;
    @Size(max = 2147483647)
    @Column(name = "descricao_justificativa")
    private String descricaoJustificativa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "situacao")
    private String situacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "parecer")
    private String parecer;
    @Column(name = "hora_inicio")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime horaInicio;
    @Column(name = "hora_fim")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime horaFim;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tempo_long")
    private long tempoLong;
    @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")
    @ManyToOne(optional = false)
    private CaspUsuarios iUsuario;

    public PontoJustificativas() {
    }

    public PontoJustificativas(Integer iJustificativa) {
        this.iJustificativa = iJustificativa;
    }

    public PontoJustificativas(Integer iJustificativa, LocalDate dataInicial, LocalDate dataFinal, String tipo, boolean abonar, String situacao, String parecer, long tempoLong) {
        this.iJustificativa = iJustificativa;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.tipo = tipo;
        this.abonar = abonar;
        this.situacao = situacao;
        this.parecer = parecer;
        this.tempoLong = tempoLong;
    }

    public Integer getIJustificativa() {
        return iJustificativa;
    }

    public void setIJustificativa(Integer iJustificativa) {
        this.iJustificativa = iJustificativa;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public LocalTime getTempo() {
        return tempo;
    }

    public void setTempo(LocalTime tempo) {
        this.tempo = tempo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getAbonar() {
        return abonar;
    }

    public void setAbonar(boolean abonar) {
        this.abonar = abonar;
    }

    public String getDescricaoJustificativa() {
        return descricaoJustificativa;
    }

    public void setDescricaoJustificativa(String descricaoJustificativa) {
        this.descricaoJustificativa = descricaoJustificativa;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalDateTime horaFim) {
        this.horaFim = horaFim;
    }

    public long getTempoLong() {
        return tempoLong;
    }

    public void setTempoLong(long tempoLong) {
        this.tempoLong = tempoLong;
    }

    public CaspUsuarios getIUsuario() {
        return iUsuario;
    }

    public void setIUsuario(CaspUsuarios iUsuario) {
        this.iUsuario = iUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iJustificativa != null ? iJustificativa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PontoJustificativas)) {
            return false;
        }
        PontoJustificativas other = (PontoJustificativas) object;
        if ((this.iJustificativa == null && other.iJustificativa != null) || (this.iJustificativa != null && !this.iJustificativa.equals(other.iJustificativa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.PontoJustificativas[ iJustificativa=" + iJustificativa + " ]";
    }
    
}
