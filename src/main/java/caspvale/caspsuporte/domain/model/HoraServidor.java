/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "hora_servidor",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HoraServidor.findAll", query = "SELECT h FROM HoraServidor h"),
    @NamedQuery(name = "HoraServidor.findByNow", query = "SELECT h FROM HoraServidor h WHERE h.now = :now")})
public class HoraServidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "now")
    //@Temporal(TemporalType.TIMESTAMP)
    @Id
    private LocalDateTime now;

    public HoraServidor() {
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }
    
}
