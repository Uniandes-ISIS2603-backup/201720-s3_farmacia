package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.farmacia.entities.OrdenDeCompraEntity;
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
 * @author hs.hernandez
 */
@RunWith(Arquillian.class)
public class OrdenDeCompraPersistenceTest {
   
   
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrdenDeCompraEntity.class.getPackage())
                .addPackage(OrdenDeCompraPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public OrdenDeCompraPersistenceTest() {
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
     * Test of create method, of class OrdenDeCompraPersistence.
     */
    @Test
    public void testCreate() throws Exception {
    }

    /**
     * Test of update method, of class OrdenDeCompraPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
    }

    /**
     * Test of delete method, of class OrdenDeCompraPersistence.
     */
    @Test
    public void testDelete() throws Exception {
    }

    /**
     * Test of find method, of class OrdenDeCompraPersistence.
     */
    @Test
    public void testFind() throws Exception {
    }

    /**
     * Test of findAll method, of class OrdenDeCompraPersistence.
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
    private OrdenDeCompraPersistence persistence;

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
    private List<OrdenDeCompraEntity> data = new ArrayList<OrdenDeCompraEntity>();
    
    private void clearData() {
        em.createQuery("delete from orden de compra Entity").executeUpdate();
    }
    private void insertData() {
        PodamFactory factory;
        factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            OrdenDeCompraEntity entity = factory.manufacturePojo(OrdenDeCompraEntity.class);

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
    public void createOrdenDeCompraEntityTest() {
    PodamFactory factory = new PodamFactoryImpl();
    OrdenDeCompraEntity newEntity = factory.manufacturePojo(OrdenDeCompraEntity.class);
    OrdenDeCompraEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    OrdenDeCompraEntity entity = em.find(OrdenDeCompraEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
    
    }
    
    @Test
    public void getOrdenDeCompraEntityTest() {
    List<OrdenDeCompraEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (OrdenDeCompraEntity ent : list) {
        boolean found = false;
        for (OrdenDeCompraEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getordendecompraTest() {
    OrdenDeCompraEntity entity = data.get(0);
    OrdenDeCompraEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
    
    }
    
    @Test
    public void updateordendecompraTest() {
    OrdenDeCompraEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    OrdenDeCompraEntity newEntity = factory.manufacturePojo(OrdenDeCompraEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    OrdenDeCompraEntity resp = em.find(OrdenDeCompraEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getName(), resp.getName());
    
    }
    
    @Test
    public void deleteordendecompraTest() {
    OrdenDeCompraEntity entity = data.get(0);
    persistence.delete(entity.getId());
    OrdenDeCompraEntity deleted = em.find(OrdenDeCompraEntity.class, entity.getId());
    Assert.assertNull(deleted);
    }
    
    
}