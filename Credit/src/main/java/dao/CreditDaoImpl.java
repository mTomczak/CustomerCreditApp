package dao;

import model.Credit;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Component
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
        entityManager.merge(credit);
        transaction.commit();
//        entityManager.close();
    }

    @Override
    public Credit getCredit(int ID) {
//        Credit credit = entityManager.find(Credit.class, ID);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Credit credit = entityManager.find(Credit.class, ID);
        transaction.commit();
//        entityManager.close();
        return credit;
    }

    @Override
    public void closeEntityManager() {
        entityManager.close();
    }

}
