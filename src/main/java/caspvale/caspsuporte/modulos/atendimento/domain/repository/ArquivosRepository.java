/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.domain.repository;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspArquivos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hilton
 */
public interface ArquivosRepository extends JpaRepository<CaspArquivos, Integer> {

}