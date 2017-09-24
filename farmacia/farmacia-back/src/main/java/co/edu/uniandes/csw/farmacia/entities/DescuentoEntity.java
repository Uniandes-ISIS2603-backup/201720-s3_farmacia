
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jp.carreno
 */
@Entity
public class DescuentoEntity extends BaseEntity implements Serializable{
    
    private Double porcentaje;
    
    private String condiciones;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaIn;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;
    
    public Double getPorcentaje(){
        return porcentaje;
    }
    
    public void setPorcentaje(Double porcentaje){
        this.porcentaje = porcentaje;
    }
    
    public String getCondiciones(){
        return condiciones;
    }
    
    public void setCondiciones(String condiciones){
        this.condiciones = condiciones;
    }
    
    public Date getFechaIn(){
        return fechaIn;
    }
    
    public void setFechaIn(Date fechaIn){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        date.format(fechaIn);
        this.fechaIn = fechaIn;
    }
    
    public Date getFechaFin(){
        return fechaFin;
    }
    
    public void setFechaFin(Date fechaFin){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        date.format(fechaFin);
        this.fechaFin = fechaFin;
    }
    
}
