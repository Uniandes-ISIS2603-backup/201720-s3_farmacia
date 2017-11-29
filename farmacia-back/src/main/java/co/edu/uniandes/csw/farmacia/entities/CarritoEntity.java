/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author hs.hernandez
 */
@Entity
public class CarritoEntity extends BaseEntity implements Serializable{
 
    private Long totalCarrito;
    private Long idCliente;
    
    @PodamExclude
    @OneToMany(mappedBy = "carrito",fetch = FetchType.LAZY,orphanRemoval = false ,cascade = CascadeType.MERGE)
    private List<ProductoEntity> productos = new ArrayList<>();

    
    /**
     * Costo total del carrito
     * @return costoTotal
     */
    public Long getTotalCarrito() {
      totalCarrito =0L;
        if(!productos.isEmpty()){
            for(int i = 0; i < productos.size();i++){
                totalCarrito += (productos.get(i).getCosto()*productos.get(i).getCantidad());
            }
        }else{
            totalCarrito=0L;
        }
       return totalCarrito;
    }

    /**
     * 
     * @param totalCarrito 
     */
    public void setTotalCarrito(Long totalCarrito) {
        this.totalCarrito = totalCarrito;
    }

    /**
     * 
     * @return 
     */
    public List<ProductoEntity> getProductos() {    
        return productos;
    }

    /**
     * Set productos
     * @param productos 
     */
    public void setProductos(List<ProductoEntity> productos) {
        this.productos = productos;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    
    
}
