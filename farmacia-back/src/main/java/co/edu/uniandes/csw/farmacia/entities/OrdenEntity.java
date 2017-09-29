/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author jp.carreno
 */
@Entity
public class OrdenEntity extends BaseEntity implements Serializable{
    
    private String tipo;
    
    @ManyToOne
    private FarmaciaEntity farmacia;
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
    
    public String getTipo()
    {
        return tipo;
    }
            
}