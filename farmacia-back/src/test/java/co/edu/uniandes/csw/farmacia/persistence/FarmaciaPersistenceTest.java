/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.FarmaciaEntity;
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
 * @author jp.carreno
 */
@RunWith(Arquillian.class)
public class FarmaciaPersistenceTest {
   
   
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FarmaciaEntity.class.getPackage())
                .addPackage(FarmaciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public FarmaciaPersistenceTest() {
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
     * Test of create method, of class FarmaciaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
    }

    /**
     * Test of update method, of class FarmaciaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
    }

    /**
     * Test of delete method, of class FarmaciaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
    }

    /**
     * Test of find method, of class FarmaciaPersistence.
     */
    @Test
    public void testFind() throws Exception {
    }

    /**
     * Test of findAll method, of class ClientePersistence.
     */
    @Test
    public void testFindAll() throws Exception {
    }
    
    ///////////////////////////////////
    
    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private FarmaciaPersistence persistence;

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
    private List<FarmaciaEntity> data = new ArrayList<FarmaciaEntity>();
    
    private void clearData() {
        em.createQuery("delete from FarmaciaEntity").executeUpdate();
    }
    private void insertData() {
        PodamFactory factory;
        factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FarmaciaEntity entity = factory.manufacturePojo(FarmaciaEntity.class);

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
    public void createFarmaciaEntityTest() {
    PodamFactory factory = new PodamFactoryImpl();
    FarmaciaEntity newEntity = factory.manufacturePojo(FarmaciaEntity.class);
    FarmaciaEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    FarmaciaEntity entity = em.find(FarmaciaEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
    
    }
    
    @Test
    public void getFarmaciaEntityTest() {
    List<FarmaciaEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (FarmaciaEntity ent : list) {
        boolean found = false;
        for (FarmaciaEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getFarmaciaTest() {
    FarmaciaEntity entity = data.get(0);
    FarmaciaEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
    
    }
    
    @Test
    public void updateFarmaciaTest() {
    FarmaciaEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    FarmaciaEntity newEntity = factory.manufacturePojo(FarmaciaEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    FarmaciaEntity resp = em.find(FarmaciaEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getName(), resp.getName());
    
    }
    
    @Test
    public void deleteFarmaciaTest() {
    FarmaciaEntity entity = data.get(0);
    persistence.delete(entity.getId());
    FarmaciaEntity deleted = em.find(FarmaciaEntity.class, entity.getId());
    Assert.assertNull(deleted);
    }
    
    
}
