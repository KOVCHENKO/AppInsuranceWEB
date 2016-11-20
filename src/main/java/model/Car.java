package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Car implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String label;
    private String stateNumber;
    private double capacity;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "car", targetEntity = Driver.class)
    private List<Driver> drivers = new ArrayList<Driver>();

    public Car() {
    }

    public Car(String label, String stateNumber, double capacity) {
        this.label = label;
        this.stateNumber = stateNumber;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", stateNumber='" + stateNumber + '\'' +
                ", capacity=" + capacity +
                ", drivers=" + drivers +
                '}';
    }
}
