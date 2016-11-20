package facade;

import model.Contract;

import javax.ejb.Local;
import java.util.List;


@Local
public interface ContractFacade {

    /**
     * Показать все договора на страхование
     * @return
     */
    List<Contract> showAll();

    void save(Contract contract);

    Contract getById(int id);
}
