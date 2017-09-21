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
    private Date fecha;
    private int totalfactura;

    public FacturaDTO(){}
    
    public FacturaDTO(FacturaEntity en){
        this.id = en.getId();
        this.totalfactura = en.getTotalFactura();
    }
    
    public FacturaEntity toEntity(){
        FacturaEntity en = new FacturaEntity();
        en.setId(this.getId());
        en.setTotalFactura(this.totalfactura);
        return en;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTotalfactura() {
        return totalfactura;
    }

    public void setTotalfactura(int totalfactura) {
        this.totalfactura = totalfactura;
    }
    
}
