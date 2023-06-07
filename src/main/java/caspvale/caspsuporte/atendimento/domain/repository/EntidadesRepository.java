package caspvale.caspsuporte.atendimento.domain.repository;

import caspvale.caspsuporte.atendimento.domain.model.CaspEntidades;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Hilton
 */
public interface EntidadesRepository extends JpaRepository<CaspEntidades, Integer> {
    
    @Query("SELECT c FROM CaspEntidades c "
            + "WHERE c.situacaoEntidade like %:situacao% "
            + "and :usuario MEMBER OF c.caspUsuariosList "
            + "ORDER BY c.nomeEntidade ASC")
    public List<CaspEntidades> entidadesDoUsuario(String situacao, CaspUsuarios usuario);
}
