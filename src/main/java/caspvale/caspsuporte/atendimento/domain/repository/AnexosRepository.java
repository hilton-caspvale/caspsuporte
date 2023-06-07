/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package caspvale.caspsuporte.atendimento.domain.repository;

import caspvale.caspsuporte.atendimento.domain.model.CaspAnexos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hilton
 */
public interface AnexosRepository extends JpaRepository<CaspAnexos, Integer> {
    
}
