package controller;

import facade.CarFacade;
import facade.DriverFacade;
import model.Car;
import model.Driver;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CarController implements Serializable {

    private String label, stateNumber;
    private int id;
    private double capacity;
    private List<Driver> drivers;

    private List<Car> filteredCars;


    @EJB
    CarFacade carFacade;

    /**
     * Посмотреть всех машины
     * @return
     */
    public List<Car> getAllCars() {
        List<Car> cars = carFacade.getAllCars();

        return cars;
    }

    /**
     * Добавить автомобиль
     */
    public void save() {
        Car car = new Car(label, stateNumber, capacity);
        carFacade.create(car);
    }

    /**
     * Удалить автомобиль
     */
    public String destroy(Car car) {
        carFacade.destroy(car);

        return "home?faces-redirect=true";
    }

    /**
     * Вернуть страницу с добавлением водителя к машине
     * @param carId
     * @return
     */
    public String addDriverPage(int carId, String stateNumber) {
        this.setId(carId);
        this.setStateNumber(stateNumber);
        return "add_driver";
    }

    /**
     * Добавить водителей к автомобилю
     * @param carId
     * @return
     */
    public String addDriver(int id, Driver driver) {
        carFacade.addDriverToCar(id, driver);

        return "home?faces-redirect=true";
    }

    /**
     * Посмотреть всех водителей закрепленных за автомобилем
     * @param id
     * @return
     */
    public String showAllDriversOfCar(int id) {
        this.setDrivers(carFacade.showAllDriversOfCar(id));

        return "/car/showallcar_drivers";
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public double getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Car> getFilteredCars() {
        return filteredCars;
    }

    public void setFilteredCars(List<Car> filteredCars) {
        this.filteredCars = filteredCars;
    }
}
