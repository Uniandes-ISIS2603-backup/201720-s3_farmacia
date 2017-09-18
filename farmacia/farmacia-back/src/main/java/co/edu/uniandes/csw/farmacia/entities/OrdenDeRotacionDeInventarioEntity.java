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
 * @author a.gracia10
 */
@Entity
public class OrdenDeRotacionDeInventarioEntity extends BaseEntity implements Serializable {
    String justificacion;
    String encargado; //que verga es esto?
    
    public String getJustificacion()
    {
        return justificacion;
    }
    
    public void setJustificacion(String just)
    {
        this.justificacion = just;
    }
    
    public String getEncargado()
    {
        return encargado;
    }
    
    public void setEncargado(String enc)
    {
        this.encargado = enc;
    }
}