/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hs.hernandez
 */
public class ClienteDetailDTO extends ClienteDTO{
    
    /**
     * Relacion a cero o muchas facturas
    */
    private List<FacturaDTO> facturas;

    
    
    public ClienteDetailDTO(){
        super();
    }
    
    public ClienteDetailDTO(ClienteEntity en){
        super(en);
        if(en.getFacturas() != null){
            facturas = new ArrayList<>();
            for(FacturaEntity em : en.getFacturas())
                facturas.add(new FacturaDTO(em));
        }

    }
    @Override
    public ClienteEntity toEntity(){
        ClienteEntity enti = super.toEntity();
       if(getFacturas() != null){
             
            ArrayList<FacturaEntity> facEnti = new ArrayList<>();
            for (FacturaDTO dtofac : getFacturas()) {
                facEnti.add(dtofac.toEntity());
            }
            enti.setFacturas(facEnti);
        }
        return enti;
    }
    
    public List<FacturaDTO> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<FacturaDTO> facturas) {
        this.facturas = facturas;
    }
}
