/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package caspvale.caspsuporte.atendimento.domain.repository;

import caspvale.caspsuporte.atendimento.domain.model.CaspAreas;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Hilton
 */
public interface AreasRepository extends JpaRepository<CaspAreas, Integer> {
    
    @Query("SELECT c FROM CaspAreas c WHERE c.situacaoArea like %:situacao% and :usuario MEMBER OF c.caspUsuariosList ORDER BY c.descricaoArea ASC")
    public List<CaspAreas> areasDoUsuario(String situacao, CaspUsuarios usuario);
    
    @Query("SELECT c FROM CaspAreas c WHERE c.iArea in :listaIAreas ORDER BY c.descricaoArea ASC")
    public List<CaspAreas> areasDoChamado(List<Integer> listaIAreas);
    
}
