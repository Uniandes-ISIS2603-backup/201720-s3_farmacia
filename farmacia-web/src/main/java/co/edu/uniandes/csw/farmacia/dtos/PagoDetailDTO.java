/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.PagoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jp.carreno
 */
public class PagoDetailDTO extends PagoDTO{
    
    
    public PagoDetailDTO(){
        super();
    }
    
    public PagoDetailDTO(PagoEntity en){
        super(en);
    }
    
    @Override
    public PagoEntity toEntity(){
        PagoEntity enti = super.toEntity();
        return enti;
    }
}
