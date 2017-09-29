/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author a.gracia10
 */
@Entity
public class ItemInventarioEntity implements Serializable {
            
            
            @Id
            @GeneratedValue (strategy = GenerationType.IDENTITY)
            private long id;
            //Codigo de barras del item en especifico
            private String codigoDeBarras;
            //fecha que vence el producto
            @Temporal (TemporalType.DATE)
            //
            private Date Vencimiento;

            
            
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

            
            
            
    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public Date getVencimiento() {
        return Vencimiento;
    }

    public void setVencimiento(Date Vencimiento) {
        this.Vencimiento = Vencimiento;
    }
            
            
}   
