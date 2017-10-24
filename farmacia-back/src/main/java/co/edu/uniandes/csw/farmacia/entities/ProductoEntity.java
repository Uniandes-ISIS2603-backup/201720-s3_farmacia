/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


/**
 *
 * @author lm.gonzalezf
 */
@Entity
public class ProductoEntity extends BaseEntity implements Serializable{
    private String name;
    private String descripcion;
    private String proveedor;
    


    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getName()
    {
        return name;
    }
    
    public String getDescripcion()
    {
        return descripcion;
    }
    
    public void setName(String nombrexd)
    {
        this.name = nombrexd;
    }
    
    public void setDescripcion(String infoxd)
    {
        this.descripcion = infoxd;
    }
    
}