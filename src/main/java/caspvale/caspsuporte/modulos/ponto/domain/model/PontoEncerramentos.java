/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.ponto.domain.model;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "ponto_encerramentos",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoEncerramentos.findAll", query = "SELECT p FROM PontoEncerramentos p"),
    @NamedQuery(name = "PontoEncerramentos.findByIEncerramento", query = "SELECT p FROM PontoEncerramentos p WHERE p.iEncerramento = :iEncerramento"),
    @NamedQuery(name = "PontoEncerramentos.findBySituacao", query = "SELECT p FROM PontoEncerramentos p WHERE p.situacao = :situacao"),
    @NamedQuery(name = "PontoEncerramentos.findByAutorizarReajuste", query = "SELECT p FROM PontoEncerramentos p WHERE p.autorizarReajuste = :autorizarReajuste"),
    @NamedQuery(name = "PontoEncerramentos.findByQuantidadeDias", query = "SELECT p FROM PontoEncerramentos p WHERE p.quantidadeDias = :quantidadeDias"),
    @NamedQuery(name = "PontoEncerramentos.findByDiasACompensar", query = "SELECT p FROM PontoEncerramentos p WHERE p.diasACompensar = :diasACompensar"),
    @NamedQuery(name = "PontoEncerramentos.findByDsr", query = "SELECT p FROM PontoEncerramentos p WHERE p.dsr = :dsr"),
    @NamedQuery(name = "PontoEncerramentos.findByDescontos", query = "SELECT p FROM PontoEncerramentos p WHERE p.descontos = :descontos"),
    @NamedQuery(name = "PontoEncerramentos.findByAcrescimosNormais", query = "SELECT p FROM PontoEncerramentos p WHERE p.acrescimosNormais = :acrescimosNormais"),
    @NamedQuery(name = "PontoEncerramentos.findByAcrescimosAdicionais", query = "SELECT p FROM PontoEncerramentos p WHERE p.acrescimosAdicionais = :acrescimosAdicionais"),
    @NamedQuery(name = "PontoEncerramentos.findByHorasNaoTrabalhadas", query = "SELECT p FROM PontoEncerramentos p WHERE p.horasNaoTrabalhadas = :horasNaoTrabalhadas"),
    @NamedQuery(name = "PontoEncerramentos.findByHorasAbonadas", query = "SELECT p FROM PontoEncerramentos p WHERE p.horasAbonadas = :horasAbonadas"),
    @NamedQuery(name = "PontoEncerramentos.findByHorasTrabalhadas", query = "SELECT p FROM PontoEncerramentos p WHERE p.horasTrabalhadas = :horasTrabalhadas"),
    @NamedQuery(name = "PontoEncerramentos.findByHorasTotais", query = "SELECT p FROM PontoEncerramentos p WHERE p.horasTotais = :horasTotais"),
    @NamedQuery(name = "PontoEncerramentos.findByDiasSemRegistro", query = "SELECT p FROM PontoEncerramentos p WHERE p.diasSemRegistro = :diasSemRegistro"),
    @NamedQuery(name = "PontoEncerramentos.findByRegistrosInconsistentes", query = "SELECT p FROM PontoEncerramentos p WHERE p.registrosInconsistentes = :registrosInconsistentes"),
    @NamedQuery(name = "PontoEncerramentos.findByUsuarioAlteracao", query = "SELECT p FROM PontoEncerramentos p WHERE p.usuarioAlteracao = :usuarioAlteracao"),
    @NamedQuery(name = "PontoEncerramentos.findByDtalteracao", query = "SELECT p FROM PontoEncerramentos p WHERE p.dtalteracao = :dtalteracao"),
    @NamedQuery(name = "PontoEncerramentos.findByDescricao1", query = "SELECT p FROM PontoEncerramentos p WHERE p.descricao1 = :descricao1"),
    @NamedQuery(name = "PontoEncerramentos.findByDescricao2", query = "SELECT p FROM PontoEncerramentos p WHERE p.descricao2 = :descricao2"),
    @NamedQuery(name = "PontoEncerramentos.findByDescricao3", query = "SELECT p FROM PontoEncerramentos p WHERE p.descricao3 = :descricao3"),
    @NamedQuery(name = "PontoEncerramentos.findByDiasTrabalhados", query = "SELECT p FROM PontoEncerramentos p WHERE p.diasTrabalhados = :diasTrabalhados"),
    @NamedQuery(name = "PontoEncerramentos.findByNomeArquivoGerado", query = "SELECT p FROM PontoEncerramentos p WHERE p.nomeArquivoGerado = :nomeArquivoGerado"),
    @NamedQuery(name = "PontoEncerramentos.findByNomeArquivoCorrigido", query = "SELECT p FROM PontoEncerramentos p WHERE p.nomeArquivoCorrigido = :nomeArquivoCorrigido")})
public class PontoEncerramentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_encerramento")
    private Integer iEncerramento;
    @Size(max = 2147483647)
    @Column(name = "situacao")
    private String situacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autorizar_reajuste")
    private boolean autorizarReajuste;
    @Column(name = "quantidade_dias")
    private Integer quantidadeDias;
    @Column(name = "dias_a_compensar")
    private Integer diasACompensar;
    @Size(max = 2147483647)
    @Column(name = "dsr")
    private String dsr;
    @Size(max = 2147483647)
    @Column(name = "descontos")
    private String descontos;
    @Size(max = 2147483647)
    @Column(name = "acrescimos_normais")
    private String acrescimosNormais;
    @Size(max = 2147483647)
    @Column(name = "acrescimos_adicionais")
    private String acrescimosAdicionais;
    @Size(max = 2147483647)
    @Column(name = "horas_nao_trabalhadas")
    private String horasNaoTrabalhadas;
    @Size(max = 2147483647)
    @Column(name = "horas_abonadas")
    private String horasAbonadas;
    @Size(max = 2147483647)
    @Column(name = "horas_trabalhadas")
    private String horasTrabalhadas;
    @Size(max = 2147483647)
    @Column(name = "horas_totais")
    private String horasTotais;
    @Size(max = 2147483647)
    @Column(name = "dias_sem_registro")
    private String diasSemRegistro;
    @Size(max = 2147483647)
    @Column(name = "registros_inconsistentes")
    private String registrosInconsistentes;
    @Lob
    @Column(name = "arquivo_gerado")
    private byte[] arquivoGerado;
    @Lob
    @Column(name = "arquivo_corrigido")
    private byte[] arquivoCorrigido;
    @Size(max = 2147483647)
    @Column(name = "usuario_alteracao")
    private String usuarioAlteracao;
    @Column(name = "dtalteracao")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dtalteracao;
    @Size(max = 2147483647)
    @Column(name = "descricao1")
    private String descricao1;
    @Size(max = 2147483647)
    @Column(name = "descricao2")
    private String descricao2;
    @Size(max = 2147483647)
    @Column(name = "descricao3")
    private String descricao3;
    @Column(name = "dias_trabalhados")
    private Integer diasTrabalhados;
    @Size(max = 2147483647)
    @Column(name = "nome_arquivo_gerado")
    private String nomeArquivoGerado;
    @Size(max = 2147483647)
    @Column(name = "nome_arquivo_corrigido")
    private String nomeArquivoCorrigido;
    @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")
    @ManyToOne(optional = false)
    private CaspUsuarios iUsuario;
    @JoinColumn(name = "i_periodo", referencedColumnName = "i_periodo")
    @ManyToOne(optional = false)
    private PontoPeriodos iPeriodo;

    public PontoEncerramentos() {
    }

    public PontoEncerramentos(Integer iEncerramento) {
        this.iEncerramento = iEncerramento;
    }

    public PontoEncerramentos(Integer iEncerramento, boolean autorizarReajuste) {
        this.iEncerramento = iEncerramento;
        this.autorizarReajuste = autorizarReajuste;
    }

    public Integer getIEncerramento() {
        return iEncerramento;
    }

    public void setIEncerramento(Integer iEncerramento) {
        this.iEncerramento = iEncerramento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public boolean getAutorizarReajuste() {
        return autorizarReajuste;
    }

    public void setAutorizarReajuste(boolean autorizarReajuste) {
        this.autorizarReajuste = autorizarReajuste;
    }

    public Integer getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(Integer quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public Integer getDiasACompensar() {
        return diasACompensar;
    }

    public void setDiasACompensar(Integer diasACompensar) {
        this.diasACompensar = diasACompensar;
    }

    public String getDsr() {
        return dsr;
    }

    public void setDsr(String dsr) {
        this.dsr = dsr;
    }

    public String getDescontos() {
        return descontos;
    }

    public void setDescontos(String descontos) {
        this.descontos = descontos;
    }

    public String getAcrescimosNormais() {
        return acrescimosNormais;
    }

    public void setAcrescimosNormais(String acrescimosNormais) {
        this.acrescimosNormais = acrescimosNormais;
    }

    public String getAcrescimosAdicionais() {
        return acrescimosAdicionais;
    }

    public void setAcrescimosAdicionais(String acrescimosAdicionais) {
        this.acrescimosAdicionais = acrescimosAdicionais;
    }

    public String getHorasNaoTrabalhadas() {
        return horasNaoTrabalhadas;
    }

    public void setHorasNaoTrabalhadas(String horasNaoTrabalhadas) {
        this.horasNaoTrabalhadas = horasNaoTrabalhadas;
    }

    public String getHorasAbonadas() {
        return horasAbonadas;
    }

    public void setHorasAbonadas(String horasAbonadas) {
        this.horasAbonadas = horasAbonadas;
    }

    public String getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(String horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public String getHorasTotais() {
        return horasTotais;
    }

    public void setHorasTotais(String horasTotais) {
        this.horasTotais = horasTotais;
    }

    public String getDiasSemRegistro() {
        return diasSemRegistro;
    }

    public void setDiasSemRegistro(String diasSemRegistro) {
        this.diasSemRegistro = diasSemRegistro;
    }

    public String getRegistrosInconsistentes() {
        return registrosInconsistentes;
    }

    public void setRegistrosInconsistentes(String registrosInconsistentes) {
        this.registrosInconsistentes = registrosInconsistentes;
    }

    public byte[] getArquivoGerado() {
        return arquivoGerado;
    }

    public void setArquivoGerado(byte[] arquivoGerado) {
        this.arquivoGerado = arquivoGerado;
    }

    public byte[] getArquivoCorrigido() {
        return arquivoCorrigido;
    }

    public void setArquivoCorrigido(byte[] arquivoCorrigido) {
        this.arquivoCorrigido = arquivoCorrigido;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public LocalDateTime getDtalteracao() {
        return dtalteracao;
    }

    public void setDtalteracao(LocalDateTime dtalteracao) {
        this.dtalteracao = dtalteracao;
    }

    public String getDescricao1() {
        return descricao1;
    }

    public void setDescricao1(String descricao1) {
        this.descricao1 = descricao1;
    }

    public String getDescricao2() {
        return descricao2;
    }

    public void setDescricao2(String descricao2) {
        this.descricao2 = descricao2;
    }

    public String getDescricao3() {
        return descricao3;
    }

    public void setDescricao3(String descricao3) {
        this.descricao3 = descricao3;
    }

    public Integer getDiasTrabalhados() {
        return diasTrabalhados;
    }

    public void setDiasTrabalhados(Integer diasTrabalhados) {
        this.diasTrabalhados = diasTrabalhados;
    }

    public String getNomeArquivoGerado() {
        return nomeArquivoGerado;
    }

    public void setNomeArquivoGerado(String nomeArquivoGerado) {
        this.nomeArquivoGerado = nomeArquivoGerado;
    }

    public String getNomeArquivoCorrigido() {
        return nomeArquivoCorrigido;
    }

    public void setNomeArquivoCorrigido(String nomeArquivoCorrigido) {
        this.nomeArquivoCorrigido = nomeArquivoCorrigido;
    }

    public CaspUsuarios getIUsuario() {
        return iUsuario;
    }

    public void setIUsuario(CaspUsuarios iUsuario) {
        this.iUsuario = iUsuario;
    }

    public PontoPeriodos getIPeriodo() {
        return iPeriodo;
    }

    public void setIPeriodo(PontoPeriodos iPeriodo) {
        this.iPeriodo = iPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iEncerramento != null ? iEncerramento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PontoEncerramentos)) {
            return false;
        }
        PontoEncerramentos other = (PontoEncerramentos) object;
        if ((this.iEncerramento == null && other.iEncerramento != null) || (this.iEncerramento != null && !this.iEncerramento.equals(other.iEncerramento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.PontoEncerramentos[ iEncerramento=" + iEncerramento + " ]";
    }
    
}
