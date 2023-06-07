/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.domain.model;

import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ponto_horarios",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoHorarios.findAll", query = "SELECT p FROM PontoHorarios p"),
    @NamedQuery(name = "PontoHorarios.findByIHorario", query = "SELECT p FROM PontoHorarios p WHERE p.iHorario = :iHorario"),
    @NamedQuery(name = "PontoHorarios.findByEntrada", query = "SELECT p FROM PontoHorarios p WHERE p.entrada = :entrada"),
    @NamedQuery(name = "PontoHorarios.findByAlmoco", query = "SELECT p FROM PontoHorarios p WHERE p.almoco = :almoco"),
    @NamedQuery(name = "PontoHorarios.findByRetorno", query = "SELECT p FROM PontoHorarios p WHERE p.retorno = :retorno"),
    @NamedQuery(name = "PontoHorarios.findBySaida", query = "SELECT p FROM PontoHorarios p WHERE p.saida = :saida"),
    @NamedQuery(name = "PontoHorarios.findByEntradaAdicional1", query = "SELECT p FROM PontoHorarios p WHERE p.entradaAdicional1 = :entradaAdicional1"),
    @NamedQuery(name = "PontoHorarios.findBySaidaAdicional1", query = "SELECT p FROM PontoHorarios p WHERE p.saidaAdicional1 = :saidaAdicional1"),
    @NamedQuery(name = "PontoHorarios.findByEntradaAdicional2", query = "SELECT p FROM PontoHorarios p WHERE p.entradaAdicional2 = :entradaAdicional2"),
    @NamedQuery(name = "PontoHorarios.findBySaidaAdicional2", query = "SELECT p FROM PontoHorarios p WHERE p.saidaAdicional2 = :saidaAdicional2"),
    @NamedQuery(name = "PontoHorarios.findByEntradaAdicional3", query = "SELECT p FROM PontoHorarios p WHERE p.entradaAdicional3 = :entradaAdicional3"),
    @NamedQuery(name = "PontoHorarios.findBySaidaAdicional3", query = "SELECT p FROM PontoHorarios p WHERE p.saidaAdicional3 = :saidaAdicional3"),
    @NamedQuery(name = "PontoHorarios.findByDescontos", query = "SELECT p FROM PontoHorarios p WHERE p.descontos = :descontos"),
    @NamedQuery(name = "PontoHorarios.findByDsr", query = "SELECT p FROM PontoHorarios p WHERE p.dsr = :dsr"),
    @NamedQuery(name = "PontoHorarios.findByAcrescimos", query = "SELECT p FROM PontoHorarios p WHERE p.acrescimos = :acrescimos"),
    @NamedQuery(name = "PontoHorarios.findByHorasTrabalhadas", query = "SELECT p FROM PontoHorarios p WHERE p.horasTrabalhadas = :horasTrabalhadas"),
    @NamedQuery(name = "PontoHorarios.findByHorasNaoTrabalhadas", query = "SELECT p FROM PontoHorarios p WHERE p.horasNaoTrabalhadas = :horasNaoTrabalhadas"),
    @NamedQuery(name = "PontoHorarios.findByHorasTotais", query = "SELECT p FROM PontoHorarios p WHERE p.horasTotais = :horasTotais"),
    @NamedQuery(name = "PontoHorarios.findByDataRegistro", query = "SELECT p FROM PontoHorarios p WHERE p.dataRegistro = :dataRegistro"),
    @NamedQuery(name = "PontoHorarios.findByDiaDaSemana", query = "SELECT p FROM PontoHorarios p WHERE p.diaDaSemana = :diaDaSemana"),
    @NamedQuery(name = "PontoHorarios.findByFeriado", query = "SELECT p FROM PontoHorarios p WHERE p.feriado = :feriado"),
    @NamedQuery(name = "PontoHorarios.findByFaltaJustificada", query = "SELECT p FROM PontoHorarios p WHERE p.faltaJustificada = :faltaJustificada"),
    @NamedQuery(name = "PontoHorarios.findByFerias", query = "SELECT p FROM PontoHorarios p WHERE p.ferias = :ferias"),
    @NamedQuery(name = "PontoHorarios.findByEditavel", query = "SELECT p FROM PontoHorarios p WHERE p.editavel = :editavel"),
    @NamedQuery(name = "PontoHorarios.findByUsuarioAlteracao", query = "SELECT p FROM PontoHorarios p WHERE p.usuarioAlteracao = :usuarioAlteracao"),
    @NamedQuery(name = "PontoHorarios.findByDataalteracao", query = "SELECT p FROM PontoHorarios p WHERE p.dataalteracao = :dataalteracao"),
    @NamedQuery(name = "PontoHorarios.findBySituacao", query = "SELECT p FROM PontoHorarios p WHERE p.situacao = :situacao"),
    @NamedQuery(name = "PontoHorarios.findByDescontosLong", query = "SELECT p FROM PontoHorarios p WHERE p.descontosLong = :descontosLong"),
    @NamedQuery(name = "PontoHorarios.findByDsrLong", query = "SELECT p FROM PontoHorarios p WHERE p.dsrLong = :dsrLong"),
    @NamedQuery(name = "PontoHorarios.findByAcrescimosLong", query = "SELECT p FROM PontoHorarios p WHERE p.acrescimosLong = :acrescimosLong"),
    @NamedQuery(name = "PontoHorarios.findByHorasTrabalhadasLong", query = "SELECT p FROM PontoHorarios p WHERE p.horasTrabalhadasLong = :horasTrabalhadasLong"),
    @NamedQuery(name = "PontoHorarios.findByHorasNaoTrabalhadasLong", query = "SELECT p FROM PontoHorarios p WHERE p.horasNaoTrabalhadasLong = :horasNaoTrabalhadasLong"),
    @NamedQuery(name = "PontoHorarios.findByHorasTotaisLong", query = "SELECT p FROM PontoHorarios p WHERE p.horasTotaisLong = :horasTotaisLong"),
    @NamedQuery(name = "PontoHorarios.findByHorasAbonadasLong", query = "SELECT p FROM PontoHorarios p WHERE p.horasAbonadasLong = :horasAbonadasLong")})
public class PontoHorarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_horario")
    private Integer iHorario;
    @Column(name = "entrada")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime entrada;
    @Column(name = "almoco")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime almoco;
    @Column(name = "retorno")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime retorno;
    @Column(name = "saida")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime saida;
    @Column(name = "entrada_adicional1")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime entradaAdicional1;
    @Column(name = "saida_adicional1")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime saidaAdicional1;
    @Column(name = "entrada_adicional2")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime entradaAdicional2;
    @Column(name = "saida_adicional2")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime saidaAdicional2;
    @Column(name = "entrada_adicional3")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime entradaAdicional3;
    @Column(name = "saida_adicional3")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime saidaAdicional3;
    @Column(name = "descontos")
    //@Temporal(TemporalType.TIME)
    private LocalTime descontos;
    @Column(name = "dsr")
    //@Temporal(TemporalType.TIME)
    private LocalTime dsr;
    @Column(name = "acrescimos")
    //@Temporal(TemporalType.TIME)
    private LocalTime acrescimos;
    @Column(name = "horas_trabalhadas")
    //@Temporal(TemporalType.TIME)
    private LocalTime horasTrabalhadas;
    @Column(name = "horas_nao_trabalhadas")
    //@Temporal(TemporalType.TIME)
    private LocalTime horasNaoTrabalhadas;
    @Column(name = "horas_totais")
    //@Temporal(TemporalType.TIME)
    private LocalTime horasTotais;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_registro")
    //@Temporal(TemporalType.DATE)
    private LocalDate dataRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "dia_da_semana")
    private String diaDaSemana;
    @Basic(optional = false)
    @NotNull
    @Column(name = "feriado")
    private boolean feriado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "falta_justificada")
    private boolean faltaJustificada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ferias")
    private boolean ferias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "editavel")
    private boolean editavel;
    @Size(max = 2147483647)
    @Column(name = "usuario_alteracao")
    private String usuarioAlteracao;
    @Column(name = "dataalteracao")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataalteracao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "situacao")
    private String situacao;
    @Column(name = "descontos_long")
    private long descontosLong;
    @Column(name = "dsr_long")
    private long dsrLong;
    @Column(name = "acrescimos_long")
    private long acrescimosLong;
    @Column(name = "horas_trabalhadas_long")
    private long horasTrabalhadasLong;
    @Column(name = "horas_nao_trabalhadas_long")
    private long horasNaoTrabalhadasLong;
    @Column(name = "horas_totais_long")
    private long horasTotaisLong;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horas_abonadas_long")
    private long horasAbonadasLong;
    @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")
    @ManyToOne(optional = false)
    private CaspUsuarios iUsuario;
    @JoinColumn(name = "i_periodo", referencedColumnName = "i_periodo")
    @ManyToOne
    private PontoPeriodos iPeriodo;
    @JoinColumn(name = "i_turno", referencedColumnName = "i_turno")
    @ManyToOne
    private PontoTurnos iTurno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iHorario")
    private List<PontoIntercorrencias> pontoIntercorrenciasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iHorario")
    private List<PontoSolicitacoesHorarios> pontoSolicitacoesHorariosList;

    public PontoHorarios() {
    }

    public PontoHorarios(Integer iHorario) {
        this.iHorario = iHorario;
    }

    public PontoHorarios(Integer iHorario, LocalDate dataRegistro, String diaDaSemana, boolean feriado, boolean faltaJustificada, boolean ferias, boolean editavel, String situacao, long horasAbonadasLong) {
        this.iHorario = iHorario;
        this.dataRegistro = dataRegistro;
        this.diaDaSemana = diaDaSemana;
        this.feriado = feriado;
        this.faltaJustificada = faltaJustificada;
        this.ferias = ferias;
        this.editavel = editavel;
        this.situacao = situacao;
        this.horasAbonadasLong = horasAbonadasLong;
    }

    public Integer getIHorario() {
        return iHorario;
    }

    public void setIHorario(Integer iHorario) {
        this.iHorario = iHorario;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getAlmoco() {
        return almoco;
    }

    public void setAlmoco(LocalDateTime almoco) {
        this.almoco = almoco;
    }

    public LocalDateTime getRetorno() {
        return retorno;
    }

    public void setRetorno(LocalDateTime retorno) {
        this.retorno = retorno;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public LocalDateTime getEntradaAdicional1() {
        return entradaAdicional1;
    }

    public void setEntradaAdicional1(LocalDateTime entradaAdicional1) {
        this.entradaAdicional1 = entradaAdicional1;
    }

    public LocalDateTime getSaidaAdicional1() {
        return saidaAdicional1;
    }

    public void setSaidaAdicional1(LocalDateTime saidaAdicional1) {
        this.saidaAdicional1 = saidaAdicional1;
    }

    public LocalDateTime getEntradaAdicional2() {
        return entradaAdicional2;
    }

    public void setEntradaAdicional2(LocalDateTime entradaAdicional2) {
        this.entradaAdicional2 = entradaAdicional2;
    }

    public LocalDateTime getSaidaAdicional2() {
        return saidaAdicional2;
    }

    public void setSaidaAdicional2(LocalDateTime saidaAdicional2) {
        this.saidaAdicional2 = saidaAdicional2;
    }

    public LocalDateTime getEntradaAdicional3() {
        return entradaAdicional3;
    }

    public void setEntradaAdicional3(LocalDateTime entradaAdicional3) {
        this.entradaAdicional3 = entradaAdicional3;
    }

    public LocalDateTime getSaidaAdicional3() {
        return saidaAdicional3;
    }

    public void setSaidaAdicional3(LocalDateTime saidaAdicional3) {
        this.saidaAdicional3 = saidaAdicional3;
    }

    public LocalTime getDescontos() {
        return descontos;
    }

    public void setDescontos(LocalTime descontos) {
        this.descontos = descontos;
    }

    public LocalTime getDsr() {
        return dsr;
    }

    public void setDsr(LocalTime dsr) {
        this.dsr = dsr;
    }

    public LocalTime getAcrescimos() {
        return acrescimos;
    }

    public void setAcrescimos(LocalTime acrescimos) {
        this.acrescimos = acrescimos;
    }

    public LocalTime getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(LocalTime horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public LocalTime getHorasNaoTrabalhadas() {
        return horasNaoTrabalhadas;
    }

    public void setHorasNaoTrabalhadas(LocalTime horasNaoTrabalhadas) {
        this.horasNaoTrabalhadas = horasNaoTrabalhadas;
    }

    public LocalTime getHorasTotais() {
        return horasTotais;
    }

    public void setHorasTotais(LocalTime horasTotais) {
        this.horasTotais = horasTotais;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public boolean getFeriado() {
        return feriado;
    }

    public void setFeriado(boolean feriado) {
        this.feriado = feriado;
    }

    public boolean getFaltaJustificada() {
        return faltaJustificada;
    }

    public void setFaltaJustificada(boolean faltaJustificada) {
        this.faltaJustificada = faltaJustificada;
    }

    public boolean getFerias() {
        return ferias;
    }

    public void setFerias(boolean ferias) {
        this.ferias = ferias;
    }

    public boolean getEditavel() {
        return editavel;
    }

    public void setEditavel(boolean editavel) {
        this.editavel = editavel;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public LocalDateTime getDataalteracao() {
        return dataalteracao;
    }

    public void setDataalteracao(LocalDateTime dataalteracao) {
        this.dataalteracao = dataalteracao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public long getDescontosLong() {
        return descontosLong;
    }

    public void setDescontosLong(long descontosLong) {
        this.descontosLong = descontosLong;
    }

    public long getDsrLong() {
        return dsrLong;
    }

    public void setDsrLong(long dsrLong) {
        this.dsrLong = dsrLong;
    }

    public long getAcrescimosLong() {
        return acrescimosLong;
    }

    public void setAcrescimosLong(long acrescimosLong) {
        this.acrescimosLong = acrescimosLong;
    }

    public long getHorasTrabalhadasLong() {
        return horasTrabalhadasLong;
    }

    public void setHorasTrabalhadasLong(long horasTrabalhadasLong) {
        this.horasTrabalhadasLong = horasTrabalhadasLong;
    }

    public long getHorasNaoTrabalhadasLong() {
        return horasNaoTrabalhadasLong;
    }

    public void setHorasNaoTrabalhadasLong(long horasNaoTrabalhadasLong) {
        this.horasNaoTrabalhadasLong = horasNaoTrabalhadasLong;
    }

    public long getHorasTotaisLong() {
        return horasTotaisLong;
    }

    public void setHorasTotaisLong(long horasTotaisLong) {
        this.horasTotaisLong = horasTotaisLong;
    }

    public long getHorasAbonadasLong() {
        return horasAbonadasLong;
    }

    public void setHorasAbonadasLong(long horasAbonadasLong) {
        this.horasAbonadasLong = horasAbonadasLong;
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

    public PontoTurnos getITurno() {
        return iTurno;
    }

    public void setITurno(PontoTurnos iTurno) {
        this.iTurno = iTurno;
    }

    @XmlTransient
    public List<PontoIntercorrencias> getPontoIntercorrenciasList() {
        return pontoIntercorrenciasList;
    }

    public void setPontoIntercorrenciasList(List<PontoIntercorrencias> pontoIntercorrenciasList) {
        this.pontoIntercorrenciasList = pontoIntercorrenciasList;
    }

    @XmlTransient
    public List<PontoSolicitacoesHorarios> getPontoSolicitacoesHorariosList() {
        return pontoSolicitacoesHorariosList;
    }

    public void setPontoSolicitacoesHorariosList(List<PontoSolicitacoesHorarios> pontoSolicitacoesHorariosList) {
        this.pontoSolicitacoesHorariosList = pontoSolicitacoesHorariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iHorario != null ? iHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PontoHorarios)) {
            return false;
        }
        PontoHorarios other = (PontoHorarios) object;
        if ((this.iHorario == null && other.iHorario != null) || (this.iHorario != null && !this.iHorario.equals(other.iHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.PontoHorarios[ iHorario=" + iHorario + " ]";
    }
    
}
