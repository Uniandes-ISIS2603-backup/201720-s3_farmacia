/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.ItemInventarioEntity;
import java.util.Date;

/**
 *
 * @author a.gracia10
 */
public class ItemInventarioDTO  {
    
    private long id;
    private String codigo;
    private Date vencimiento;
    //private OrdenDeRotacionDeInventarioDTO ord;
    
    public ItemInventarioDTO()
    {
        
    }
    
    public ItemInventarioDTO(ItemInventarioEntity ent)
    {
        this.id = ent.getId();
        this.codigo= ent.getCodigoDeBarras();
        this.vencimiento = ent.getVencimiento();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }
    
    public ItemInventarioEntity toEntity()
    {
        ItemInventarioEntity ent = new ItemInventarioEntity();
        ent.setCodigoDeBarras(this.codigo);
        ent.setId(this.id);
        ent.setVencimiento(this.vencimiento);
        return ent;
    }
            
    
}
