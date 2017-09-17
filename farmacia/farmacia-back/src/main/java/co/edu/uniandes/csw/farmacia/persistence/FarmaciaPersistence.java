        package co.edu.uniandes.csw.farmacia.persistence;

/*
MIT License
Copyright (c) 2017 Universidad de los Andes - ISIS2603
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

import co.edu.uniandes.csw.farmacia.entities.FarmaciaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class FarmaciaPersistence {

    private static final Logger LOGGER = Logger.getLogger(FarmaciaPersistence.class.getName());

    @PersistenceContext(unitName = "farmaciaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Farmacia que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public FarmaciaEntity create(FarmaciaEntity entity) {
        LOGGER.info("Creando un Farmacias nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la Farmacia en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un Farmacias nueva");
        return entity;
    }

    /**
     * Actualiza un Farmacias.
     *
     * @param entity: la Farmacia que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un Farmacias con los cambios aplicados.
     */
    public FarmaciaEntity update(FarmaciaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Farmacia con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Farmacia con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un Farmacias de la base de datos recibiendo como argumento el id
     * de la Farmacia
     *
     * @param id: id correspondiente a la Farmacia a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Farmacia con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public FarmaciaEntity find(Long id) para obtener la Farmacia a borrar.
        FarmaciaEntity entity = em.find(FarmaciaEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from FarmaciaEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun Farmacias con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Farmacia buscada.
     * @return un Farmacias.
     */
    public FarmaciaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Farmacia con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from FarmaciaEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(FarmaciaEntity.class, id);
    }

    /**
     * Devuelve todas las Farmaciaes de la base de datos.
     *
     * @return una lista con todas las Farmaciaes que encuentre en la base de
 datos, "select u from FarmaciaEntity u" es como un "select * from
 FarmaciaEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<FarmaciaEntity> findAll() {
        LOGGER.info("Consultando todas las Farmacias");
        // Se crea un query para buscar todas las Farmaciaes en la base de datos.
        TypedQuery query = em.createQuery("select u from FarmaciaEntity u", FarmaciaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Farmaciaes.
        return query.getResultList();
    }
}