package app.dao;

import app.model.Credit;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Component
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

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Credit credit = entityManager.find(Credit.class, ID);
        transaction.commit();
//        entityManager.close();
        return credit;
    }

    @Override
    public List<Credit> getCredits() {
        EntityTransaction transaction = entityManager.getTransaction();


        String jpql = "Select c FROM Credit c";
        List<Credit> creditList = (List<Credit>) entityManager.createQuery(jpql  ).getResultList();

        return creditList;
    }

    @Override
    public void closeManager() {
        entityManager.close();
    }


    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
