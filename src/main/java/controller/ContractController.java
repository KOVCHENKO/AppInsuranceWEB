package controller;

import facade.ContractFacade;
import model.Car;
import model.Contract;
import service.ContractService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ContractController implements Serializable {

    private Car contractCar;
    private int startDate, finishDate;
    private Contract concludedContract;

    @Inject
    ContractService contractService;

    /**
     * Выбрать машину для заключения договора
     * @param car
     * @return
     */
    public String chooseCar(Car car) {
        contractCar = car;

        return "create_contract_step2";
    }

    /**
     * Создать договор
     * @return
     */
    public void save(Car contractedCar) {
        Contract contract = new Contract(startDate, finishDate, contractedCar);
        contractService.save(contract);
    }

    /**
     * Показать все договора в наличии у пользователя
     * @return
     */
    public List<Contract> getAllContracts() {
        List<Contract> contracts = contractService.showAll();

        return contracts;
    }

    /**
     * Показать одиночный договор
     * @param id
     * @return
     */
    public String getContractById(int id) {
         this.setConcludedContract(contractService.getById(id));

        return "show_single_contract";
    }


    // getters/setters
    public Car getContractCar() {
        return contractCar;
    }

    public void setContractCar(Car contractCar) {
        this.contractCar = contractCar;
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

    public Contract getConcludedContract() {
        return concludedContract;
    }

    public void setConcludedContract(Contract concludedContract) {
        this.concludedContract = concludedContract;
    }
}
