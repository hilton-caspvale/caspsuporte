/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.ponto.domain.model;

import java.io.Serializable;
import java.time.LocalTime;
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
@Table(name = "ponto_config",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoConfig.findAll", query = "SELECT p FROM PontoConfig p"),
    @NamedQuery(name = "PontoConfig.findByIConfig", query = "SELECT p FROM PontoConfig p WHERE p.iConfig = :iConfig"),
    @NamedQuery(name = "PontoConfig.findByAntecipaEntrada", query = "SELECT p FROM PontoConfig p WHERE p.antecipaEntrada = :antecipaEntrada"),
    @NamedQuery(name = "PontoConfig.findByProrrogaEntrada", query = "SELECT p FROM PontoConfig p WHERE p.prorrogaEntrada = :prorrogaEntrada"),
    @NamedQuery(name = "PontoConfig.findByAntecipaAlmoco", query = "SELECT p FROM PontoConfig p WHERE p.antecipaAlmoco = :antecipaAlmoco"),
    @NamedQuery(name = "PontoConfig.findByProrrogaAlmoco", query = "SELECT p FROM PontoConfig p WHERE p.prorrogaAlmoco = :prorrogaAlmoco"),
    @NamedQuery(name = "PontoConfig.findByAntecipaSaida", query = "SELECT p FROM PontoConfig p WHERE p.antecipaSaida = :antecipaSaida"),
    @NamedQuery(name = "PontoConfig.findByProrrogaSaida", query = "SELECT p FROM PontoConfig p WHERE p.prorrogaSaida = :prorrogaSaida"),
    @NamedQuery(name = "PontoConfig.findByAntecipaEntradaadicional", query = "SELECT p FROM PontoConfig p WHERE p.antecipaEntradaadicional = :antecipaEntradaadicional"),
    @NamedQuery(name = "PontoConfig.findByProrrogaEntradaadicional", query = "SELECT p FROM PontoConfig p WHERE p.prorrogaEntradaadicional = :prorrogaEntradaadicional"),
    @NamedQuery(name = "PontoConfig.findByEmail", query = "SELECT p FROM PontoConfig p WHERE p.email = :email"),
    @NamedQuery(name = "PontoConfig.findBySenha", query = "SELECT p FROM PontoConfig p WHERE p.senha = :senha"),
    @NamedQuery(name = "PontoConfig.findByServidorSmtp", query = "SELECT p FROM PontoConfig p WHERE p.servidorSmtp = :servidorSmtp"),
    @NamedQuery(name = "PontoConfig.findByPorta", query = "SELECT p FROM PontoConfig p WHERE p.porta = :porta"),
    @NamedQuery(name = "PontoConfig.findByAutenticacaoSsl", query = "SELECT p FROM PontoConfig p WHERE p.autenticacaoSsl = :autenticacaoSsl"),
    @NamedQuery(name = "PontoConfig.findByAntecipaRetorno", query = "SELECT p FROM PontoConfig p WHERE p.antecipaRetorno = :antecipaRetorno"),
    @NamedQuery(name = "PontoConfig.findByProrrogaRetorno", query = "SELECT p FROM PontoConfig p WHERE p.prorrogaRetorno = :prorrogaRetorno"),
    @NamedQuery(name = "PontoConfig.findByAntecipaSaidaadicional", query = "SELECT p FROM PontoConfig p WHERE p.antecipaSaidaadicional = :antecipaSaidaadicional"),
    @NamedQuery(name = "PontoConfig.findByProrrogaSaidaadicional", query = "SELECT p FROM PontoConfig p WHERE p.prorrogaSaidaadicional = :prorrogaSaidaadicional"),
    @NamedQuery(name = "PontoConfig.findByMsgEmailNormal", query = "SELECT p FROM PontoConfig p WHERE p.msgEmailNormal = :msgEmailNormal"),
    @NamedQuery(name = "PontoConfig.findByMsgEmailAtraso", query = "SELECT p FROM PontoConfig p WHERE p.msgEmailAtraso = :msgEmailAtraso"),
    @NamedQuery(name = "PontoConfig.findByMsgEmailAcrescimos", query = "SELECT p FROM PontoConfig p WHERE p.msgEmailAcrescimos = :msgEmailAcrescimos"),
    @NamedQuery(name = "PontoConfig.findByMsgEmailForaHorario", query = "SELECT p FROM PontoConfig p WHERE p.msgEmailForaHorario = :msgEmailForaHorario"),
    @NamedQuery(name = "PontoConfig.findByMsgEmailHorarioPosterior", query = "SELECT p FROM PontoConfig p WHERE p.msgEmailHorarioPosterior = :msgEmailHorarioPosterior"),
    @NamedQuery(name = "PontoConfig.findByMsgEmailEncerramento", query = "SELECT p FROM PontoConfig p WHERE p.msgEmailEncerramento = :msgEmailEncerramento"),
    @NamedQuery(name = "PontoConfig.findByMsgEmailAssinatura", query = "SELECT p FROM PontoConfig p WHERE p.msgEmailAssinatura = :msgEmailAssinatura"),
    @NamedQuery(name = "PontoConfig.findByEnviaEmailNormal", query = "SELECT p FROM PontoConfig p WHERE p.enviaEmailNormal = :enviaEmailNormal"),
    @NamedQuery(name = "PontoConfig.findByEnviaEmailAtraso", query = "SELECT p FROM PontoConfig p WHERE p.enviaEmailAtraso = :enviaEmailAtraso"),
    @NamedQuery(name = "PontoConfig.findByEnviaEmailAcrescimos", query = "SELECT p FROM PontoConfig p WHERE p.enviaEmailAcrescimos = :enviaEmailAcrescimos"),
    @NamedQuery(name = "PontoConfig.findByEnviaEmailForaHorario", query = "SELECT p FROM PontoConfig p WHERE p.enviaEmailForaHorario = :enviaEmailForaHorario"),
    @NamedQuery(name = "PontoConfig.findByEnviaEmailHorarioPosterior", query = "SELECT p FROM PontoConfig p WHERE p.enviaEmailHorarioPosterior = :enviaEmailHorarioPosterior"),
    @NamedQuery(name = "PontoConfig.findByEnviaEmailEncerramento", query = "SELECT p FROM PontoConfig p WHERE p.enviaEmailEncerramento = :enviaEmailEncerramento"),
    @NamedQuery(name = "PontoConfig.findByUsuarioAlteracao", query = "SELECT p FROM PontoConfig p WHERE p.usuarioAlteracao = :usuarioAlteracao"),
    @NamedQuery(name = "PontoConfig.findByDtAlteracao", query = "SELECT p FROM PontoConfig p WHERE p.dtAlteracao = :dtAlteracao"),
    @NamedQuery(name = "PontoConfig.findByExibeUsuarioInativo", query = "SELECT p FROM PontoConfig p WHERE p.exibeUsuarioInativo = :exibeUsuarioInativo"),
    @NamedQuery(name = "PontoConfig.findByEmailFrom", query = "SELECT p FROM PontoConfig p WHERE p.emailFrom = :emailFrom"),
    @NamedQuery(name = "PontoConfig.findByEmailCopia", query = "SELECT p FROM PontoConfig p WHERE p.emailCopia = :emailCopia"),
    @NamedQuery(name = "PontoConfig.findByMsgAtraso", query = "SELECT p FROM PontoConfig p WHERE p.msgAtraso = :msgAtraso"),
    @NamedQuery(name = "PontoConfig.findByMsgAdicional", query = "SELECT p FROM PontoConfig p WHERE p.msgAdicional = :msgAdicional"),
    @NamedQuery(name = "PontoConfig.findByMsgForaHorario", query = "SELECT p FROM PontoConfig p WHERE p.msgForaHorario = :msgForaHorario"),
    @NamedQuery(name = "PontoConfig.findByMsgHorarioPosterior", query = "SELECT p FROM PontoConfig p WHERE p.msgHorarioPosterior = :msgHorarioPosterior"),
    @NamedQuery(name = "PontoConfig.findByGeraInterAtraso", query = "SELECT p FROM PontoConfig p WHERE p.geraInterAtraso = :geraInterAtraso"),
    @NamedQuery(name = "PontoConfig.findByAtrasoAutorizado", query = "SELECT p FROM PontoConfig p WHERE p.atrasoAutorizado = :atrasoAutorizado"),
    @NamedQuery(name = "PontoConfig.findByGeraInterAdicional", query = "SELECT p FROM PontoConfig p WHERE p.geraInterAdicional = :geraInterAdicional"),
    @NamedQuery(name = "PontoConfig.findByAdicionalAutorizado", query = "SELECT p FROM PontoConfig p WHERE p.adicionalAutorizado = :adicionalAutorizado"),
    @NamedQuery(name = "PontoConfig.findByGeraInterForaHorario", query = "SELECT p FROM PontoConfig p WHERE p.geraInterForaHorario = :geraInterForaHorario"),
    @NamedQuery(name = "PontoConfig.findByForaHorarioAutorizado", query = "SELECT p FROM PontoConfig p WHERE p.foraHorarioAutorizado = :foraHorarioAutorizado"),
    @NamedQuery(name = "PontoConfig.findByGeraInterHorarioPosterior", query = "SELECT p FROM PontoConfig p WHERE p.geraInterHorarioPosterior = :geraInterHorarioPosterior"),
    @NamedQuery(name = "PontoConfig.findByHorarioPosteriorAutorizado", query = "SELECT p FROM PontoConfig p WHERE p.horarioPosteriorAutorizado = :horarioPosteriorAutorizado"),
    @NamedQuery(name = "PontoConfig.findByGeraInterTurno", query = "SELECT p FROM PontoConfig p WHERE p.geraInterTurno = :geraInterTurno"),
    @NamedQuery(name = "PontoConfig.findByInterTurnoAutorizado", query = "SELECT p FROM PontoConfig p WHERE p.interTurnoAutorizado = :interTurnoAutorizado"),
    @NamedQuery(name = "PontoConfig.findByGeraInterIntervalo", query = "SELECT p FROM PontoConfig p WHERE p.geraInterIntervalo = :geraInterIntervalo"),
    @NamedQuery(name = "PontoConfig.findByInterIntervaloAutorizado", query = "SELECT p FROM PontoConfig p WHERE p.interIntervaloAutorizado = :interIntervaloAutorizado"),
    @NamedQuery(name = "PontoConfig.findByValidaMac", query = "SELECT p FROM PontoConfig p WHERE p.validaMac = :validaMac")})
public class PontoConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_config")
    private Integer iConfig;
    @Column(name = "antecipa_entrada")
    //@Temporal(TemporalType.TIME)
    private LocalTime antecipaEntrada;
    @Column(name = "prorroga_entrada")
    //@Temporal(TemporalType.TIME)
    private LocalTime prorrogaEntrada;
    @Column(name = "antecipa_almoco")
    //@Temporal(TemporalType.TIME)
    private LocalTime antecipaAlmoco;
    @Column(name = "prorroga_almoco")
    //@Temporal(TemporalType.TIME)
    private LocalTime prorrogaAlmoco;
    @Column(name = "antecipa_saida")
    //@Temporal(TemporalType.TIME)
    private LocalTime antecipaSaida;
    @Column(name = "prorroga_saida")
    //@Temporal(TemporalType.TIME)
    private LocalTime prorrogaSaida;
    @Column(name = "antecipa_entradaadicional")
    //@Temporal(TemporalType.TIME)
    private LocalTime antecipaEntradaadicional;
    @Column(name = "prorroga_entradaadicional")
    //@Temporal(TemporalType.TIME)
    private LocalTime prorrogaEntradaadicional;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Size(max = 2147483647)
    @Column(name = "senha")
    private String senha;
    @Size(max = 2147483647)
    @Column(name = "servidor_smtp")
    private String servidorSmtp;
    @Column(name = "porta")
    private Integer porta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autenticacao_ssl")
    private boolean autenticacaoSsl;
    @Column(name = "antecipa_retorno")
    //@Temporal(TemporalType.TIME)
    private LocalTime antecipaRetorno;
    @Column(name = "prorroga_retorno")
    //@Temporal(TemporalType.TIME)
    private LocalTime prorrogaRetorno;
    @Column(name = "antecipa_saidaadicional")
    //@Temporal(TemporalType.TIME)
    private LocalTime antecipaSaidaadicional;
    @Column(name = "prorroga_saidaadicional")
    //@Temporal(TemporalType.TIME)
    private LocalTime prorrogaSaidaadicional;
    @Size(max = 2147483647)
    @Column(name = "msg_email_normal")
    private String msgEmailNormal;
    @Size(max = 2147483647)
    @Column(name = "msg_email_atraso")
    private String msgEmailAtraso;
    @Size(max = 2147483647)
    @Column(name = "msg_email_acrescimos")
    private String msgEmailAcrescimos;
    @Size(max = 2147483647)
    @Column(name = "msg_email_fora_horario")
    private String msgEmailForaHorario;
    @Size(max = 2147483647)
    @Column(name = "msg_email_horario_posterior")
    private String msgEmailHorarioPosterior;
    @Size(max = 2147483647)
    @Column(name = "msg_email_encerramento")
    private String msgEmailEncerramento;
    @Size(max = 2147483647)
    @Column(name = "msg_email_assinatura")
    private String msgEmailAssinatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "envia_email_normal")
    private boolean enviaEmailNormal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "envia_email_atraso")
    private boolean enviaEmailAtraso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "envia_email_acrescimos")
    private boolean enviaEmailAcrescimos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "envia_email_fora_horario")
    private boolean enviaEmailForaHorario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "envia_email_horario_posterior")
    private boolean enviaEmailHorarioPosterior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "envia_email_encerramento")
    private boolean enviaEmailEncerramento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "usuario_alteracao")
    private String usuarioAlteracao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_alteracao")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalTime dtAlteracao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exibe_usuario_inativo")
    private boolean exibeUsuarioInativo;
    @Size(max = 2147483647)
    @Column(name = "email_from")
    private String emailFrom;
    @Size(max = 2147483647)
    @Column(name = "email_copia")
    private String emailCopia;
    @Size(max = 2147483647)
    @Column(name = "msg_atraso")
    private String msgAtraso;
    @Size(max = 2147483647)
    @Column(name = "msg_adicional")
    private String msgAdicional;
    @Size(max = 2147483647)
    @Column(name = "msg_fora_horario")
    private String msgForaHorario;
    @Size(max = 2147483647)
    @Column(name = "msg_horario_posterior")
    private String msgHorarioPosterior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gera_inter_atraso")
    private boolean geraInterAtraso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "atraso_autorizado")
    private boolean atrasoAutorizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gera_inter_adicional")
    private boolean geraInterAdicional;
    @Basic(optional = false)
    @NotNull
    @Column(name = "adicional_autorizado")
    private boolean adicionalAutorizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gera_inter_fora_horario")
    private boolean geraInterForaHorario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fora_horario_autorizado")
    private boolean foraHorarioAutorizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gera_inter_horario_posterior")
    private boolean geraInterHorarioPosterior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario_posterior_autorizado")
    private boolean horarioPosteriorAutorizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gera_inter_turno")
    private boolean geraInterTurno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inter_turno_autorizado")
    private boolean interTurnoAutorizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gera_inter_intervalo")
    private boolean geraInterIntervalo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inter_intervalo_autorizado")
    private boolean interIntervaloAutorizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valida_mac")
    private boolean validaMac;

    public PontoConfig() {
    }

    public PontoConfig(Integer iConfig) {
        this.iConfig = iConfig;
    }

    public PontoConfig(Integer iConfig, boolean autenticacaoSsl, boolean enviaEmailNormal, boolean enviaEmailAtraso, boolean enviaEmailAcrescimos, boolean enviaEmailForaHorario, boolean enviaEmailHorarioPosterior, boolean enviaEmailEncerramento, String usuarioAlteracao, LocalTime dtAlteracao, boolean exibeUsuarioInativo, boolean geraInterAtraso, boolean atrasoAutorizado, boolean geraInterAdicional, boolean adicionalAutorizado, boolean geraInterForaHorario, boolean foraHorarioAutorizado, boolean geraInterHorarioPosterior, boolean horarioPosteriorAutorizado, boolean geraInterTurno, boolean interTurnoAutorizado, boolean geraInterIntervalo, boolean interIntervaloAutorizado, boolean validaMac) {
        this.iConfig = iConfig;
        this.autenticacaoSsl = autenticacaoSsl;
        this.enviaEmailNormal = enviaEmailNormal;
        this.enviaEmailAtraso = enviaEmailAtraso;
        this.enviaEmailAcrescimos = enviaEmailAcrescimos;
        this.enviaEmailForaHorario = enviaEmailForaHorario;
        this.enviaEmailHorarioPosterior = enviaEmailHorarioPosterior;
        this.enviaEmailEncerramento = enviaEmailEncerramento;
        this.usuarioAlteracao = usuarioAlteracao;
        this.dtAlteracao = dtAlteracao;
        this.exibeUsuarioInativo = exibeUsuarioInativo;
        this.geraInterAtraso = geraInterAtraso;
        this.atrasoAutorizado = atrasoAutorizado;
        this.geraInterAdicional = geraInterAdicional;
        this.adicionalAutorizado = adicionalAutorizado;
        this.geraInterForaHorario = geraInterForaHorario;
        this.foraHorarioAutorizado = foraHorarioAutorizado;
        this.geraInterHorarioPosterior = geraInterHorarioPosterior;
        this.horarioPosteriorAutorizado = horarioPosteriorAutorizado;
        this.geraInterTurno = geraInterTurno;
        this.interTurnoAutorizado = interTurnoAutorizado;
        this.geraInterIntervalo = geraInterIntervalo;
        this.interIntervaloAutorizado = interIntervaloAutorizado;
        this.validaMac = validaMac;
    }

    public Integer getIConfig() {
        return iConfig;
    }

    public void setIConfig(Integer iConfig) {
        this.iConfig = iConfig;
    }

    public LocalTime getAntecipaEntrada() {
        return antecipaEntrada;
    }

    public void setAntecipaEntrada(LocalTime antecipaEntrada) {
        this.antecipaEntrada = antecipaEntrada;
    }

    public LocalTime getProrrogaEntrada() {
        return prorrogaEntrada;
    }

    public void setProrrogaEntrada(LocalTime prorrogaEntrada) {
        this.prorrogaEntrada = prorrogaEntrada;
    }

    public LocalTime getAntecipaAlmoco() {
        return antecipaAlmoco;
    }

    public void setAntecipaAlmoco(LocalTime antecipaAlmoco) {
        this.antecipaAlmoco = antecipaAlmoco;
    }

    public LocalTime getProrrogaAlmoco() {
        return prorrogaAlmoco;
    }

    public void setProrrogaAlmoco(LocalTime prorrogaAlmoco) {
        this.prorrogaAlmoco = prorrogaAlmoco;
    }

    public LocalTime getAntecipaSaida() {
        return antecipaSaida;
    }

    public void setAntecipaSaida(LocalTime antecipaSaida) {
        this.antecipaSaida = antecipaSaida;
    }

    public LocalTime getProrrogaSaida() {
        return prorrogaSaida;
    }

    public void setProrrogaSaida(LocalTime prorrogaSaida) {
        this.prorrogaSaida = prorrogaSaida;
    }

    public LocalTime getAntecipaEntradaadicional() {
        return antecipaEntradaadicional;
    }

    public void setAntecipaEntradaadicional(LocalTime antecipaEntradaadicional) {
        this.antecipaEntradaadicional = antecipaEntradaadicional;
    }

    public LocalTime getProrrogaEntradaadicional() {
        return prorrogaEntradaadicional;
    }

    public void setProrrogaEntradaadicional(LocalTime prorrogaEntradaadicional) {
        this.prorrogaEntradaadicional = prorrogaEntradaadicional;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getServidorSmtp() {
        return servidorSmtp;
    }

    public void setServidorSmtp(String servidorSmtp) {
        this.servidorSmtp = servidorSmtp;
    }

    public Integer getPorta() {
        return porta;
    }

    public void setPorta(Integer porta) {
        this.porta = porta;
    }

    public boolean getAutenticacaoSsl() {
        return autenticacaoSsl;
    }

    public void setAutenticacaoSsl(boolean autenticacaoSsl) {
        this.autenticacaoSsl = autenticacaoSsl;
    }

    public LocalTime getAntecipaRetorno() {
        return antecipaRetorno;
    }

    public void setAntecipaRetorno(LocalTime antecipaRetorno) {
        this.antecipaRetorno = antecipaRetorno;
    }

    public LocalTime getProrrogaRetorno() {
        return prorrogaRetorno;
    }

    public void setProrrogaRetorno(LocalTime prorrogaRetorno) {
        this.prorrogaRetorno = prorrogaRetorno;
    }

    public LocalTime getAntecipaSaidaadicional() {
        return antecipaSaidaadicional;
    }

    public void setAntecipaSaidaadicional(LocalTime antecipaSaidaadicional) {
        this.antecipaSaidaadicional = antecipaSaidaadicional;
    }

    public LocalTime getProrrogaSaidaadicional() {
        return prorrogaSaidaadicional;
    }

    public void setProrrogaSaidaadicional(LocalTime prorrogaSaidaadicional) {
        this.prorrogaSaidaadicional = prorrogaSaidaadicional;
    }

    public String getMsgEmailNormal() {
        return msgEmailNormal;
    }

    public void setMsgEmailNormal(String msgEmailNormal) {
        this.msgEmailNormal = msgEmailNormal;
    }

    public String getMsgEmailAtraso() {
        return msgEmailAtraso;
    }

    public void setMsgEmailAtraso(String msgEmailAtraso) {
        this.msgEmailAtraso = msgEmailAtraso;
    }

    public String getMsgEmailAcrescimos() {
        return msgEmailAcrescimos;
    }

    public void setMsgEmailAcrescimos(String msgEmailAcrescimos) {
        this.msgEmailAcrescimos = msgEmailAcrescimos;
    }

    public String getMsgEmailForaHorario() {
        return msgEmailForaHorario;
    }

    public void setMsgEmailForaHorario(String msgEmailForaHorario) {
        this.msgEmailForaHorario = msgEmailForaHorario;
    }

    public String getMsgEmailHorarioPosterior() {
        return msgEmailHorarioPosterior;
    }

    public void setMsgEmailHorarioPosterior(String msgEmailHorarioPosterior) {
        this.msgEmailHorarioPosterior = msgEmailHorarioPosterior;
    }

    public String getMsgEmailEncerramento() {
        return msgEmailEncerramento;
    }

    public void setMsgEmailEncerramento(String msgEmailEncerramento) {
        this.msgEmailEncerramento = msgEmailEncerramento;
    }

    public String getMsgEmailAssinatura() {
        return msgEmailAssinatura;
    }

    public void setMsgEmailAssinatura(String msgEmailAssinatura) {
        this.msgEmailAssinatura = msgEmailAssinatura;
    }

    public boolean getEnviaEmailNormal() {
        return enviaEmailNormal;
    }

    public void setEnviaEmailNormal(boolean enviaEmailNormal) {
        this.enviaEmailNormal = enviaEmailNormal;
    }

    public boolean getEnviaEmailAtraso() {
        return enviaEmailAtraso;
    }

    public void setEnviaEmailAtraso(boolean enviaEmailAtraso) {
        this.enviaEmailAtraso = enviaEmailAtraso;
    }

    public boolean getEnviaEmailAcrescimos() {
        return enviaEmailAcrescimos;
    }

    public void setEnviaEmailAcrescimos(boolean enviaEmailAcrescimos) {
        this.enviaEmailAcrescimos = enviaEmailAcrescimos;
    }

    public boolean getEnviaEmailForaHorario() {
        return enviaEmailForaHorario;
    }

    public void setEnviaEmailForaHorario(boolean enviaEmailForaHorario) {
        this.enviaEmailForaHorario = enviaEmailForaHorario;
    }

    public boolean getEnviaEmailHorarioPosterior() {
        return enviaEmailHorarioPosterior;
    }

    public void setEnviaEmailHorarioPosterior(boolean enviaEmailHorarioPosterior) {
        this.enviaEmailHorarioPosterior = enviaEmailHorarioPosterior;
    }

    public boolean getEnviaEmailEncerramento() {
        return enviaEmailEncerramento;
    }

    public void setEnviaEmailEncerramento(boolean enviaEmailEncerramento) {
        this.enviaEmailEncerramento = enviaEmailEncerramento;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public LocalTime getDtAlteracao() {
        return dtAlteracao;
    }

    public void setDtAlteracao(LocalTime dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    public boolean getExibeUsuarioInativo() {
        return exibeUsuarioInativo;
    }

    public void setExibeUsuarioInativo(boolean exibeUsuarioInativo) {
        this.exibeUsuarioInativo = exibeUsuarioInativo;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailCopia() {
        return emailCopia;
    }

    public void setEmailCopia(String emailCopia) {
        this.emailCopia = emailCopia;
    }

    public String getMsgAtraso() {
        return msgAtraso;
    }

    public void setMsgAtraso(String msgAtraso) {
        this.msgAtraso = msgAtraso;
    }

    public String getMsgAdicional() {
        return msgAdicional;
    }

    public void setMsgAdicional(String msgAdicional) {
        this.msgAdicional = msgAdicional;
    }

    public String getMsgForaHorario() {
        return msgForaHorario;
    }

    public void setMsgForaHorario(String msgForaHorario) {
        this.msgForaHorario = msgForaHorario;
    }

    public String getMsgHorarioPosterior() {
        return msgHorarioPosterior;
    }

    public void setMsgHorarioPosterior(String msgHorarioPosterior) {
        this.msgHorarioPosterior = msgHorarioPosterior;
    }

    public boolean getGeraInterAtraso() {
        return geraInterAtraso;
    }

    public void setGeraInterAtraso(boolean geraInterAtraso) {
        this.geraInterAtraso = geraInterAtraso;
    }

    public boolean getAtrasoAutorizado() {
        return atrasoAutorizado;
    }

    public void setAtrasoAutorizado(boolean atrasoAutorizado) {
        this.atrasoAutorizado = atrasoAutorizado;
    }

    public boolean getGeraInterAdicional() {
        return geraInterAdicional;
    }

    public void setGeraInterAdicional(boolean geraInterAdicional) {
        this.geraInterAdicional = geraInterAdicional;
    }

    public boolean getAdicionalAutorizado() {
        return adicionalAutorizado;
    }

    public void setAdicionalAutorizado(boolean adicionalAutorizado) {
        this.adicionalAutorizado = adicionalAutorizado;
    }

    public boolean getGeraInterForaHorario() {
        return geraInterForaHorario;
    }

    public void setGeraInterForaHorario(boolean geraInterForaHorario) {
        this.geraInterForaHorario = geraInterForaHorario;
    }

    public boolean getForaHorarioAutorizado() {
        return foraHorarioAutorizado;
    }

    public void setForaHorarioAutorizado(boolean foraHorarioAutorizado) {
        this.foraHorarioAutorizado = foraHorarioAutorizado;
    }

    public boolean getGeraInterHorarioPosterior() {
        return geraInterHorarioPosterior;
    }

    public void setGeraInterHorarioPosterior(boolean geraInterHorarioPosterior) {
        this.geraInterHorarioPosterior = geraInterHorarioPosterior;
    }

    public boolean getHorarioPosteriorAutorizado() {
        return horarioPosteriorAutorizado;
    }

    public void setHorarioPosteriorAutorizado(boolean horarioPosteriorAutorizado) {
        this.horarioPosteriorAutorizado = horarioPosteriorAutorizado;
    }

    public boolean getGeraInterTurno() {
        return geraInterTurno;
    }

    public void setGeraInterTurno(boolean geraInterTurno) {
        this.geraInterTurno = geraInterTurno;
    }

    public boolean getInterTurnoAutorizado() {
        return interTurnoAutorizado;
    }

    public void setInterTurnoAutorizado(boolean interTurnoAutorizado) {
        this.interTurnoAutorizado = interTurnoAutorizado;
    }

    public boolean getGeraInterIntervalo() {
        return geraInterIntervalo;
    }

    public void setGeraInterIntervalo(boolean geraInterIntervalo) {
        this.geraInterIntervalo = geraInterIntervalo;
    }

    public boolean getInterIntervaloAutorizado() {
        return interIntervaloAutorizado;
    }

    public void setInterIntervaloAutorizado(boolean interIntervaloAutorizado) {
        this.interIntervaloAutorizado = interIntervaloAutorizado;
    }

    public boolean getValidaMac() {
        return validaMac;
    }

    public void setValidaMac(boolean validaMac) {
        this.validaMac = validaMac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iConfig != null ? iConfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PontoConfig)) {
            return false;
        }
        PontoConfig other = (PontoConfig) object;
        if ((this.iConfig == null && other.iConfig != null) || (this.iConfig != null && !this.iConfig.equals(other.iConfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.PontoConfig[ iConfig=" + iConfig + " ]";
    }
    
}
