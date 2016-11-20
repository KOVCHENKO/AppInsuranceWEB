package model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Driver implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int driverLicense;

    @ManyToOne(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    public Driver() {
    }

    public Driver(String name, int driverLicense) {
        this.name = name;
        this.driverLicense = driverLicense;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(int driverLicense) {
        this.driverLicense = driverLicense;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", driverLicense=" + driverLicense +
                ", car=" + car +
                '}';
    }
}
