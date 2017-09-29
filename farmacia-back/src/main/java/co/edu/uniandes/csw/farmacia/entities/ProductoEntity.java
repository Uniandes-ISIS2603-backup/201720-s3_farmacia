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
    private String nombre;
    private String informacion;
    
    @OneToMany()
    private List<MultimediaEntity> listaMultimedia = new ArrayList<MultimediaEntity>();
    
    
    
    
    public List<MultimediaEntity> getMultimedia()
    {
        return this.listaMultimedia;
    }
    
    public void setMultimedia(List<MultimediaEntity> listanueva)
    {
        this.listaMultimedia = listanueva;
    }
    


    public String getName()
    {
        return nombre;
    }
    
    public String getInfo()
    {
        return informacion;
    }
    
    public void setName(String nombrexd)
    {
        this.nombre = nombrexd;
    }
    
    public void setInfo(String infoxd)
    {
        this.informacion = infoxd;
    }
    
}