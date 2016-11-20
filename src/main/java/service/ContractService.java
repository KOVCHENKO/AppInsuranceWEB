package service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facade.ContractFacade;
import model.Car;
import model.Contract;
import model.Driver;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class ContractService implements Serializable {

    @EJB
    ContractFacade contractFacade;


    /**
     * Извлечь все контракты
     * @return
     */
    public List<Contract> showAll() {
        return contractFacade.showAll();
    }


    /**
     * Сохранить договор в БД
     * @param contract
     */
    public void save(Contract contract) {

        Car carToSearch = contract.getCar();
        List<Driver> driversToContract = carToSearch.getDrivers();

        List<Integer> coefficients = new ArrayList<>();

        for(Driver singleDriver : driversToContract) {
            int driverLisenseNumber = singleDriver.getDriverLicense();
            String sURL = "http://localhost:8080/RSSApp_war/ws/discounts/dln/" + driverLisenseNumber; //just a string
            URL url = null;
            try {
                url = new URL(sURL);
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();
                JsonParser jp = new JsonParser(); //from gson
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
                JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
                String coefficient = rootobj.get("coefficient").getAsString(); //just grab the zipcode
                int coefficientNumber = Integer.parseInt(coefficient);
                coefficients.add(coefficientNumber);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int coefficientToCalculate = Collections.min(coefficients);
        double engineCapacity = carToSearch.getCapacity();
        int baseCost = 500;

        double contractCost = (baseCost * engineCapacity) - (coefficientToCalculate * baseCost / 100);

        // Здесь пойдет рассчет стоимости контракта
        contract.setCost(contractCost);
        // Если срок действия больше чем текущий, значит договор действительный, если нет, то нет
        contract.setValid(true);

        contractFacade.save(contract);
    }

    /**
     * Получить одиночный договор
     * @param id
     * @return
     */
    public Contract getById(int id) {
        return contractFacade.getById(id);
    }

}
