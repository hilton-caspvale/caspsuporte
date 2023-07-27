package caspvale.caspsuporte.modulos.atendimento.domain.repository;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspAreas;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspEntidades;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspSistemas;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Hilton
 */
public interface UsuariosRepository extends JpaRepository<CaspUsuarios, Integer> {

    public Optional<CaspUsuarios> findByNlogin(String nlogin);

    public Optional<CaspUsuarios> findByEmailUsuario(String emailUsuario);

    @Query("Select c from CaspUsuarios c where "
            + "(c.nlogin like %?1% )"
            + " AND (c.emailUsuario like %?2%)"
            + " AND (c.nomeUsuario like %?3%)"
            + " AND (c.situacaoUsuario like %?4%)"
            + " AND (c.iTipoUsuario.descricaoTipoUsuario like %?5%) order by c.nomeUsuario asc")
    public List<CaspUsuarios> buscarPorCampos(String nlogin, String email, String nome, String situacao, String descricaoTipoUsuario);

    @Query("Select c from CaspUsuarios c where c.iTipoUsuario.descricaoTipoUsuario = 'CLIENTE' AND c.situacaoUsuario like %?1% order by c.nomeUsuario asc")
    public List<CaspUsuarios> buscarClientes(String situacao);
    
    @Query("Select c from CaspUsuarios c \n"
            + "WHERE (:ientidade = 0 or :entidade MEMBER OF c.caspEntidadesList)\n"
            + "and (:iarea = 0 or :area MEMBER OF c.caspAreasList)\n"
            + "and (:isistema = 0 or :sistema MEMBER OF c.caspSistemasList)"
            + "AND (c.iTipoUsuario.descricaoTipoUsuario <> 'CLIENTE')"
            + "AND (c.situacaoUsuario = :situacao or :situacao = 'TODOS') order by c.nomeUsuario asc")
    public List<CaspUsuarios> usuariosPorFiltros(CaspEntidades entidade, Integer ientidade, CaspAreas area, Integer iarea, CaspSistemas sistema, Integer isistema, String situacao);
    
    @Query("Select c from CaspUsuarios c \n"
            + "WHERE (:ientidade = 0 or :entidade MEMBER OF c.caspEntidadesList)\n"
            + "and (:iarea = 0 or :area MEMBER OF c.caspAreasList)\n"
            + "and (:isistema = 0 or :sistema MEMBER OF c.caspSistemasList)"
            + "AND (c.iTipoUsuario.descricaoTipoUsuario = 'CLIENTE')"
            + "AND (c.situacaoUsuario = :situacao or :situacao = 'TODOS') order by c.nomeUsuario asc")
    public List<CaspUsuarios> clientesPorFiltros(CaspEntidades entidade, Integer ientidade, CaspAreas area, Integer iarea, CaspSistemas sistema, Integer isistema, String situacao);   
    
    @Query("Select c from CaspUsuarios c \n"
            + "WHERE (:ientidade = 0 or :entidade MEMBER OF c.caspEntidadesList)\n"
            + "and (:iarea = 0 or :area MEMBER OF c.caspAreasList)\n"
            + "and (:isistema = 0 or :sistema MEMBER OF c.caspSistemasList)"
            + "AND (:sometneClientes = false or c.iTipoUsuario.descricaoTipoUsuario = 'CLIENTE')"
            + "AND (c.situacaoUsuario = :situacao or :situacao = 'TODOS') order by c.nomeUsuario asc")
    public List<CaspUsuarios> usuariosAbertura(boolean sometneClientes, CaspEntidades entidade, Integer ientidade, CaspAreas area, Integer iarea, CaspSistemas sistema, Integer isistema, String situacao);   
   
}
