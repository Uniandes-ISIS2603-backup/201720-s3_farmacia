/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.DireccionEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.DireccionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lm.gonzalezf
 */
@Stateless
public class DireccionLogic {
    
    private static final Logger LOGGER = Logger.getLogger(DireccionLogic.class.getName());

    @Inject
    private DireccionPersistence persistence;
    
    /**
     * Lista de Direccions
     * @return lista de Direccions
     */
    public List<DireccionEntity> getDireccions(){
        LOGGER.info("Inicia proceso de consultar todos los Direccions");
        List<DireccionEntity> Direccions = persistence.findAll();
        return Direccions;
    }
    
    /**
     * Direccion con ID dado
     * @param id del Direccion
     * @return Direccion
     * @throws BusinessLogicException no existe el Direccion con el id dado
     */
    public DireccionEntity getDireccion (Long id) throws BusinessLogicException{
         LOGGER.log(Level.INFO, "Inicia proceso de consultar Direccion con id={0}", id);
        DireccionEntity Direccion = persistence.find(id);
        if (Direccion == null) {
            LOGGER.log(Level.SEVERE, "El Direccion con el id {0} no existe", id);
            throw new BusinessLogicException("El Direccion no existe cpn el id dado");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar Direccion con id={0}", id);
        return Direccion;
    }
    
    /**
     * Crea un Direccion nuevo
     * @param entity del Direccion
     * @return entidad cread
     * @throws BusinessLogicException ya existe un Direccion con el id dado
     */
    public DireccionEntity createDireccion(DireccionEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Direccion");
        if(getDireccion(entity.getId())!= null){
            throw new BusinessLogicException("Ya existe un Direccion con el id dado");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Direccion");
        return entity;
    }
    
    /**
     * Actualiza la informacion del Direccion con el ID dado
     * @param id del Direccion
     * @return datos actualizados del Direccion
     * @throws BusinessLogicException no existe el Direccion con el ID dado 
     */
    public DireccionEntity updateDireccion(Long id)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un Direccion con id={0}", id);
        DireccionEntity entity = getDireccion(id);
        if (entity !=null) {
            throw new BusinessLogicException("El ISBN es inválido");
        }
        DireccionEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el Direccion con id={0}", entity.getId());
        return newEntity;
    }
    /**
     * Elimina el Direccion con el id dado
     * @param id del Direccion que se desea eliminar
     */
    public void deleteBook(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un Direccion con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un Direccion con id={0}", id);
    }
    
}
