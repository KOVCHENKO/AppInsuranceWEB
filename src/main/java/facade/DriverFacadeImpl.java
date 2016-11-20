package facade;

import model.Driver;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DriverFacadeImpl implements DriverFacade {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Driver driver) {

    }

    /**
     * Посмтреть всех водителей
     * @return
     */
    @Override
    public List<Driver> getAllDrivers() {
        Query q = em.createQuery("select d from Driver d");
        List<Driver> drivers = q.getResultList();
        return drivers;
    }

    @Override
    public void destroy(Driver driver) {
        em.remove(em.contains(driver) ? driver : em.merge(driver));
    }
}
