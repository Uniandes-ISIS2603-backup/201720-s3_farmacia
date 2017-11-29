/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.PagoEntity;

/**
 *
 * @author jp.carreno
 */
public class PagoDTO{
    private String nombre;
    private Long id;
    private Long idUsuario;
    private Double costo;

    /**
     * Constructor por defecto
     */
    public PagoDTO(){}
    
    /**
     * Constructor
     * @param entity 
     */
     public PagoDTO(PagoEntity entity){
         if(entity != null){
             this.nombre = entity.getName();
             this.id = entity.getId();
             this.costo = entity.getCosto();
             this.idUsuario = entity.getIdUsuario();
         }
     }
     
     public PagoEntity toEntity(){
         PagoEntity en = new PagoEntity();
         en.setCosto(this.costo);
         en.setId(this.id);
         en.setName(this.nombre);
         en.setIdUsuario(this.idUsuario);
         
         return en;
     }
     
     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
