package caspvale.caspsuporte.modulos.atendimento.domain.repository;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspChamados;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Hilton
 */
public interface ChamadosRepository extends JpaRepository<CaspChamados, Integer> {

    @Query(value = "SELECT * FROM casp1.casp_chamados c inner join casp1.casp_situacoes s on c.i_situacao = s.i_situacao\n"
            + "       where s.situacao in('A')\n"
            + "       and c.i_chamado in(select ca.i_chamado from casp1.casp_chamados_areas ca\n"
            + "                           where ca.i_area in(select ua.i_area from casp1.casp_usuarios_areas ua\n"
            + "                                                inner join casp1.casp_usuarios u on u.i_usuario = ua.i_usuario\n"
            + "                                                where ua.i_area = ca.i_area and u.nlogin = ?1))\n"
            + "       and c.i_chamado in(select ce.i_chamado from casp1.casp_chamados_entidades ce\n"
            + "                           where ce.i_entidades in(select ue.i_entidade from casp1.casp_usuarios_entidades ue \n"
            + "                                                    inner join casp1.casp_usuarios u on u.i_usuario = ue.i_usuario\n"
            + "                                                    where ue.i_entidade = ce.i_entidades and u.nlogin = ?1))\n"
            + "       and c.i_chamado in(select cs.i_chamado from casp1.casp_chamados_sistemas cs \n"
            + "                           where cs.i_sistema in(select us.i_sistema from casp1.casp_usuarios_sistemas us \n"
            + "                                                  inner join casp1.casp_usuarios u on u.i_usuario = us.i_usuario\n"
            + "                                                  where us.i_sistema = cs.i_sistema and u.nlogin = ?1))",
            nativeQuery = true)
    public List<CaspChamados> aguardandoAreaEntidadeSistema(String nlogin);

    @Query(value = "SELECT count(c.i_chamado) FROM casp1.casp_chamados c inner join casp1.casp_situacoes s on c.i_situacao = s.i_situacao\n"
            + "       where s.situacao in('A')\n"
            + "       and c.i_chamado in(select ca.i_chamado from casp1.casp_chamados_areas ca\n"
            + "                           where ca.i_area in(select ua.i_area from casp1.casp_usuarios_areas ua\n"
            + "                                                inner join casp1.casp_usuarios u on u.i_usuario = ua.i_usuario\n"
            + "                                                where ua.i_area = ca.i_area and u.nlogin = ?1))\n"
            + "       and c.i_chamado in(select ce.i_chamado from casp1.casp_chamados_entidades ce\n"
            + "                           where ce.i_entidades in(select ue.i_entidade from casp1.casp_usuarios_entidades ue \n"
            + "                                                    inner join casp1.casp_usuarios u on u.i_usuario = ue.i_usuario\n"
            + "                                                    where ue.i_entidade = ce.i_entidades and u.nlogin = ?1))\n"
            + "       and c.i_chamado in(select cs.i_chamado from casp1.casp_chamados_sistemas cs \n"
            + "                           where cs.i_sistema in(select us.i_sistema from casp1.casp_usuarios_sistemas us \n"
            + "                                                  inner join casp1.casp_usuarios u on u.i_usuario = us.i_usuario\n"
            + "                                                  where us.i_sistema = cs.i_sistema and u.nlogin = ?1))",
            nativeQuery = true)
    public Integer aguardandoAreaEntidadeSistemaSize(String nlogin);

    @Query(value = "SELECT * FROM casp1.casp_chamados c inner join casp1.casp_situacoes s on c.i_situacao = s.i_situacao\n"
            + "       where s.situacao in('R')\n"
            + "       and c.i_chamado in(select ca.i_chamado from casp1.casp_chamados_areas ca\n"
            + "                           where ca.i_area in(select ua.i_area from casp1.casp_usuarios_areas ua\n"
            + "                                                inner join casp1.casp_usuarios u on u.i_usuario = ua.i_usuario\n"
            + "                                                where ua.i_area = ca.i_area and u.nlogin = ?1))\n"
            + "       and c.i_chamado in(select ce.i_chamado from casp1.casp_chamados_entidades ce\n"
            + "                           where ce.i_entidades in(select ue.i_entidade from casp1.casp_usuarios_entidades ue \n"
            + "                                                    inner join casp1.casp_usuarios u on u.i_usuario = ue.i_usuario\n"
            + "                                                    where ue.i_entidade = ce.i_entidades and u.nlogin = ?1))\n"
            + "       and c.i_chamado in(select cs.i_chamado from casp1.casp_chamados_sistemas cs \n"
            + "                           where cs.i_sistema in(select us.i_sistema from casp1.casp_usuarios_sistemas us \n"
            + "                                                  inner join casp1.casp_usuarios u on u.i_usuario = us.i_usuario\n"
            + "                                                  where us.i_sistema = cs.i_sistema and u.nlogin = ?1))",
            nativeQuery = true)
    public List<CaspChamados> emAnaliseAreaEntidadeSistema(String nlogin);

    @Query(value = "SELECT count(c.i_chamado) FROM casp1.casp_chamados c inner join casp1.casp_situacoes s on c.i_situacao = s.i_situacao\n"
            + "       where s.situacao in('R')\n"
            + "       and c.i_chamado in(select ca.i_chamado from casp1.casp_chamados_areas ca\n"
            + "                           where ca.i_area in(select ua.i_area from casp1.casp_usuarios_areas ua\n"
            + "                                                inner join casp1.casp_usuarios u on u.i_usuario = ua.i_usuario\n"
            + "                                                where ua.i_area = ca.i_area and u.nlogin = ?1))\n"
            + "       and c.i_chamado in(select ce.i_chamado from casp1.casp_chamados_entidades ce\n"
            + "                           where ce.i_entidades in(select ue.i_entidade from casp1.casp_usuarios_entidades ue \n"
            + "                                                    inner join casp1.casp_usuarios u on u.i_usuario = ue.i_usuario\n"
            + "                                                    where ue.i_entidade = ce.i_entidades and u.nlogin = ?1))\n"
            + "       and c.i_chamado in(select cs.i_chamado from casp1.casp_chamados_sistemas cs \n"
            + "                           where cs.i_sistema in(select us.i_sistema from casp1.casp_usuarios_sistemas us \n"
            + "                                                  inner join casp1.casp_usuarios u on u.i_usuario = us.i_usuario\n"
            + "                                                  where us.i_sistema = cs.i_sistema and u.nlogin = ?1))",
            nativeQuery = true)
    public Integer emAnaliseAreaEntidadeSistemaSize(String nlogin);

    @Query(value = "SELECT * FROM casp1.casp_chamados c inner join casp1.casp_situacoes s on c.i_situacao = s.i_situacao\n"
            + "       where s.situacao in('C','E')\n"
            + "       and c.i_chamado in(select ca.i_chamado from casp1.casp_chamados_areas ca\n"
            + "                           where ca.i_area in(select ua.i_area from casp1.casp_usuarios_areas ua\n"
            + "                                                inner join casp1.casp_usuarios u on u.i_usuario = ua.i_usuario\n"
            + "                                                where ua.i_area = ca.i_area and u.nlogin = ?1))\n"
            + "       and c.i_chamado in(select ce.i_chamado from casp1.casp_chamados_entidades ce\n"
            + "                           where ce.i_entidades in(select ue.i_entidade from casp1.casp_usuarios_entidades ue \n"
            + "                                                    inner join casp1.casp_usuarios u on u.i_usuario = ue.i_usuario\n"
            + "                                                    where ue.i_entidade = ce.i_entidades and u.nlogin = ?1))\n"
            + "       and c.i_chamado in(select cs.i_chamado from casp1.casp_chamados_sistemas cs \n"
            + "                           where cs.i_sistema in(select us.i_sistema from casp1.casp_usuarios_sistemas us \n"
            + "                                                  inner join casp1.casp_usuarios u on u.i_usuario = us.i_usuario\n"
            + "                                                  where us.i_sistema = cs.i_sistema and u.nlogin = ?1))",
            nativeQuery = true)
    public List<CaspChamados> finalizadosAreaEntidadeSistema(String nlogin);

    @Query(value = "SELECT count(c.i_chamado) FROM casp1.casp_chamados c inner join casp1.casp_situacoes s on c.i_situacao = s.i_situacao\n"
            + "       where s.situacao in('C','E')\n"
            + "       and c.i_chamado in(select ca.i_chamado from casp1.casp_chamados_areas ca\n"
            + "                           where ca.i_area in(select ua.i_area from casp1.casp_usuarios_areas ua\n"
            + "                                                inner join casp1.casp_usuarios u on u.i_usuario = ua.i_usuario\n"
            + "                                                where ua.i_area = ca.i_area and u.nlogin = ?1))\n"
            + "       and c.i_chamado in(select ce.i_chamado from casp1.casp_chamados_entidades ce\n"
            + "                           where ce.i_entidades in(select ue.i_entidade from casp1.casp_usuarios_entidades ue \n"
            + "                                                    inner join casp1.casp_usuarios u on u.i_usuario = ue.i_usuario\n"
            + "                                                    where ue.i_entidade = ce.i_entidades and u.nlogin = ?1))\n"
            + "       and c.i_chamado in(select cs.i_chamado from casp1.casp_chamados_sistemas cs \n"
            + "                           where cs.i_sistema in(select us.i_sistema from casp1.casp_usuarios_sistemas us \n"
            + "                                                  inner join casp1.casp_usuarios u on u.i_usuario = us.i_usuario\n"
            + "                                                  where us.i_sistema = cs.i_sistema and u.nlogin = ?1))",
            nativeQuery = true)
    public Integer finalizadosAreaEntidadeSistemaSize(String nlogin);

    @Query("SELECT c FROM CaspChamados c WHERE c.iSituacao.situacao in('A') "
            + "and (c.iUsuarioAbertura.nlogin = :usuario or c.iUsuarioAtendimento.nlogin = :usuario)")
    public List<CaspChamados> aguardandoClienteUsuario(String usuario);

    @Query("SELECT count(c.iChamado) FROM CaspChamados c WHERE c.iSituacao.situacao in('A') "
            + "and (c.iUsuarioAbertura.nlogin = :usuario or c.iUsuarioAtendimento.nlogin = :usuario)")
    public Integer aguardandoClienteUsuarioSize(String usuario);

    @Query("SELECT c FROM CaspChamados c WHERE c.iSituacao.situacao in('R') "
            + "and (c.iUsuarioAbertura = :usuario or c.iUsuarioAtendimento = :usuario or c.iUsuarioEncaminhamento = :usuario)")
    public List<CaspChamados> emAnaliseUsuario(CaspUsuarios usuario);
    
    @Query("SELECT count(c.iChamado) FROM CaspChamados c WHERE c.iSituacao.situacao in('R') "
            + "and (c.iUsuarioAbertura = :usuario or c.iUsuarioAtendimento = :usuario or c.iUsuarioEncaminhamento = :usuario)")
    public Integer emAnaliseUsuarioSize(CaspUsuarios usuario);

    /*@Query("SELECT count(c.iChamado) FROM CaspChamados c WHERE c.iSituacao.situacao in('R') "
            + "and (c.iUsuarioAbertura.nlogin = :usuario or c.iUsuarioAtendimento.nlogin = :usuario or c.iUsuarioEncaminhamento.nlogin = :usuario)")
    public Integer emAnaliseClienteUsuarioSize(String usuario);

    @Query("SELECT c FROM CaspChamados c WHERE c.iSituacao.situacao in('C','E') "
            + "and (c.iUsuarioAbertura.nlogin = :usuario or c.iUsuarioAtendimento.nlogin = :usuario or c.iUsuarioEncaminhamento.nlogin = :usuario or c.iUsuarioEncerramento.nlogin = :usuario)")
    public List<CaspChamados> finalizadosClienteUsuario(String usuario);

    @Query("SELECT count(c.iChamado) FROM CaspChamados c WHERE c.iSituacao.situacao in('C','E') "
            + "and (c.iUsuarioAbertura.nlogin = :usuario or c.iUsuarioAtendimento.nlogin = :usuario or c.iUsuarioEncaminhamento.nlogin = :usuario or c.iUsuarioEncerramento.nlogin = :usuario)")
    public Integer finalizadosClienteUsuarioSize(String usuario);*/

    @Query("SELECT c FROM CaspChamados c WHERE c.iSituacao.situacao in('A')")
    public List<CaspChamados> aguardando();

    @Query("SELECT count(c.iChamado) FROM CaspChamados c WHERE c.iSituacao.situacao in('A')")
    public Integer aguardandoSize();

    @Query("SELECT c FROM CaspChamados c WHERE c.iSituacao.situacao in('R')")
    public List<CaspChamados> emAnalise();

    @Query("SELECT count(c.iChamado) FROM CaspChamados c WHERE c.iSituacao.situacao in('R')")
    public Integer emAnaliseSize();

    @Query("SELECT c FROM CaspChamados c WHERE c.iSituacao.situacao in('C','E')")
    public List<CaspChamados> finalizados();

    @Query("SELECT count(c.iChamado) FROM CaspChamados c WHERE c.iSituacao.situacao in('C','E')")
    public Integer finalizadosSize();
}
