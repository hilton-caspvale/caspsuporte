/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.ponto.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "ponto_periodos",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoPeriodos.findAll", query = "SELECT p FROM PontoPeriodos p"),
    @NamedQuery(name = "PontoPeriodos.findByIPeriodo", query = "SELECT p FROM PontoPeriodos p WHERE p.iPeriodo = :iPeriodo"),
    @NamedQuery(name = "PontoPeriodos.findByDatainicial", query = "SELECT p FROM PontoPeriodos p WHERE p.datainicial = :datainicial"),
    @NamedQuery(name = "PontoPeriodos.findByDatafinal", query = "SELECT p FROM PontoPeriodos p WHERE p.datafinal = :datafinal"),
    @NamedQuery(name = "PontoPeriodos.findByMesAno", query = "SELECT p FROM PontoPeriodos p WHERE p.mesAno = :mesAno"),
    @NamedQuery(name = "PontoPeriodos.findByEncerrado", query = "SELECT p FROM PontoPeriodos p WHERE p.encerrado = :encerrado"),
    @NamedQuery(name = "PontoPeriodos.findBySituacao", query = "SELECT p FROM PontoPeriodos p WHERE p.situacao = :situacao")})
public class PontoPeriodos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_periodo")
    private Integer iPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datainicial")
    //@Temporal(TemporalType.DATE)
    private LocalDate datainicial;
    @Column(name = "datafinal")
    //@Temporal(TemporalType.DATE)
    private LocalDate datafinal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "mes_ano")
    private String mesAno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "encerrado")
    private boolean encerrado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "situacao")
    private String situacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iPeriodo")
    private List<PontoEncerramentos> pontoEncerramentosList;
    @OneToMany(mappedBy = "iPeriodo")
    private List<PontoHorarios> pontoHorariosList;

    public PontoPeriodos() {
    }

    public PontoPeriodos(Integer iPeriodo) {
        this.iPeriodo = iPeriodo;
    }

    public PontoPeriodos(Integer iPeriodo, LocalDate datainicial, String mesAno, boolean encerrado, String situacao) {
        this.iPeriodo = iPeriodo;
        this.datainicial = datainicial;
        this.mesAno = mesAno;
        this.encerrado = encerrado;
        this.situacao = situacao;
    }

    public Integer getIPeriodo() {
        return iPeriodo;
    }

    public void setIPeriodo(Integer iPeriodo) {
        this.iPeriodo = iPeriodo;
    }

    public LocalDate getDatainicial() {
        return datainicial;
    }

    public void setDatainicial(LocalDate datainicial) {
        this.datainicial = datainicial;
    }

    public LocalDate getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(LocalDate datafinal) {
        this.datafinal = datafinal;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public boolean getEncerrado() {
        return encerrado;
    }

    public void setEncerrado(boolean encerrado) {
        this.encerrado = encerrado;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @XmlTransient
    public List<PontoEncerramentos> getPontoEncerramentosList() {
        return pontoEncerramentosList;
    }

    public void setPontoEncerramentosList(List<PontoEncerramentos> pontoEncerramentosList) {
        this.pontoEncerramentosList = pontoEncerramentosList;
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
        hash += (iPeriodo != null ? iPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PontoPeriodos)) {
            return false;
        }
        PontoPeriodos other = (PontoPeriodos) object;
        if ((this.iPeriodo == null && other.iPeriodo != null) || (this.iPeriodo != null && !this.iPeriodo.equals(other.iPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.PontoPeriodos[ iPeriodo=" + iPeriodo + " ]";
    }
    
}
