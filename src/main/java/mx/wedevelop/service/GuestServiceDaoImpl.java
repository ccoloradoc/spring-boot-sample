package mx.wedevelop.service;

import mx.wedevelop.model.Guest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by colorado on 24/02/17.
 */
@Service
@Profile("dao")
public class GuestServiceDaoImpl implements GuestService {

    private EntityManagerFactory emf;


    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Guest> findAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Guest", Guest.class).getResultList();
    }

    @Override
    public Guest findById(int id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Guest.class, id);
    }

    @Override
    public Guest saveOrUpdate(Guest guest) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Guest savedGuest = em.merge(guest);
        em.getTransaction().commit();
        return savedGuest;
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Guest.class, id));
        em.getTransaction().commit();
    }
}
