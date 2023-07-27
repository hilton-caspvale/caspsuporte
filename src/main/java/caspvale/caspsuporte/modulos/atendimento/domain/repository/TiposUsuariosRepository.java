/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.domain.repository;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspTiposUsuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Hilton
 */
public interface TiposUsuariosRepository extends JpaRepository<CaspTiposUsuarios, Integer> {
    
    @Query("SELECT c FROM CaspTiposUsuarios c WHERE c.descricaoTipoUsuario = ?1")
    public CaspTiposUsuarios findByDescricaoTipoUsuario(String descricaoTipo);
    
    @Query("SELECT c FROM CaspTiposUsuarios c WHERE c.descricaoTipoUsuario in('ANALISTA','ADMINISTRADOR')")
    public List<CaspTiposUsuarios> buscarTiposNaoClientes();
    
    @Query("SELECT c FROM CaspTiposUsuarios c WHERE c.descricaoTipoUsuario in('CLIENTE')")
    public CaspTiposUsuarios buscarTiposClientes();
    
}
