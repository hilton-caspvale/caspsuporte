/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.ponto.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ponto_config_motivos",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoConfigMotivos.findAll", query = "SELECT p FROM PontoConfigMotivos p"),
    @NamedQuery(name = "PontoConfigMotivos.findByIMotivo", query = "SELECT p FROM PontoConfigMotivos p WHERE p.iMotivo = :iMotivo"),
    @NamedQuery(name = "PontoConfigMotivos.findByMsgAtraso", query = "SELECT p FROM PontoConfigMotivos p WHERE p.msgAtraso = :msgAtraso"),
    @NamedQuery(name = "PontoConfigMotivos.findByMsgAdicional", query = "SELECT p FROM PontoConfigMotivos p WHERE p.msgAdicional = :msgAdicional"),
    @NamedQuery(name = "PontoConfigMotivos.findByMsgForaHorario", query = "SELECT p FROM PontoConfigMotivos p WHERE p.msgForaHorario = :msgForaHorario"),
    @NamedQuery(name = "PontoConfigMotivos.findByMsgHorarioPosterior", query = "SELECT p FROM PontoConfigMotivos p WHERE p.msgHorarioPosterior = :msgHorarioPosterior"),
    @NamedQuery(name = "PontoConfigMotivos.findByGeraInterAtraso", query = "SELECT p FROM PontoConfigMotivos p WHERE p.geraInterAtraso = :geraInterAtraso"),
    @NamedQuery(name = "PontoConfigMotivos.findByAtrasoAutorizado", query = "SELECT p FROM PontoConfigMotivos p WHERE p.atrasoAutorizado = :atrasoAutorizado"),
    @NamedQuery(name = "PontoConfigMotivos.findByGeraInterAdicional", query = "SELECT p FROM PontoConfigMotivos p WHERE p.geraInterAdicional = :geraInterAdicional"),
    @NamedQuery(name = "PontoConfigMotivos.findByAdicionalAutorizado", query = "SELECT p FROM PontoConfigMotivos p WHERE p.adicionalAutorizado = :adicionalAutorizado"),
    @NamedQuery(name = "PontoConfigMotivos.findByGeraInterForaHorario", query = "SELECT p FROM PontoConfigMotivos p WHERE p.geraInterForaHorario = :geraInterForaHorario"),
    @NamedQuery(name = "PontoConfigMotivos.findByForaHorarioAutorizado", query = "SELECT p FROM PontoConfigMotivos p WHERE p.foraHorarioAutorizado = :foraHorarioAutorizado"),
    @NamedQuery(name = "PontoConfigMotivos.findByGeraInterHorarioPosterior", query = "SELECT p FROM PontoConfigMotivos p WHERE p.geraInterHorarioPosterior = :geraInterHorarioPosterior"),
    @NamedQuery(name = "PontoConfigMotivos.findByHorarioPosteriorAutorizado", query = "SELECT p FROM PontoConfigMotivos p WHERE p.horarioPosteriorAutorizado = :horarioPosteriorAutorizado"),
    @NamedQuery(name = "PontoConfigMotivos.findByGeraInterTurno", query = "SELECT p FROM PontoConfigMotivos p WHERE p.geraInterTurno = :geraInterTurno"),
    @NamedQuery(name = "PontoConfigMotivos.findByInterTurnoAutorizado", query = "SELECT p FROM PontoConfigMotivos p WHERE p.interTurnoAutorizado = :interTurnoAutorizado"),
    @NamedQuery(name = "PontoConfigMotivos.findByGeraInterIntervalo", query = "SELECT p FROM PontoConfigMotivos p WHERE p.geraInterIntervalo = :geraInterIntervalo"),
    @NamedQuery(name = "PontoConfigMotivos.findByInterIntervaloAutorizado", query = "SELECT p FROM PontoConfigMotivos p WHERE p.interIntervaloAutorizado = :interIntervaloAutorizado"),
    @NamedQuery(name = "PontoConfigMotivos.findByUsuarioAlteracao", query = "SELECT p FROM PontoConfigMotivos p WHERE p.usuarioAlteracao = :usuarioAlteracao"),
    @NamedQuery(name = "PontoConfigMotivos.findByDtAlteracao", query = "SELECT p FROM PontoConfigMotivos p WHERE p.dtAlteracao = :dtAlteracao")})
public class PontoConfigMotivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_motivo")
    private Integer iMotivo;
    @Size(max = 2147483647)
    @Column(name = "msg_atraso")
    private String msgAtraso;
    @Size(max = 2147483647)
    @Column(name = "msg_adicional")
    private String msgAdicional;
    @Size(max = 2147483647)
    @Column(name = "msg_fora_horario")
    private String msgForaHorario;
    @Size(max = 2147483647)
    @Column(name = "msg_horario_posterior")
    private String msgHorarioPosterior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gera_inter_atraso")
    private boolean geraInterAtraso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "atraso_autorizado")
    private boolean atrasoAutorizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gera_inter_adicional")
    private boolean geraInterAdicional;
    @Basic(optional = false)
    @NotNull
    @Column(name = "adicional_autorizado")
    private boolean adicionalAutorizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gera_inter_fora_horario")
    private boolean geraInterForaHorario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fora_horario_autorizado")
    private boolean foraHorarioAutorizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gera_inter_horario_posterior")
    private boolean geraInterHorarioPosterior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_posterior_autorizado")
    private boolean horarioPosteriorAutorizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gera_inter_turno")
    private boolean geraInterTurno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inter_turno_autorizado")
    private boolean interTurnoAutorizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gera_inter_intervalo")
    private boolean geraInterIntervalo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inter_intervalo_autorizado")
    private boolean interIntervaloAutorizado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "usuario_alteracao")
    private String usuarioAlteracao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_alteracao")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dtAlteracao;

    public PontoConfigMotivos() {
    }

    public PontoConfigMotivos(Integer iMotivo) {
        this.iMotivo = iMotivo;
    }

    public PontoConfigMotivos(Integer iMotivo, boolean geraInterAtraso, boolean atrasoAutorizado, boolean geraInterAdicional, boolean adicionalAutorizado, boolean geraInterForaHorario, boolean foraHorarioAutorizado, boolean geraInterHorarioPosterior, boolean horarioPosteriorAutorizado, boolean geraInterTurno, boolean interTurnoAutorizado, boolean geraInterIntervalo, boolean interIntervaloAutorizado, String usuarioAlteracao, LocalDateTime dtAlteracao) {
        this.iMotivo = iMotivo;
        this.geraInterAtraso = geraInterAtraso;
        this.atrasoAutorizado = atrasoAutorizado;
        this.geraInterAdicional = geraInterAdicional;
        this.adicionalAutorizado = adicionalAutorizado;
        this.geraInterForaHorario = geraInterForaHorario;
        this.foraHorarioAutorizado = foraHorarioAutorizado;
        this.geraInterHorarioPosterior = geraInterHorarioPosterior;
        this.horarioPosteriorAutorizado = horarioPosteriorAutorizado;
        this.geraInterTurno = geraInterTurno;
        this.interTurnoAutorizado = interTurnoAutorizado;
        this.geraInterIntervalo = geraInterIntervalo;
        this.interIntervaloAutorizado = interIntervaloAutorizado;
        this.usuarioAlteracao = usuarioAlteracao;
        this.dtAlteracao = dtAlteracao;
    }

    public Integer getIMotivo() {
        return iMotivo;
    }

    public void setIMotivo(Integer iMotivo) {
        this.iMotivo = iMotivo;
    }

    public String getMsgAtraso() {
        return msgAtraso;
    }

    public void setMsgAtraso(String msgAtraso) {
        this.msgAtraso = msgAtraso;
    }

    public String getMsgAdicional() {
        return msgAdicional;
    }

    public void setMsgAdicional(String msgAdicional) {
        this.msgAdicional = msgAdicional;
    }

    public String getMsgForaHorario() {
        return msgForaHorario;
    }

    public void setMsgForaHorario(String msgForaHorario) {
        this.msgForaHorario = msgForaHorario;
    }

    public String getMsgHorarioPosterior() {
        return msgHorarioPosterior;
    }

    public void setMsgHorarioPosterior(String msgHorarioPosterior) {
        this.msgHorarioPosterior = msgHorarioPosterior;
    }

    public boolean getGeraInterAtraso() {
        return geraInterAtraso;
    }

    public void setGeraInterAtraso(boolean geraInterAtraso) {
        this.geraInterAtraso = geraInterAtraso;
    }

    public boolean getAtrasoAutorizado() {
        return atrasoAutorizado;
    }

    public void setAtrasoAutorizado(boolean atrasoAutorizado) {
        this.atrasoAutorizado = atrasoAutorizado;
    }

    public boolean getGeraInterAdicional() {
        return geraInterAdicional;
    }

    public void setGeraInterAdicional(boolean geraInterAdicional) {
        this.geraInterAdicional = geraInterAdicional;
    }

    public boolean getAdicionalAutorizado() {
        return adicionalAutorizado;
    }

    public void setAdicionalAutorizado(boolean adicionalAutorizado) {
        this.adicionalAutorizado = adicionalAutorizado;
    }

    public boolean getGeraInterForaHorario() {
        return geraInterForaHorario;
    }

    public void setGeraInterForaHorario(boolean geraInterForaHorario) {
        this.geraInterForaHorario = geraInterForaHorario;
    }

    public boolean getForaHorarioAutorizado() {
        return foraHorarioAutorizado;
    }

    public void setForaHorarioAutorizado(boolean foraHorarioAutorizado) {
        this.foraHorarioAutorizado = foraHorarioAutorizado;
    }

    public boolean getGeraInterHorarioPosterior() {
        return geraInterHorarioPosterior;
    }

    public void setGeraInterHorarioPosterior(boolean geraInterHorarioPosterior) {
        this.geraInterHorarioPosterior = geraInterHorarioPosterior;
    }

    public boolean getHorarioPosteriorAutorizado() {
        return horarioPosteriorAutorizado;
    }

    public void setHorarioPosteriorAutorizado(boolean horarioPosteriorAutorizado) {
        this.horarioPosteriorAutorizado = horarioPosteriorAutorizado;
    }

    public boolean getGeraInterTurno() {
        return geraInterTurno;
    }

    public void setGeraInterTurno(boolean geraInterTurno) {
        this.geraInterTurno = geraInterTurno;
    }

    public boolean getInterTurnoAutorizado() {
        return interTurnoAutorizado;
    }

    public void setInterTurnoAutorizado(boolean interTurnoAutorizado) {
        this.interTurnoAutorizado = interTurnoAutorizado;
    }

    public boolean getGeraInterIntervalo() {
        return geraInterIntervalo;
    }

    public void setGeraInterIntervalo(boolean geraInterIntervalo) {
        this.geraInterIntervalo = geraInterIntervalo;
    }

    public boolean getInterIntervaloAutorizado() {
        return interIntervaloAutorizado;
    }

    public void setInterIntervaloAutorizado(boolean interIntervaloAutorizado) {
        this.interIntervaloAutorizado = interIntervaloAutorizado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iMotivo != null ? iMotivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PontoConfigMotivos)) {
            return false;
        }
        PontoConfigMotivos other = (PontoConfigMotivos) object;
        if ((this.iMotivo == null && other.iMotivo != null) || (this.iMotivo != null && !this.iMotivo.equals(other.iMotivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.PontoConfigMotivos[ iMotivo=" + iMotivo + " ]";
    }
    
}
