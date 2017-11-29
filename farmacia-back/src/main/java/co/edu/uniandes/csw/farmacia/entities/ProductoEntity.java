/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author lm.gonzalezf
 */
@Entity
public class ProductoEntity extends BaseEntity implements Serializable{
    
    private String name;
    private String descripcion;
    private String proveedor;
    private long costo;
    private long cantidad;
    
    @PodamExclude
    @ManyToOne
    private CarritoEntity carrito;
    
    
    @PodamExclude
    @OneToOne(mappedBy="ProductoEntity",cascade = CascadeType.REMOVE,  orphanRemoval=true)
    @XmlInverseReference(mappedBy="ProductoEntity")
    private ItemEntity itemAsociado;

    public ItemEntity getItemAsociado() {
        return itemAsociado;
    }

    public void setItemAsociado(ItemEntity itemAsociado) {
        this.itemAsociado = itemAsociado;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String getName()
    {
        return name;
    }
    
    public String getDescripcion()
    {
        return descripcion;
    }
    
    @Override
    public void setName(String nombrexd)
    {
        this.name = nombrexd;
    }
    
    public void setDescripcion(String infoxd)
    {
        this.descripcion = infoxd;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }

    public CarritoEntity getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoEntity carrito) {
        this.carrito = carrito;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
    
    
}