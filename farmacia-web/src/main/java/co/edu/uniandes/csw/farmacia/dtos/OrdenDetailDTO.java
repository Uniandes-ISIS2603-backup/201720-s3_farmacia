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
public class OrdenDetailDTO extends OrdenDTO{
    
    /**
     * Constructor por defecto
     */
    public OrdenDetailDTO(){   
    }
    
    /**
     * Constructor para transformar un entity a un DTO
     * @param entity 
     */
    public OrdenDetailDTO(OrdenEntity entity)
    {
        super(entity);
    }
    
    /**
     * Transforma un DTO en un Entity
     * @return 
     */
    @Override
    public OrdenEntity toEntity()
    {
        OrdenEntity e = super.toEntity();
        return e;
    }
}
