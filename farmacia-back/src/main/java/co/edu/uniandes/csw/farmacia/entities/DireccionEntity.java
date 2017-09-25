/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author a.bravo
 */
@Entity
public class DireccionEntity extends BaseEntity implements Serializable{
    private String direccion;
    private String ciudad;
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String edad) {
        this.ciudad = ciudad;
    }
}
