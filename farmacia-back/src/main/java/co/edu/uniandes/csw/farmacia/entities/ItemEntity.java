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
import javax.persistence.OneToOne;


/**
 *
 * @author lm.gonzalezf
 */
@Entity
public class ItemEntity extends BaseEntity implements Serializable{
    private int numItemsVendidos;
    private int numItemsProvistos;
    private int numItemsEntregados;
    
    @OneToOne
    private ProductoEntity productoAsociado;
    
    private SuministroEntity suministroAsociado;
    
    private int costo;

    
    public int getNumItemsVendidos() {
        return numItemsVendidos;
    }

    public void setNumItemsVendidos(int numItemsVendidos) {
        this.numItemsVendidos = numItemsVendidos;
    }

    public int getNumItemsProvistos() {
        return numItemsProvistos;
    }

    public void setNumItemsProvistos(int numItemsProvistos) {
        this.numItemsProvistos = numItemsProvistos;
    }

    public int getNumItemsEntregados() {
        return numItemsEntregados;
    }

    public void setNumItemsEntregados(int numItemsEntregados) {
        this.numItemsEntregados = numItemsEntregados;
    }

    public ProductoEntity getProductoAsociado() {
        return productoAsociado;
    }

    public void setProductoAsociado(ProductoEntity productoAsociado) {
        this.productoAsociado = productoAsociado;
    }

    public SuministroEntity getSuministroAsociado() {
        return suministroAsociado;
    }

    public void setSuministroAsociado(SuministroEntity SuministroEntity) {
        this.suministroAsociado = SuministroEntity;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
       
}