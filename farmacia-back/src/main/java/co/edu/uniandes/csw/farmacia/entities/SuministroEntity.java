/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;



import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 *
 * @author lm.gonzalezf
 */
@Entity
public class SuministroEntity extends BaseEntity implements Serializable {
    
    private String nombre;
    private String informacion;
    
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private ItemEntity itemAsociado;

    public String getNombre()
    {
        return nombre;
    }
    
    public String getInfo()
    {
        return informacion;
    }
    
    public void setNombre(String nombrexd)
    {
        this.nombre = nombrexd;
    }
    
    public void setInfo(String infoxd)
    {
        this.informacion = infoxd;
    }
}