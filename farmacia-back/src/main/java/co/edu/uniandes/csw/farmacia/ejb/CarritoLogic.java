/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.CarritoEntity;
import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.OrdenDeCompraEntity;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.CarritoPersistence;
import com.sun.tools.extcheck.Main;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author hs.hernandez
 */
@Stateless
public class CarritoLogic {
 
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());

    @Inject
    private CarritoPersistence persistence;
    
    @Inject 
    private ProductoLogic productologic;
    
    @Inject
    private OrdenDeCompraLogic ordenlogic;
    
    @Inject
    private ClienteLogic clientelogic;
    
    
    
    public CarritoEntity añadirProducto(Long idProducto, CarritoEntity ent){
        List<ProductoEntity> prods = ent.getProductos();
        ProductoEntity prod = productologic.getProducto(idProducto);
        prods.add(prod);
        prod.setCarrito(ent);
        ent.setProductos(prods);
        return ent;
    }
    /**
     * Lista de clientes
     * @return lista de clientes
     */
    public List<CarritoEntity> getCarrito(){
        LOGGER.info("Inicia proceso de consultar todos los CarritoEntity");
        
        List<CarritoEntity> Carrito = persistence.findAll();
        return Carrito;
    }
    
    
    /**
     * Cliente con ID dado
     * @param id del cliente
     * @return Cliente
     * @throws BusinessLogicException no existe el cliente con el id dado
     */
    public CarritoEntity getCarrito (Long id){
         LOGGER.log(Level.INFO, "Inicia proceso de consultar CarritoEntity con id={0}", id);
        CarritoEntity Carrito = persistence.find(id);
        if (Carrito == null) {
            LOGGER.log(Level.SEVERE, "El CarritoEntity con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar CarritoEntity con id={0}", id);
        return Carrito;
    }
    
    /**
     * Crea un cliente nuevo
     * @param entity del cliente
     * @return entidad cread
     * @throws BusinessLogicException ya existe un cliente con el id dado
     */
    public CarritoEntity createCarrito(CarritoEntity entity)throws BusinessLogicException{
        
        LOGGER.info("Inicia proceso de creación de Carrito en la clase CarritoEntity");
       
       
        List<ProductoEntity> lista = new ArrayList<>();
        for(ProductoEntity p : entity.getProductos()) {
            ProductoEntity a = productologic.getProducto(p.getId());
            a.setCarrito(entity);
            lista.add(a);
        }
        entity.setProductos(lista);
        
        
        LOGGER.info("Termina proceso de creación de CarritoEntity");
        return persistence.create(entity);
    }
    /**
     * Elimina el cliente con el id dado
     * @param id del cliente que se desea eliminar
     */
    public void deleteCarrito(Long id) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un Carrito con id={0}", id);
        
        //Crear la orden de compra asociada al carrito
        CarritoEntity entity = persistence.find(id);
        
        OrdenDeCompraEntity orden = new OrdenDeCompraEntity();
        orden.setCostoTotal(entity.getTotalCarrito());
        ClienteEntity cliente = clientelogic.getCliente(entity.getIdCliente());
        cliente.setId(entity.getIdCliente());
        orden.setCliente(cliente);
        cliente.getOrdenes().add(orden);
        //Fecha actual
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        //Fecha actual
        orden.setFecha(date.format(now));
        orden.setCliente(cliente);
        orden.setIdCliente(cliente.getId());
        ordenlogic.createOrdenDeCompra(orden);
        desvincularProductos(entity);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un Carrito con id={0}", id);
    }
    
    private void desvincularProductos(CarritoEntity en) {
        for(int i = en.getProductos().size()-1; i >= 0; i--) {
            en.getProductos().remove(i).setCarrito(null);
        }
    }
}
