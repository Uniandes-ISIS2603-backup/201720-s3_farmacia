/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.OrdenDeCompraEntity;

/**
 *clase que muestra la informacion completa de una Orden De Compra
 * @author a.gracia10
 */
public class OrdenDeCompraDetailDTO extends OrdenDeCompraDTO { 
    
    //TODO hacer la relacion entre los productos
    
    /**
     * Constructor por defecto
     */
    public OrdenDeCompraDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public OrdenDeCompraDetailDTO(OrdenDeCompraEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return la entidad que tambien representa el dto
     */
    @Override
    public OrdenDeCompraEntity toEntity() {
        OrdenDeCompraEntity ent = super.toEntity();
        return ent;
    }
    
}
