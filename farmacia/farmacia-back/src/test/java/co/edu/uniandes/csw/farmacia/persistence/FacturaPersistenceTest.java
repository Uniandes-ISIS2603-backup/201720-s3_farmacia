/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.farmacia.entities.FacturaEntity;
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
 * @author hs.hernandez
 */
@RunWith(Arquillian.class)
public class FacturaPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public FacturaPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class FacturaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
    }

    /**
     * Test of update method, of class FacturaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
    }

    /**
     * Test of delete method, of class FacturaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
    }

    /**
     * Test of find method, of class FacturaPersistence.
     */
    @Test
    public void testFind() throws Exception {
    }

    /**
     * Test of findAll method, of class FacturaPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
    }
    
    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private FacturaPersistence persistence;

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
    private List<FacturaEntity> data = new ArrayList<FacturaEntity>();
    
    private void clearData() {
        em.createQuery("delete from FacturaEntity").executeUpdate();
    }
    private void insertData() {
        PodamFactory factory;
        factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
 
    @Before
    public void setUp1() {
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
    public void createFacturaEntityTest() {
    PodamFactory factory = new PodamFactoryImpl();
    FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
    FacturaEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
    
    }
    
    @Test
    public void getFacturaEntityTest() {
    List<FacturaEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (FacturaEntity ent : list) {
        boolean found = false;
        for (FacturaEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getFacturaTest() {
    FacturaEntity entity = data.get(0);
    FacturaEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
    
    }
    
    @Test
    public void updateFacturaTest() {
    FacturaEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getName(), resp.getName());
    
    }
    
    @Test
    public void deleteXYZTest() {
    FacturaEntity entity = data.get(0);
    persistence.delete(entity.getId());
    FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
    Assert.assertNull(deleted);
    }
    
    
    
}
