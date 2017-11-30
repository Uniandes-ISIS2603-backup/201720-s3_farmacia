/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.CarritoEntity;

/**
 *
 * @author hs.hernandez
 */
public class CarritoDTO {
    private Long totalCarrito;
    private Long id;
    private Long idCliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Constructor vacio
     */
    public CarritoDTO(){}
    
    /**
     * Constructor real
     * @param entity 
     */
    
    public CarritoDTO(CarritoEntity entity){
        if(entity != null && !entity.getProductos().isEmpty()){
            this.totalCarrito = entity.getTotalCarrito();
            this.id = entity.getId();
            this.idCliente =0L;
        }
    }
    
    /**
     * Pasar a entity
     * @return 
     */
    public CarritoEntity toEntity(){
        CarritoEntity en = new CarritoEntity();
        en.setTotalCarrito(this.totalCarrito);
        en.setId(this.id);
         en.setIdCliente(0L);
        return en;
    }

    /**
     * Total carrito
     * @return 
     */
    public Long getTotalCarrito() {
        return totalCarrito;
    }

    /**
     * Modifica el total 
     */
    public void setTotalCarrito(Long totalCarrito) {
        this.totalCarrito = totalCarrito;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    
}
