/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author hs.hernandez
 */
@Entity
public class FacturaEntity extends BaseEntity implements Serializable{
    private int totalFactura ;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity clientes;

    
    public ClienteEntity getClientes() {
        return clientes;
    }

    public void setClientes(ClienteEntity clientes) {
        this.clientes = clientes;
    }
    
    public int getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(int totalFactura) {
        this.totalFactura = totalFactura;
    }
    
    
}
