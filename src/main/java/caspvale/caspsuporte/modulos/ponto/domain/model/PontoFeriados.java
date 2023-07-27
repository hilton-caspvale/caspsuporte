/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.ponto.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "ponto_feriados",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoFeriados.findAll", query = "SELECT p FROM PontoFeriados p"),
    @NamedQuery(name = "PontoFeriados.findByIFeriado", query = "SELECT p FROM PontoFeriados p WHERE p.iFeriado = :iFeriado"),
    @NamedQuery(name = "PontoFeriados.findByDescricao", query = "SELECT p FROM PontoFeriados p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "PontoFeriados.findByTipo", query = "SELECT p FROM PontoFeriados p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PontoFeriados.findByDia", query = "SELECT p FROM PontoFeriados p WHERE p.dia = :dia")})
public class PontoFeriados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_feriado")
    private Integer iFeriado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 2147483647)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia")
    //@Temporal(TemporalType.DATE)
    private LocalDate dia;

    public PontoFeriados() {
    }

    public PontoFeriados(Integer iFeriado) {
        this.iFeriado = iFeriado;
    }

    public PontoFeriados(Integer iFeriado, String descricao, LocalDate dia) {
        this.iFeriado = iFeriado;
        this.descricao = descricao;
        this.dia = dia;
    }

    public Integer getIFeriado() {
        return iFeriado;
    }

    public void setIFeriado(Integer iFeriado) {
        this.iFeriado = iFeriado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iFeriado != null ? iFeriado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PontoFeriados)) {
            return false;
        }
        PontoFeriados other = (PontoFeriados) object;
        if ((this.iFeriado == null && other.iFeriado != null) || (this.iFeriado != null && !this.iFeriado.equals(other.iFeriado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.PontoFeriados[ iFeriado=" + iFeriado + " ]";
    }
    
}
