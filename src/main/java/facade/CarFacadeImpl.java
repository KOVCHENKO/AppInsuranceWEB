package facade;

import model.Car;
import model.Driver;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CarFacadeImpl implements CarFacade {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Car> getAllCars() {
            Query q = em.createQuery("select c from Car c");
            List<Car> cars = q.getResultList();
        return cars;
    }

    @Override
    public void create(Car car) {
        em.persist(car);
    }

    @Override
    public void destroy(Car car) {
        em.remove(em.contains(car) ? car : em.merge(car));
    }

    /**
     * Добавить водителя к машине
     * @param id
     * @param driver
     */
    @Override
    public void addDriverToCar(int id, Driver driver) {
        Car car = this.findCarById(id);
        driver.setCar(car);

        em.merge(driver);
        em.flush();
    }

    /**
     * Найти машину по id
     * @param id
     * @return
     */
    @Override
    public Car findCarById(int id) {
        Car car = em.find(Car.class, id);
        return car;
    }

    /**
     * Посмотреть всех водителей закрепленных за авто
     * @param id
     */
    @Override
    public List<Driver> showAllDriversOfCar(int id) {
        Query q = em.createQuery("select d from Driver d where car_id = :id");
        q.setParameter("id", id);
        List<Driver> drivers = q.getResultList();

        return drivers;
    }


}
