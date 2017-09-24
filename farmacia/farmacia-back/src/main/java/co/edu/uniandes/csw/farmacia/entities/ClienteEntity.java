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
    
    
    private int edad;
    
    @PodamExclude
    @OneToMany(mappedBy = "clientes")
    private ArrayList<FacturaEntity> facturas;

    public ArrayList<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<FacturaEntity> facturas) {
        this.facturas = facturas;
    }


    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
