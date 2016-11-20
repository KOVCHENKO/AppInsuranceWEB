package facade;


import model.Contract;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ContractFacadeImpl implements ContractFacade {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Contract> showAll() {
        Query q = em.createQuery("select c from Contract c");
        List<Contract> contracts = q.getResultList();
        return contracts;
    }

    @Override
    public void save(Contract contract) {
        em.persist(contract);
    }

    @Override
    public Contract getById(int id) {
        Contract contract = em.find(Contract.class, id);
        return contract;
    }


}
