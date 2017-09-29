/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.OrdenEntity;


/**
 *
 * @author jp.carreno
 */
public class OrdenDTO {
    
    private String nombre;
    private Long id;
    private String tipo;
    
    /**
    * Constructor por defecto
    */
    public OrdenDTO(){}
    
    /**
     * Constructor
     * @param entity 
     */
    public OrdenDTO(OrdenEntity entity)
    {
        if(entity != null)
        {
            this.nombre = entity.getName();
            this.id = entity.getId();
            this.tipo = entity.getTipo();
        }
    }
    
    public OrdenEntity toEntity()
    {
        OrdenEntity e = new OrdenEntity();
        e.setId(this.id);
        e.setName(this.nombre);
        e.setTipo(this.tipo);
        return e;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public String getTipo()
    {
        return tipo;
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
}
