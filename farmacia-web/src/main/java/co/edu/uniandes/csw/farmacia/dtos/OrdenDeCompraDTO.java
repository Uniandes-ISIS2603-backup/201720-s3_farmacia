/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.OrdenDeCompraEntity;
import java.util.Date;

/**
 *metodo que tiene lo mas basico de una orden de compra(id, nombre)
 * @author a.gracia10
 */
public class OrdenDeCompraDTO {
    
    private long id;
    private String nombre;
    private Long costoTotal;
    private Date fecha;
   
    
    
    public OrdenDeCompraDTO()
    {}
    
    /**
     * constructor de un dto de un entity
     * @param ent entity del que se construye el dto
     */
    public OrdenDeCompraDTO( OrdenDeCompraEntity ent)
    {
        this.id = ent.getId();
        this.nombre = ent.getName();
        this.costoTotal = ent.getCostoTotal();
        this.fecha = ent.getFecha();
        
    }
    
    public Long getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Long costoTotal) {
        this.costoTotal = costoTotal;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return nombre;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.nombre = name;
    }
    
    public OrdenDeCompraEntity toEntity()
    {
        OrdenDeCompraEntity ent = new OrdenDeCompraEntity();
        ent.setId(this.id);
        ent.setName(this.nombre);
        ent.setCostoTotal(this.costoTotal);
        ent.setFecha(this.fecha);
        
        return ent;
    }
    
}
