/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;

/**
 *
 * @author hs.hernandez
 */
public class ClienteDTO{
    private String nombre;
    private int edad;
    private Long id;
    private Long cedula;

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    /**
     * Constructor por defecto
     */
    public ClienteDTO(){}
    
    /**
     * Constructor
     * @param entity 
     */
     public ClienteDTO(ClienteEntity entity){
         if(entity != null){
             this.nombre = entity.getName();
             this.id = entity.getId();
             this.edad = entity.getEdad();
             this.cedula = entity.getCedula();
         }
     }
     
     public ClienteEntity toEntity(){
         ClienteEntity en = new ClienteEntity();
         en.setEdad(this.edad);
         en.setId(this.id);
         en.setName(this.nombre);
         en.setCedula(this.cedula);
         
         return en;
     }
     
     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
