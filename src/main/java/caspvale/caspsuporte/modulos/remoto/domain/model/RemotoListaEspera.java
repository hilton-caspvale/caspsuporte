/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.remoto.domain.model;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspPrioridades;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "remoto_lista_espera",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RemotoListaEspera.findAll", query = "SELECT r FROM RemotoListaEspera r"),
    @NamedQuery(name = "RemotoListaEspera.findByILista", query = "SELECT r FROM RemotoListaEspera r WHERE r.iLista = :iLista"),
    @NamedQuery(name = "RemotoListaEspera.findByOrdem", query = "SELECT r FROM RemotoListaEspera r WHERE r.ordem = :ordem"),
    @NamedQuery(name = "RemotoListaEspera.findByDtAgendamento", query = "SELECT r FROM RemotoListaEspera r WHERE r.dtAgendamento = :dtAgendamento"),
    @NamedQuery(name = "RemotoListaEspera.findByTempoEstimado", query = "SELECT r FROM RemotoListaEspera r WHERE r.tempoEstimado = :tempoEstimado"),
    @NamedQuery(name = "RemotoListaEspera.findByComentario", query = "SELECT r FROM RemotoListaEspera r WHERE r.comentario = :comentario")})
public class RemotoListaEspera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_lista")
    private Integer iLista;
    @Column(name = "ordem")
    private Integer ordem;
    @Column(name = "dt_agendamento")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalTime dtAgendamento;
    @Column(name = "tempo_estimado")
    private BigInteger tempoEstimado;
    @Size(max = 2147483647)
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "i_prioridade", referencedColumnName = "i_prioridade")
    @ManyToOne(optional = false)
    private CaspPrioridades iPrioridade;
    @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")
    @ManyToOne(optional = false)
    private CaspUsuarios iUsuario;
    @JoinColumn(name = "i_contato", referencedColumnName = "i_contato")
    @ManyToOne(optional = false)
    private RemotoContatos iContato;

    public RemotoListaEspera() {
    }

    public RemotoListaEspera(Integer iLista) {
        this.iLista = iLista;
    }

    public Integer getILista() {
        return iLista;
    }

    public void setILista(Integer iLista) {
        this.iLista = iLista;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public LocalTime getDtAgendamento() {
        return dtAgendamento;
    }

    public void setDtAgendamento(LocalTime dtAgendamento) {
        this.dtAgendamento = dtAgendamento;
    }

    public BigInteger getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(BigInteger tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public CaspPrioridades getIPrioridade() {
        return iPrioridade;
    }

    public void setIPrioridade(CaspPrioridades iPrioridade) {
        this.iPrioridade = iPrioridade;
    }

    public CaspUsuarios getIUsuario() {
        return iUsuario;
    }

    public void setIUsuario(CaspUsuarios iUsuario) {
        this.iUsuario = iUsuario;
    }

    public RemotoContatos getIContato() {
        return iContato;
    }

    public void setIContato(RemotoContatos iContato) {
        this.iContato = iContato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iLista != null ? iLista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RemotoListaEspera)) {
            return false;
        }
        RemotoListaEspera other = (RemotoListaEspera) object;
        if ((this.iLista == null && other.iLista != null) || (this.iLista != null && !this.iLista.equals(other.iLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.RemotoListaEspera[ iLista=" + iLista + " ]";
    }
    
}
