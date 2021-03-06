/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.OrdenAprovEntity;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author a.bravo
 */
@RunWith(Arquillian.class)
public class OrdenAprovPersistenceTest {
   
   
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrdenAprovEntity.class.getPackage())
                .addPackage(OrdenAprovPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public OrdenAprovPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class OrdenAprovPersistence.
     */
    @Test
    public void testCreate() throws Exception {
    }

    /**
     * Test of update method, of class OrdenAprovPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
    }

    /**
     * Test of delete method, of class OrdenAprovPersistence.
     */
    @Test
    public void testDelete() throws Exception {
    }

    /**
     * Test of find method, of class OrdenAprovPersistence.
     */
    @Test
    public void testFind() throws Exception {
    }

    /**
     * Test of findAll method, of class OrdenAprovPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
    }
    
    ///////////////////////////////////
    
    /**
     * Inyección de la dependencia a la clase OrdenAprovPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private OrdenAprovPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

     /**
     *
     */
    private List<OrdenAprovEntity> data = new ArrayList<OrdenAprovEntity>();
    
    private void clearData() {
        em.createQuery("delete from OrdenAprovEntity").executeUpdate();
    }
    private void insertData() {
        PodamFactory factory;
        factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            OrdenAprovEntity entity = factory.manufacturePojo(OrdenAprovEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
 
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    @Test
    public void createOrdenAprovEntityTest() {
    PodamFactory factory = new PodamFactoryImpl();
    OrdenAprovEntity newEntity = factory.manufacturePojo(OrdenAprovEntity.class);
    OrdenAprovEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    OrdenAprovEntity entity = em.find(OrdenAprovEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
    
    }
    
    @Test
    public void getOrdenAprovEntityTest() {
    List<OrdenAprovEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (OrdenAprovEntity ent : list) {
        boolean found = false;
        for (OrdenAprovEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getOrdenAprovTest() {
    OrdenAprovEntity entity = data.get(0);
    OrdenAprovEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
    
    }
    
    @Test
    public void updateOrdenAprovTest() {
    OrdenAprovEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    OrdenAprovEntity newEntity = factory.manufacturePojo(OrdenAprovEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    OrdenAprovEntity resp = em.find(OrdenAprovEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getName(), resp.getName());
    
    }
    
    @Test
    public void deleteOrdenAprovTest() {
    OrdenAprovEntity entity = data.get(0);
    persistence.delete(entity.getId());
    OrdenAprovEntity deleted = em.find(OrdenAprovEntity.class, entity.getId());
    Assert.assertNull(deleted);
    }
    
    
}