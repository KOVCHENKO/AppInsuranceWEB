package controller;

import facade.DriverFacade;
import model.Driver;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class DriverController implements Serializable {

    private int driverLicense;
    private String name;
    private List<Driver> filteredDrivers;

    @EJB
    DriverFacade driverFacade;

    /**
     * Добавить водителя
     */
    public void save() {
        Driver driver = new Driver(name, driverLicense);
        driverFacade.create(driver);
    }


    /**
     * Получить всех водителей
     * @return
     */
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = driverFacade.getAllDrivers();

        return drivers;
    }

    /**
     * Удалить автомобиль
     * @param driver
     * @return
     */
    public String destroy(Driver driver) {
        driverFacade.destroy(driver);

        return "home?faces-redirect=true";
    }


    // getter/setters
    public int getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(int driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Driver> getFilteredDrivers() {
        return filteredDrivers;
    }

    public void setFilteredDrivers(List<Driver> filteredDrivers) {
        this.filteredDrivers = filteredDrivers;
    }
}
