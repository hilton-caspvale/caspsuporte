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
@Table(name = "ponto_percentuais",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoPercentuais.findAll", query = "SELECT p FROM PontoPercentuais p"),
    @NamedQuery(name = "PontoPercentuais.findByIPercentual", query = "SELECT p FROM PontoPercentuais p WHERE p.iPercentual = :iPercentual"),
    @NamedQuery(name = "PontoPercentuais.findByValor", query = "SELECT p FROM PontoPercentuais p WHERE p.valor = :valor"),
    @NamedQuery(name = "PontoPercentuais.findByDescricao", query = "SELECT p FROM PontoPercentuais p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "PontoPercentuais.findByHoraInicio", query = "SELECT p FROM PontoPercentuais p WHERE p.horaInicio = :horaInicio"),
    @NamedQuery(name = "PontoPercentuais.findByHoraFim", query = "SELECT p FROM PontoPercentuais p WHERE p.horaFim = :horaFim"),
    @NamedQuery(name = "PontoPercentuais.findByAtivo", query = "SELECT p FROM PontoPercentuais p WHERE p.ativo = :ativo")})
public class PontoPercentuais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_percentual")
    private Integer iPercentual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private int valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_inicio")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_fim")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime horaFim;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;

    public PontoPercentuais() {
    }

    public PontoPercentuais(Integer iPercentual) {
        this.iPercentual = iPercentual;
    }

    public PontoPercentuais(Integer iPercentual, int valor, String descricao, LocalDateTime horaInicio, LocalDateTime horaFim, boolean ativo) {
        this.iPercentual = iPercentual;
        this.valor = valor;
        this.descricao = descricao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.ativo = ativo;
    }

    public Integer getIPercentual() {
        return iPercentual;
    }

    public void setIPercentual(Integer iPercentual) {
        this.iPercentual = iPercentual;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iPercentual != null ? iPercentual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PontoPercentuais)) {
            return false;
        }
        PontoPercentuais other = (PontoPercentuais) object;
        if ((this.iPercentual == null && other.iPercentual != null) || (this.iPercentual != null && !this.iPercentual.equals(other.iPercentual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.PontoPercentuais[ iPercentual=" + iPercentual + " ]";
    }
    
}
