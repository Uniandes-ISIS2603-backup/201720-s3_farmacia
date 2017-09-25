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
 * @author lm.gonzalezf
 */
@Entity
public class MultimediaEntity extends BaseEntity implements Serializable{
    private String nombre;
    private String productoAsociado;
    private String tipo;
    

    public String getNombre()
    {
        return nombre;
    }
    
    public String getProductoAsoc()
    {
        return productoAsociado;
    }
    
    public String getTipo()
    {
        return tipo;
    }
    
    public void setNombre(String nombrexd)
    {
        this.nombre = nombrexd;
    }
    
    public void setProductoAsociado(String productoxd)
    {
        this.productoAsociado = productoxd;
    }
    
    public void setTipo(String tipoxd)
    {
        this.tipo = tipoxd;
    }
    
}
