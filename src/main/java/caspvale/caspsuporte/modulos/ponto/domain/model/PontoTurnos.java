/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.ponto.domain.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "ponto_turnos",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoTurnos.findAll", query = "SELECT p FROM PontoTurnos p"),
    @NamedQuery(name = "PontoTurnos.findByITurno", query = "SELECT p FROM PontoTurnos p WHERE p.iTurno = :iTurno"),
    @NamedQuery(name = "PontoTurnos.findByHoraEntrada", query = "SELECT p FROM PontoTurnos p WHERE p.horaEntrada = :horaEntrada"),
    @NamedQuery(name = "PontoTurnos.findByHoraAlmoco", query = "SELECT p FROM PontoTurnos p WHERE p.horaAlmoco = :horaAlmoco"),
    @NamedQuery(name = "PontoTurnos.findByHoraRetorno", query = "SELECT p FROM PontoTurnos p WHERE p.horaRetorno = :horaRetorno"),
    @NamedQuery(name = "PontoTurnos.findByHoraSaida", query = "SELECT p FROM PontoTurnos p WHERE p.horaSaida = :horaSaida"),
    @NamedQuery(name = "PontoTurnos.findByTotalHoras", query = "SELECT p FROM PontoTurnos p WHERE p.totalHoras = :totalHoras"),
    @NamedQuery(name = "PontoTurnos.findBySituacao", query = "SELECT p FROM PontoTurnos p WHERE p.situacao = :situacao"),
    @NamedQuery(name = "PontoTurnos.findByDescricao", query = "SELECT p FROM PontoTurnos p WHERE p.descricao = :descricao")})
public class PontoTurnos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_turno")
    private Integer iTurno;
    @Column(name = "hora_entrada")
    //@Temporal(TemporalType.TIME)
    private LocalTime horaEntrada;
    @Column(name = "hora_almoco")
    //@Temporal(TemporalType.TIME)
    private LocalTime horaAlmoco;
    @Column(name = "hora_retorno")
    //@Temporal(TemporalType.TIME)
    private LocalTime horaRetorno;
    @Column(name = "hora_saida")
    //@Temporal(TemporalType.TIME)
    private LocalTime horaSaida;
    @Column(name = "total_horas")
    //@Temporal(TemporalType.TIME)
    private LocalTime totalHoras;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "situacao")
    private String situacao;
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "iTurno")
    private List<PontoHorarios> pontoHorariosList;

    public PontoTurnos() {
    }

    public PontoTurnos(Integer iTurno) {
        this.iTurno = iTurno;
    }

    public PontoTurnos(Integer iTurno, String situacao) {
        this.iTurno = iTurno;
        this.situacao = situacao;
    }

    public Integer getITurno() {
        return iTurno;
    }

    public void setITurno(Integer iTurno) {
        this.iTurno = iTurno;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraAlmoco() {
        return horaAlmoco;
    }

    public void setHoraAlmoco(LocalTime horaAlmoco) {
        this.horaAlmoco = horaAlmoco;
    }

    public LocalTime getHoraRetorno() {
        return horaRetorno;
    }

    public void setHoraRetorno(LocalTime horaRetorno) {
        this.horaRetorno = horaRetorno;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public LocalTime getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(LocalTime totalHoras) {
        this.totalHoras = totalHoras;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<PontoHorarios> getPontoHorariosList() {
        return pontoHorariosList;
    }

    public void setPontoHorariosList(List<PontoHorarios> pontoHorariosList) {
        this.pontoHorariosList = pontoHorariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iTurno != null ? iTurno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PontoTurnos)) {
            return false;
        }
        PontoTurnos other = (PontoTurnos) object;
        if ((this.iTurno == null && other.iTurno != null) || (this.iTurno != null && !this.iTurno.equals(other.iTurno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.PontoTurnos[ iTurno=" + iTurno + " ]";
    }
    
}
