/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package caspvale.caspsuporte.atendimento.domain.repository;

import caspvale.caspsuporte.atendimento.domain.model.CaspSistemas;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Hilton
 */
public interface SistemasRepository extends JpaRepository<CaspSistemas, Integer> {
    
    @Query("SELECT c FROM CaspSistemas c WHERE c.situacaoSistema like %:situacao% and :usuario MEMBER OF c.caspUsuariosList ORDER BY c.descricaoSistema")
    public List<CaspSistemas> sistemasDoUsuario(String situacao, CaspUsuarios usuario);
}
