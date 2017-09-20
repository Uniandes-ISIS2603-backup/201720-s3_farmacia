/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.OrdenDeRotacionDeInventarioEntity;
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
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author a.gracia10
 */
@RunWith(Arquillian.class)
public class OrdenDeRotacionDeInventarioPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrdenDeRotacionDeInventarioEntity.class.getPackage())
                .addPackage(OrdenDeRotacionDeInventarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public OrdenDeRotacionDeInventarioPersistenceTest() {
    }
    
    @Inject
    OrdenDeRotacionDeInventarioPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<OrdenDeRotacionDeInventarioEntity> data = new ArrayList<OrdenDeRotacionDeInventarioEntity>();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
    
    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class OrdenDeRotacionDeInventarioPersistence.
     */
    @Test
    public void testFind() throws Exception {
        OrdenDeRotacionDeInventarioEntity entity = data.get(0);
    
        OrdenDeRotacionDeInventarioEntity newEntity = persistence.find(entity.getId());
    
        Assert.assertNotNull(newEntity);
    
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of findByName method, of class OrdenDeRotacionDeInventarioPersistence.
     */
    @Test
    public void testFindByName() throws Exception {
        OrdenDeRotacionDeInventarioEntity entity = data.get(0);
    
        OrdenDeRotacionDeInventarioEntity newEntity = persistence.findByName(entity.getName());
    
        Assert.assertNotNull(newEntity);
    
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of findAll method, of class OrdenDeRotacionDeInventarioPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
         List<OrdenDeRotacionDeInventarioEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (OrdenDeRotacionDeInventarioEntity ent : list) {
        boolean found = false;
        for (OrdenDeRotacionDeInventarioEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }
    }

    /**
     * Test of create method, of class OrdenDeRotacionDeInventarioPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
    OrdenDeRotacionDeInventarioEntity newEntity = factory.manufacturePojo(OrdenDeRotacionDeInventarioEntity.class);
    OrdenDeRotacionDeInventarioEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    OrdenDeRotacionDeInventarioEntity entity = em.find(OrdenDeRotacionDeInventarioEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class OrdenDeRotacionDeInventarioPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
       OrdenDeRotacionDeInventarioEntity entity = data.get(0);
    
       PodamFactory factory = new PodamFactoryImpl();
    
       OrdenDeRotacionDeInventarioEntity newEntity = factory.manufacturePojo(OrdenDeRotacionDeInventarioEntity.class);

    
       newEntity.setId(entity.getId());

    
       persistence.update(newEntity);

    
       OrdenDeRotacionDeInventarioEntity resp = em.find(OrdenDeRotacionDeInventarioEntity.class, entity.getId());

    
       Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class OrdenDeRotacionDeInventarioPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        OrdenDeRotacionDeInventarioEntity entity = data.get(0);
    
        persistence.delete(entity.getId());
    
        OrdenDeRotacionDeInventarioEntity deleted = em.find(OrdenDeRotacionDeInventarioEntity.class, entity.getId());
    
        Assert.assertNull(deleted);
    }
    
    private void clearData() {
        em.createQuery("delete from OrdenDeRotacionDeInventarioEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            OrdenDeRotacionDeInventarioEntity entity = factory.manufacturePojo(OrdenDeRotacionDeInventarioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    
}
