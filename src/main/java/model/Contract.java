package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Contract implements Serializable {

    @Id
    @GeneratedValue
    private int id;


    private int startDate;
    private int finishDate;
    private boolean valid;
    private double cost;

    public Contract() {
    }

    public Contract(int startDate, int finishDate, Car car) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.car = car;
    }


    @OneToOne(fetch = FetchType.EAGER)
    private Car car;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(int finishDate) {
        this.finishDate = finishDate;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", valid=" + valid +
                ", cost=" + cost +
                ", car=" + car +
                '}';
    }
}
