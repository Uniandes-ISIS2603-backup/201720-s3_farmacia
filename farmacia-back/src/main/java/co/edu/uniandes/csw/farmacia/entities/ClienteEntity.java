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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author hs.hernandez
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable{
    private Integer edad;
    private Integer cedula;
    
    @PodamExclude
    @OneToMany(mappedBy = "clientes")
    private List<FacturaEntity> facturas = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<OrdenDeCompraEntity> ordenes = new ArrayList<>();
    

    public List<OrdenDeCompraEntity> getOrdenes(){
        return this.ordenes;
    }
    
    public void setOrdenes(List<OrdenDeCompraEntity> list){
        this.ordenes = list;
    }
    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }


    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    public int getCedula(){
        return cedula;
    }
    public void setCedula(int cedula){
        this.cedula = cedula;
    }
    
    
}
