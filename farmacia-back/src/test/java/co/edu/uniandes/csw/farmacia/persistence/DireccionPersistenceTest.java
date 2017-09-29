/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.DireccionEntity;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author hs.hernandez
 */
@RunWith(Arquillian.class)
public class DireccionPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DireccionEntity.class.getPackage())
                .addPackage(DireccionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public DireccionPersistenceTest() {
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
     * Inyección de la dependencia a la clase DireccionPersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    private DireccionPersistence persistence;

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
    private List<DireccionEntity> data = new ArrayList<DireccionEntity>();

    private void clearData() {
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory;
        factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            DireccionEntity entity = factory.manufacturePojo(DireccionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            System.err.println("Hola mundo");
            clearData();
            insertData();
            System.err.println("Hola mundo");
            utx.commit();
            System.err.println("Hola mundo");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
                System.err.println("Hola mundo de error");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void createDireccionEntityTest() {
        PodamFactory factory = new PodamFactoryImpl();
        DireccionEntity newEntity = factory.manufacturePojo(DireccionEntity.class);
        DireccionEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        DireccionEntity entity = em.find(DireccionEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());

    }

    @Test
    public void getDireccionEntityTest() {
        List<DireccionEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (DireccionEntity ent : list) {
            boolean found = false;
            for (DireccionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getDireccionTest() {
        DireccionEntity entity = data.get(0);
        DireccionEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());

    }

    @Test
    public void updateDireccionTest() {
        DireccionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DireccionEntity newEntity = factory.manufacturePojo(DireccionEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        DireccionEntity resp = em.find(DireccionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());

    }

    @Test
    public void deleteDireccionTest() {
        DireccionEntity entity = data.get(0);
        persistence.delete(entity.getId());
        DireccionEntity deleted = em.find(DireccionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}

