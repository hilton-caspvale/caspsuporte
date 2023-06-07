/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.atendimento.domain.model;

import caspvale.caspsuporte.domain.model.RemotoContatos;
import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "casp_entidades",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspEntidades.findAll", query = "SELECT c FROM CaspEntidades c"),
    @NamedQuery(name = "CaspEntidades.findByIEntidade", query = "SELECT c FROM CaspEntidades c WHERE c.iEntidade = :iEntidade"),
    @NamedQuery(name = "CaspEntidades.findByNomeEntidade", query = "SELECT c FROM CaspEntidades c WHERE c.nomeEntidade = :nomeEntidade"),
    @NamedQuery(name = "CaspEntidades.findByCnpjEntidade", query = "SELECT c FROM CaspEntidades c WHERE c.cnpjEntidade = :cnpjEntidade"),
    @NamedQuery(name = "CaspEntidades.findByTelefoneEntidade", query = "SELECT c FROM CaspEntidades c WHERE c.telefoneEntidade = :telefoneEntidade"),
    @NamedQuery(name = "CaspEntidades.findByEmailEntidade", query = "SELECT c FROM CaspEntidades c WHERE c.emailEntidade = :emailEntidade"),
    @NamedQuery(name = "CaspEntidades.findByEnderecoEntidade", query = "SELECT c FROM CaspEntidades c WHERE c.enderecoEntidade = :enderecoEntidade"),
    @NamedQuery(name = "CaspEntidades.findByDadosServidor", query = "SELECT c FROM CaspEntidades c WHERE c.dadosServidor = :dadosServidor"),
    @NamedQuery(name = "CaspEntidades.findByComentarioEntidade", query = "SELECT c FROM CaspEntidades c WHERE c.comentarioEntidade = :comentarioEntidade"),
    @NamedQuery(name = "CaspEntidades.findBySituacaoEntidade", query = "SELECT c FROM CaspEntidades c WHERE c.situacaoEntidade = :situacaoEntidade")})
public class CaspEntidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_entidade")
    private Integer iEntidade;
    @Size(max = 2147483647)
    @Column(name = "nome_entidade")
    private String nomeEntidade;
    @Size(max = 2147483647)
    @Column(name = "cnpj_entidade")
    private String cnpjEntidade;
    @Size(max = 2147483647)
    @Column(name = "telefone_entidade")
    private String telefoneEntidade;
    @Size(max = 2147483647)
    @Column(name = "email_entidade")
    private String emailEntidade;
    @Size(max = 2147483647)
    @Column(name = "endereco_entidade")
    private String enderecoEntidade;
    @Size(max = 2147483647)
    @Column(name = "dados_servidor")
    private String dadosServidor;
    @Size(max = 2147483647)
    @Column(name = "comentario_entidade")
    private String comentarioEntidade;
    @Size(max = 1)
    @Column(name = "situacao_entidade")
    private String situacaoEntidade;
    @ManyToMany(mappedBy = "caspEntidadesList")
    private List<CaspChamados> caspChamadosList;
    @JoinTable(name = "casp_usuarios_entidades",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_entidade", referencedColumnName = "i_entidade")}, inverseJoinColumns = {
        @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")})
    @ManyToMany
    private List<CaspUsuarios> caspUsuariosList;
    @OneToMany(mappedBy = "iEntidade")
    private List<RemotoContatos> remotoContatosList;
    @OneToMany(mappedBy = "iEntidade")
    private List<CaspUsuariosConfig> caspUsuariosConfigList;
    @JoinColumn(name = "i_tipo_entidade", referencedColumnName = "i_tipos_entidade")
    @ManyToOne(optional = false)
    private CaspTiposEntidades iTipoEntidade;

    public CaspEntidades() {
    }

    public CaspEntidades(Integer iEntidade) {
        this.iEntidade = iEntidade;
    }

    public Integer getIEntidade() {
        return iEntidade;
    }

    public void setIEntidade(Integer iEntidade) {
        this.iEntidade = iEntidade;
    }

    public String getNomeEntidade() {
        return nomeEntidade;
    }

    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }

    public String getCnpjEntidade() {
        return cnpjEntidade;
    }

    public void setCnpjEntidade(String cnpjEntidade) {
        this.cnpjEntidade = cnpjEntidade;
    }

    public String getTelefoneEntidade() {
        return telefoneEntidade;
    }

    public void setTelefoneEntidade(String telefoneEntidade) {
        this.telefoneEntidade = telefoneEntidade;
    }

    public String getEmailEntidade() {
        return emailEntidade;
    }

    public void setEmailEntidade(String emailEntidade) {
        this.emailEntidade = emailEntidade;
    }

    public String getEnderecoEntidade() {
        return enderecoEntidade;
    }

    public void setEnderecoEntidade(String enderecoEntidade) {
        this.enderecoEntidade = enderecoEntidade;
    }

    public String getDadosServidor() {
        return dadosServidor;
    }

    public void setDadosServidor(String dadosServidor) {
        this.dadosServidor = dadosServidor;
    }

    public String getComentarioEntidade() {
        return comentarioEntidade;
    }

    public void setComentarioEntidade(String comentarioEntidade) {
        this.comentarioEntidade = comentarioEntidade;
    }

    public String getSituacaoEntidade() {
        return situacaoEntidade;
    }

    public void setSituacaoEntidade(String situacaoEntidade) {
        this.situacaoEntidade = situacaoEntidade;
    }

    @XmlTransient
    public List<CaspChamados> getCaspChamadosList() {
        return caspChamadosList;
    }

    public void setCaspChamadosList(List<CaspChamados> caspChamadosList) {
        this.caspChamadosList = caspChamadosList;
    }

    @XmlTransient
    public List<CaspUsuarios> getCaspUsuariosList() {
        return caspUsuariosList;
    }

    public void setCaspUsuariosList(List<CaspUsuarios> caspUsuariosList) {
        this.caspUsuariosList = caspUsuariosList;
    }

    @XmlTransient
    public List<RemotoContatos> getRemotoContatosList() {
        return remotoContatosList;
    }

    public void setRemotoContatosList(List<RemotoContatos> remotoContatosList) {
        this.remotoContatosList = remotoContatosList;
    }

    @XmlTransient
    public List<CaspUsuariosConfig> getCaspUsuariosConfigList() {
        return caspUsuariosConfigList;
    }

    public void setCaspUsuariosConfigList(List<CaspUsuariosConfig> caspUsuariosConfigList) {
        this.caspUsuariosConfigList = caspUsuariosConfigList;
    }

    public CaspTiposEntidades getITipoEntidade() {
        return iTipoEntidade;
    }

    public void setITipoEntidade(CaspTiposEntidades iTipoEntidade) {
        this.iTipoEntidade = iTipoEntidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iEntidade != null ? iEntidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspEntidades)) {
            return false;
        }
        CaspEntidades other = (CaspEntidades) object;
        if ((this.iEntidade == null && other.iEntidade != null) || (this.iEntidade != null && !this.iEntidade.equals(other.iEntidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  iEntidade.toString();
    }
    
}
