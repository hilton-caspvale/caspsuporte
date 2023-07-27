/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.remoto.domain.model;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "remoto_conexoes",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RemotoConexoes.findAll", query = "SELECT r FROM RemotoConexoes r"),
    @NamedQuery(name = "RemotoConexoes.findByIConexao", query = "SELECT r FROM RemotoConexoes r WHERE r.iConexao = :iConexao"),
    @NamedQuery(name = "RemotoConexoes.findByIContato", query = "SELECT r FROM RemotoConexoes r WHERE r.iContato = :iContato"),
    @NamedQuery(name = "RemotoConexoes.findByIChamado", query = "SELECT r FROM RemotoConexoes r WHERE r.iChamado = :iChamado"),
    @NamedQuery(name = "RemotoConexoes.findByIUsuario", query = "SELECT r FROM RemotoConexoes r WHERE r.iUsuario = :iUsuario"),
    @NamedQuery(name = "RemotoConexoes.findByDtInicio", query = "SELECT r FROM RemotoConexoes r WHERE r.dtInicio = :dtInicio"),
    @NamedQuery(name = "RemotoConexoes.findByDtFim", query = "SELECT r FROM RemotoConexoes r WHERE r.dtFim = :dtFim"),
    @NamedQuery(name = "RemotoConexoes.findByTempoEstimado", query = "SELECT r FROM RemotoConexoes r WHERE r.tempoEstimado = :tempoEstimado"),
    @NamedQuery(name = "RemotoConexoes.findByComentario", query = "SELECT r FROM RemotoConexoes r WHERE r.comentario = :comentario"),
    @NamedQuery(name = "RemotoConexoes.findByIEntidade", query = "SELECT r FROM RemotoConexoes r WHERE r.iEntidade = :iEntidade"),
    @NamedQuery(name = "RemotoConexoes.findByStatus", query = "SELECT r FROM RemotoConexoes r WHERE r.status = :status")})
public class RemotoConexoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_conexao")
    private Integer iConexao;
    @Column(name = "i_contato")
    private Integer iContato;
    @Column(name = "i_chamado")
    private Integer iChamado;
    @Column(name = "i_usuario")
    private Integer iUsuario;
    @Column(name = "dt_inicio")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dtInicio;
    @Column(name = "dt_fim")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dtFim;
    @Column(name = "tempo_estimado")
    private long tempoEstimado;
    @Size(max = 2147483647)
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "i_entidade")
    private Integer iEntidade;
    @Size(max = 2147483647)
    @Column(name = "status")
    private String status;

    public RemotoConexoes() {
    }

    public RemotoConexoes(Integer iConexao) {
        this.iConexao = iConexao;
    }

    public Integer getIConexao() {
        return iConexao;
    }

    public void setIConexao(Integer iConexao) {
        this.iConexao = iConexao;
    }

    public Integer getIContato() {
        return iContato;
    }

    public void setIContato(Integer iContato) {
        this.iContato = iContato;
    }

    public Integer getIChamado() {
        return iChamado;
    }

    public void setIChamado(Integer iChamado) {
        this.iChamado = iChamado;
    }

    public Integer getIUsuario() {
        return iUsuario;
    }

    public void setIUsuario(Integer iUsuario) {
        this.iUsuario = iUsuario;
    }

    public LocalDateTime getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDateTime dtInicio) {
        this.dtInicio = dtInicio;
    }

    public LocalDateTime getDtFim() {
        return dtFim;
    }

    public void setDtFim(LocalDateTime dtFim) {
        this.dtFim = dtFim;
    }

    public long getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(long tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getIEntidade() {
        return iEntidade;
    }

    public void setIEntidade(Integer iEntidade) {
        this.iEntidade = iEntidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iConexao != null ? iConexao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RemotoConexoes)) {
            return false;
        }
        RemotoConexoes other = (RemotoConexoes) object;
        if ((this.iConexao == null && other.iConexao != null) || (this.iConexao != null && !this.iConexao.equals(other.iConexao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.RemotoConexoes[ iConexao=" + iConexao + " ]";
    }
    
}
