/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.domain.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "ponto_intercorrencias",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoIntercorrencias.findAll", query = "SELECT p FROM PontoIntercorrencias p"),
    @NamedQuery(name = "PontoIntercorrencias.findByIIntercorrencia", query = "SELECT p FROM PontoIntercorrencias p WHERE p.iIntercorrencia = :iIntercorrencia"),
    @NamedQuery(name = "PontoIntercorrencias.findByHoraInformada", query = "SELECT p FROM PontoIntercorrencias p WHERE p.horaInformada = :horaInformada"),
    @NamedQuery(name = "PontoIntercorrencias.findByHoraEfetiva", query = "SELECT p FROM PontoIntercorrencias p WHERE p.horaEfetiva = :horaEfetiva"),
    @NamedQuery(name = "PontoIntercorrencias.findByTipoRegistro", query = "SELECT p FROM PontoIntercorrencias p WHERE p.tipoRegistro = :tipoRegistro"),
    @NamedQuery(name = "PontoIntercorrencias.findByHoraDiferenca", query = "SELECT p FROM PontoIntercorrencias p WHERE p.horaDiferenca = :horaDiferenca"),
    @NamedQuery(name = "PontoIntercorrencias.findByAutorizado", query = "SELECT p FROM PontoIntercorrencias p WHERE p.autorizado = :autorizado"),
    @NamedQuery(name = "PontoIntercorrencias.findBySituacao", query = "SELECT p FROM PontoIntercorrencias p WHERE p.situacao = :situacao"),
    @NamedQuery(name = "PontoIntercorrencias.findByEnviarEmail", query = "SELECT p FROM PontoIntercorrencias p WHERE p.enviarEmail = :enviarEmail"),
    @NamedQuery(name = "PontoIntercorrencias.findByDiferencaLong", query = "SELECT p FROM PontoIntercorrencias p WHERE p.diferencaLong = :diferencaLong"),
    @NamedQuery(name = "PontoIntercorrencias.findByMotivo", query = "SELECT p FROM PontoIntercorrencias p WHERE p.motivo = :motivo"),
    @NamedQuery(name = "PontoIntercorrencias.findByTipo", query = "SELECT p FROM PontoIntercorrencias p WHERE p.tipo = :tipo")})
public class PontoIntercorrencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_intercorrencia")
    private Integer iIntercorrencia;
    @Column(name = "hora_informada")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime horaInformada;
    @Column(name = "hora_efetiva")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime horaEfetiva;
    @Size(max = 2147483647)
    @Column(name = "tipo_registro")
    private String tipoRegistro;
    @Column(name = "hora_diferenca")
    //@Temporal(TemporalType.TIME)
    private LocalTime horaDiferenca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autorizado")
    private boolean autorizado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "situacao")
    private String situacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enviar_email")
    private boolean enviarEmail;
    @Column(name = "diferenca_long")
    private BigInteger diferencaLong;
    @Size(max = 2147483647)
    @Column(name = "motivo")
    private String motivo;
    @Size(max = 2147483647)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "i_horario", referencedColumnName = "i_horario")
    @ManyToOne(optional = false)
    private PontoHorarios iHorario;

    public PontoIntercorrencias() {
    }

    public PontoIntercorrencias(Integer iIntercorrencia) {
        this.iIntercorrencia = iIntercorrencia;
    }

    public PontoIntercorrencias(Integer iIntercorrencia, boolean autorizado, String situacao, boolean enviarEmail) {
        this.iIntercorrencia = iIntercorrencia;
        this.autorizado = autorizado;
        this.situacao = situacao;
        this.enviarEmail = enviarEmail;
    }

    public Integer getIIntercorrencia() {
        return iIntercorrencia;
    }

    public void setIIntercorrencia(Integer iIntercorrencia) {
        this.iIntercorrencia = iIntercorrencia;
    }

    public LocalDateTime getHoraInformada() {
        return horaInformada;
    }

    public void setHoraInformada(LocalDateTime horaInformada) {
        this.horaInformada = horaInformada;
    }

    public LocalDateTime getHoraEfetiva() {
        return horaEfetiva;
    }

    public void setHoraEfetiva(LocalDateTime horaEfetiva) {
        this.horaEfetiva = horaEfetiva;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public LocalTime getHoraDiferenca() {
        return horaDiferenca;
    }

    public void setHoraDiferenca(LocalTime horaDiferenca) {
        this.horaDiferenca = horaDiferenca;
    }

    public boolean getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public boolean getEnviarEmail() {
        return enviarEmail;
    }

    public void setEnviarEmail(boolean enviarEmail) {
        this.enviarEmail = enviarEmail;
    }

    public BigInteger getDiferencaLong() {
        return diferencaLong;
    }

    public void setDiferencaLong(BigInteger diferencaLong) {
        this.diferencaLong = diferencaLong;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public PontoHorarios getIHorario() {
        return iHorario;
    }

    public void setIHorario(PontoHorarios iHorario) {
        this.iHorario = iHorario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iIntercorrencia != null ? iIntercorrencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PontoIntercorrencias)) {
            return false;
        }
        PontoIntercorrencias other = (PontoIntercorrencias) object;
        if ((this.iIntercorrencia == null && other.iIntercorrencia != null) || (this.iIntercorrencia != null && !this.iIntercorrencia.equals(other.iIntercorrencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.PontoIntercorrencias[ iIntercorrencia=" + iIntercorrencia + " ]";
    }
    
}
