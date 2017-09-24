/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author hs.hernandez
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable{
    private String nombre;
    private int edad;
    
    @PodamExclude
    @OneToMany(mappedBy = "clientes")
    private ArrayList<FacturaEntity> facturas = new ArrayList<FacturaEntity>();

    public ArrayList<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<FacturaEntity> facturas) {
        this.facturas = facturas;
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
}
