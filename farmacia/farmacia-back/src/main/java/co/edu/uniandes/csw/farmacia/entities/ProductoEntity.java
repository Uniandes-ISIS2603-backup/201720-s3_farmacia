/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


/**
 *
 * @author lm.gonzalezf
 */
@Entity
public class ProductoEntity extends BaseEntity implements Serializable{
    private String nombre;
    private String informacion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<MultimediaEntity> listaxd;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<DescuentoEntity> descuentosxd;
            
    

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
    
    public void agregarMultimedia (MultimediaEntity entity)
    {
        this.listaxd.add(entity);
    }
    
    public void eliminarMultimedia (int indexd)
    {
        this.listaxd.remove(indexd);
    }
    
    public void eliminarTodo()
    {
        this.listaxd = null;
    }
    
    public void setMultimedia(MultimediaEntity entity, int indexd)
    {
        this.listaxd.set(indexd, entity);
    }
    
    public void agregarDescuento (DescuentoEntity entity)
    {
        this.descuentosxd.add(entity);
    }
    
    public void eliminarDescuento (int indexd, DescuentoEntity entityxd)
    {
        this.descuentosxd.remove(indexd, entityxd);
    }
    
    public void eliminarTodo()
    {
        this.descuentosxd = null;
    }
    
    public void setDescuentos(DescuentoEntity entity, int indexd)
    {
        this.descuentosxd.set(indexd, entity);
    }
}