package dao;

import model.Credit;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class CreditDaoImpl implements CreditDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    public CreditDaoImpl(){
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    @Override
    public void saveCredit(Credit credit) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(credit);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public Credit getCredit(int ID) {
        Credit credit = entityManager.find(Credit.class, ID);
        return credit;
    }

    @Override
    public void closeTransaction() {

    }
}
