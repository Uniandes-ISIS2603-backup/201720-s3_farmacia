/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.OrdenDeRotacionDeInventarioEntity;

/**
 *
 * @author a.gracia10
 */
public class OrdenDeRotacionDeInventarioDTO {
    
    private long id;
    private String name;
    private String encargado;
    private String justificacion;
    
    public OrdenDeRotacionDeInventarioDTO()
    {
        
    }
    
    /**
     * constructor de un dto de un entity
     * @param ent entity del que se construye el dto
     */
    public OrdenDeRotacionDeInventarioDTO( OrdenDeRotacionDeInventarioEntity ent)
    {
        this.id = ent.getId();
        this.name = ent.getName();
        this.encargado = ent.getEncargado();
        this.justificacion = ent.getJustificacion();
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
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
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public OrdenDeRotacionDeInventarioEntity toEntity()
    {
        OrdenDeRotacionDeInventarioEntity ent = new OrdenDeRotacionDeInventarioEntity();
        ent.setId(this.id);
        ent.setName(this.name);
        ent.setEncargado(this.getEncargado());
        ent.setJustificacion(this.getJustificacion());
        return ent;
    }
}
