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
public class OrdenDeRotacionDeInventarioDetailDTO extends OrdenDeRotacionDeInventarioDTO {
    
    /**
     * Constructor por defecto
     */
    public OrdenDeRotacionDeInventarioDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public OrdenDeRotacionDeInventarioDetailDTO(OrdenDeRotacionDeInventarioEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return la entidad que tambien representa el dto
     */
    @Override
    public OrdenDeRotacionDeInventarioEntity toEntity() {
        OrdenDeRotacionDeInventarioEntity ent = super.toEntity();
        return ent;
    }
    
}
