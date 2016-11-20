package facade;


import model.Driver;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DriverFacade {


    void create(Driver driver);

    List<Driver> getAllDrivers();

    void destroy(Driver driver);
}
