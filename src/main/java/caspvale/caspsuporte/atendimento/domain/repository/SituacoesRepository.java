package caspvale.caspsuporte.atendimento.domain.repository;

import caspvale.caspsuporte.atendimento.domain.model.CaspSituacoes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hilton
 */
public interface SituacoesRepository extends JpaRepository<CaspSituacoes, Integer> {
    
}