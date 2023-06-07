package caspvale.caspsuporte.atendimento.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "casp_anexos",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspAnexos.findAll", query = "SELECT c FROM CaspAnexos c"),
    @NamedQuery(name = "CaspAnexos.findByIAnexo", query = "SELECT c FROM CaspAnexos c WHERE c.iAnexo = :iAnexo"),
    @NamedQuery(name = "CaspAnexos.findByDiretorioArquivo", query = "SELECT c FROM CaspAnexos c WHERE c.diretorioArquivo = :diretorioArquivo"),
    @NamedQuery(name = "CaspAnexos.findByDescricaoArquivo", query = "SELECT c FROM CaspAnexos c WHERE c.descricaoArquivo = :descricaoArquivo"),
    @NamedQuery(name = "CaspAnexos.findByComentarioArquivo", query = "SELECT c FROM CaspAnexos c WHERE c.comentarioArquivo = :comentarioArquivo"),
    @NamedQuery(name = "CaspAnexos.findByDataArquivo", query = "SELECT c FROM CaspAnexos c WHERE c.dataArquivo = :dataArquivo"),
    @NamedQuery(name = "CaspAnexos.findBySituacaoArquivo", query = "SELECT c FROM CaspAnexos c WHERE c.situacaoArquivo = :situacaoArquivo"),
    @NamedQuery(name = "CaspAnexos.findByIArquivo", query = "SELECT c FROM CaspAnexos c WHERE c.iArquivo = :iArquivo")})
public class CaspAnexos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_anexo")
    private Integer iAnexo;
    @Lob
    @Column(name = "arquivo")
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] arquivo;
    @Size(max = 2147483647)
    @Column(name = "diretorio_arquivo")
    private String diretorioArquivo;
    @Size(max = 2147483647)
    @Column(name = "descricao_arquivo")
    private String descricaoArquivo;
    @Size(max = 2147483647)
    @Column(name = "comentario_arquivo")
    private String comentarioArquivo;
    @Column(name = "data_arquivo")
    //@Temporal(TemporalType.DATE)
    private LocalDateTime dataArquivo;
    @Size(max = 2147483647)
    @Column(name = "situacao_arquivo")
    private String situacaoArquivo;
    @Column(name = "i_arquivo")
    private Integer iArquivo;
    @JoinColumn(name = "i_chamado", referencedColumnName = "i_chamado")
    @ManyToOne
    private CaspChamados iChamado;
    @JoinColumn(name = "i_tipo_arquivo", referencedColumnName = "i_tipo_arquivo")
    @ManyToOne
    private CaspTiposArquivos iTipoArquivo;
    @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")
    @ManyToOne
    private CaspUsuarios iUsuario;

    public CaspAnexos() {
    }

    public CaspAnexos(Integer iAnexo) {
        this.iAnexo = iAnexo;
    }

    public Integer getIAnexo() {
        return iAnexo;
    }

    public void setIAnexo(Integer iAnexo) {
        this.iAnexo = iAnexo;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getDiretorioArquivo() {
        return diretorioArquivo;
    }

    public void setDiretorioArquivo(String diretorioArquivo) {
        this.diretorioArquivo = diretorioArquivo;
    }

    public String getDescricaoArquivo() {
        return descricaoArquivo;
    }

    public void setDescricaoArquivo(String descricaoArquivo) {
        this.descricaoArquivo = descricaoArquivo;
    }

    public String getComentarioArquivo() {
        return comentarioArquivo;
    }

    public void setComentarioArquivo(String comentarioArquivo) {
        this.comentarioArquivo = comentarioArquivo;
    }

    public LocalDateTime getDataArquivo() {
        return dataArquivo;
    }

    public void setDataArquivo(LocalDateTime dataArquivo) {
        this.dataArquivo = dataArquivo;
    }

    public String getSituacaoArquivo() {
        return situacaoArquivo;
    }

    public void setSituacaoArquivo(String situacaoArquivo) {
        this.situacaoArquivo = situacaoArquivo;
    }

    public Integer getIArquivo() {
        return iArquivo;
    }

    public void setIArquivo(Integer iArquivo) {
        this.iArquivo = iArquivo;
    }

    public CaspChamados getIChamado() {
        return iChamado;
    }

    public void setIChamado(CaspChamados iChamado) {
        this.iChamado = iChamado;
    }

    public CaspTiposArquivos getITipoArquivo() {
        return iTipoArquivo;
    }

    public void setITipoArquivo(CaspTiposArquivos iTipoArquivo) {
        this.iTipoArquivo = iTipoArquivo;
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
        hash += (iAnexo != null ? iAnexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspAnexos)) {
            return false;
        }
        CaspAnexos other = (CaspAnexos) object;
        if ((this.iAnexo == null && other.iAnexo != null) || (this.iAnexo != null && !this.iAnexo.equals(other.iAnexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspAnexos[ iAnexo=" + iAnexo + " ]";
    }
    
}
