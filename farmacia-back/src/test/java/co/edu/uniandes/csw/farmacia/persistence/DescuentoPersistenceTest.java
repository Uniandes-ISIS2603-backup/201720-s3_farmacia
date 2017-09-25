/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.DescuentoEntity;
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
 * @author jp.carreno
 */
@RunWith(Arquillian.class)
public class DescuentoPersistenceTest {
   
   
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DescuentoEntity.class.getPackage())
                .addPackage(DescuentoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public DescuentoPersistenceTest() {
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

    
    
    ///////////////////////////////////
    
    /**
     * InyecciÃ³n de la dependencia a la clase XYZPersistence cuyos mÃ©todos
     * se van a probar.
     */
    @Inject
    private DescuentoPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los mÃ©todos que se estÃ¡n probando.
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
    private List<DescuentoEntity> data = new ArrayList<DescuentoEntity>();
    
    private void clearData() {
        em.createQuery("delete from DescuentoEntity").executeUpdate();
    }
    private void insertData() {
        PodamFactory factory;
        factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            DescuentoEntity entity = factory.manufacturePojo(DescuentoEntity.class);

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
    public void createDescuentoEntityTest() {
    PodamFactory factory = new PodamFactoryImpl();
    DescuentoEntity newEntity = factory.manufacturePojo(DescuentoEntity.class);
    DescuentoEntity result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    DescuentoEntity entity = em.find(DescuentoEntity.class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
    
    }
    
    @Test
    public void getClienteEntityTest() {
    List<DescuentoEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for (DescuentoEntity ent : list) {
        boolean found = false;
        for (DescuentoEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getDescuentoTest() {
    DescuentoEntity entity = data.get(0);
    DescuentoEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getName(), newEntity.getName());
    
    }
    
    @Test
    public void updateDescuentoTest() {
    DescuentoEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    DescuentoEntity newEntity = factory.manufacturePojo(DescuentoEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    DescuentoEntity resp = em.find(DescuentoEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getName(), resp.getName());
    
    }
    
    @Test
    public void deleteDescuentoTest() {
    DescuentoEntity entity = data.get(0);
    persistence.delete(entity.getId());
    DescuentoEntity deleted = em.find(DescuentoEntity.class, entity.getId());
    Assert.assertNull(deleted);
    }
    
    
}
