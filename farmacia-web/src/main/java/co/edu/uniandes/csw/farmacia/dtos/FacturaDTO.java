/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import java.util.Date;

/**
 *
 * @author hs.hernandez
 */
public class FacturaDTO {
   
    private Long id;
    private String fecha;
    private Long totalfactura;

    public FacturaDTO(){}
    
    public FacturaDTO(FacturaEntity en){
        this.id = en.getId();
        this.totalfactura = en.getTotalFactura();
        this.fecha = en.getFecha();
        
    }
    
    public FacturaEntity toEntity(){
        FacturaEntity en = new FacturaEntity();
        en.setId(this.getId());
        en.setTotalFactura(this.totalfactura);
        en.setFecha(this.fecha);
        
        return en;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Long getTotalfactura() {
        return totalfactura;
    }

    public void setTotalfactura(Long totalfactura) {
        this.totalfactura = totalfactura;
    }
    
}
