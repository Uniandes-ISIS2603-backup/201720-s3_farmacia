/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.farmacia.entities.SuministroEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author lm.gonzalezf
 */
@RunWith(Arquillian.class)
public class SuministroPersistenceTest {
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SuministroEntity.class.getPackage())
                .addPackage(SuministroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    public SuministroPersistenceTest() {
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
     * Test of create method, of class SuministroPersistence.
     */
    @Test
    public void testCreate() throws Exception {
    }

    /**
     * Test of update method, of class SuministroPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
    }

    /**
     * Test of delete method, of class SuministroPersistence.
     */
    @Test
    public void testDelete() throws Exception {
    }

    /**
     * Test of find method, of class SuministroPersistence.
     */
    @Test
    public void testFind() throws Exception {
    }

    /**
     * Test of findAll method, of class SuministroPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
    }
    
    /**
     * Inyección de la dependencia a la clase SuministroPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private SuministroPersistence persistence;

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
    private List<SuministroEntity> data = new ArrayList<SuministroEntity>();
    
    private void clearData() {
        em.createQuery("delete from SuministroEntity").executeUpdate();
    }
    private void insertData() {
        PodamFactory factory;
        factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            SuministroEntity entity = factory.manufacturePojo(SuministroEntity.class);

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
    public void createSuministroEntityTest() {
    PodamFactory factory = new PodamFactoryImpl();
    SuministroEntity newEntity = factory.manufacturePojo(SuministroEntity.class);
    SuministroEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    SuministroEntity entity = em.find(SuministroEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
    
    }
    
    @Test
    public void getSuministroEntityTest() {
    List<SuministroEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (SuministroEntity ent : list) {
        boolean found = false;
        for (SuministroEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getsuministroTest() {
    SuministroEntity entity = data.get(0);
    SuministroEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
    
    }
    
    @Test
    public void updatesuministroTest() {
    SuministroEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    SuministroEntity newEntity = factory.manufacturePojo(SuministroEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    SuministroEntity resp = em.find(SuministroEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getName(), resp.getName());
    
    }
    
    @Test
    public void deletesuministroTest() {
    SuministroEntity entity = data.get(0);
    persistence.delete(entity.getId());
    SuministroEntity deleted = em.find(SuministroEntity.class, entity.getId());
    Assert.assertNull(deleted);
    }
    
    


}