/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author lm.gonzalezf
 */
@Entity
public class ItemEntity implements Serializable{

    @Id
    private Long id;
    private int numItemsVendidos;
    private int numItemsProvistos;
    private int numItemsEntregados;
    
    @OneToOne
    @PodamExclude
    @XmlInverseReference(mappedBy="itemAsociado")
    private ProductoEntity ProductoEntity;
    
    private SuministroEntity suministroAsociado;
    
    private Long costo;
    
    

    
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

    public ProductoEntity getProductoEntity() {
        return ProductoEntity;
    }

    public void setProductoEntity(ProductoEntity ProductoEntity) {
        this.ProductoEntity = ProductoEntity;
    }

    public SuministroEntity getSuministroAsociado() {
        return suministroAsociado;
    }

    public void setSuministroAsociado(SuministroEntity SuministroEntity) {
        this.suministroAsociado = SuministroEntity;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
       
}