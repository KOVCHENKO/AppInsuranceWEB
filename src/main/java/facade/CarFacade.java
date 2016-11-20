package facade;

import model.Car;
import model.Driver;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CarFacade {
    List<Car> getAllCars();

    void create(Car car);

    void destroy(Car car);

    void addDriverToCar(int id, Driver driver);

    Car findCarById(int id);

    List<Driver> showAllDriversOfCar(int id);
}
