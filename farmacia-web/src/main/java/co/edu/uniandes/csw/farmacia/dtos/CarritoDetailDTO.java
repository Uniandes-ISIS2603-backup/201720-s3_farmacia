/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.dtos;

import co.edu.uniandes.csw.farmacia.entities.CarritoEntity;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hs.hernandez
 */
public class CarritoDetailDTO extends CarritoDTO {
    
    /**
     * Relacion con producto
     */
    
    private List<ProductoDTO> productos;
    
    public CarritoDetailDTO (){
        super();
    }
    
    public CarritoDetailDTO(CarritoEntity en ){
        super(en);
        if(en.getProductos() != null){
            productos = new ArrayList<>();
            for(ProductoEntity e : en.getProductos())
                productos.add(new ProductoDTO(e));
        }
    }
    
    @Override
    public CarritoEntity toEntity(){
        CarritoEntity enti = super.toEntity();
       if(getProductos()!= null){
            ArrayList<ProductoEntity> facEnti = new ArrayList<>();
            for (ProductoDTO dtofac : getProductos()) {
                facEnti.add(dtofac.toEntity());
            }
            enti.setProductos(facEnti);
        }
        return enti;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }
    
}
