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
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author hs.hernandez
 */
@Entity
public class OrdenDeCompraEntity extends BaseEntity implements Serializable { 

       List<ItemEntity> items = new ArrayList<>();
       
       @PodamExclude
       @OneToOne
       FacturaEntity factura;
       
       Long costoTotal;

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    public Long getCostoTotal() {
      return costoTotal;
    }

    public void setCostoTotal(Long costoTotal) {
        this.costoTotal = costoTotal;
    }
       
       
}
    