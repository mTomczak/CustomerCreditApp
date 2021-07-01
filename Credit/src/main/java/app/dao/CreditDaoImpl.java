package app.dao;

import app.model.Credit;
import app.model.Customer;
import app.model.Product;
import app.model.RestCreditModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that implements the Credityszne interface. It has methods for inserting and retrieving data from the database.
 */

@Component
public class CreditDaoImpl implements CreditDao {

    @Autowired
    RestCreditModel restCreditModel;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public CreditDaoImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    /**
     * The method of saving the credit to the base
     * @param credit Credit
     */
    @Override
    public void saveCredit(Credit credit) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(credit);
        transaction.commit();

    }

    /**
     * A method that takes a loan from the database based on the ID number
     * @param ID ID
     * @return credit
     */
    @Override
    public Credit getCredit(int ID) {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Credit credit = entityManager.find(Credit.class, ID);
        transaction.commit();
        return credit;
    }

    /**
     * The method of collecting all credits from the base
     * @return Credit list
     */
    @Override
    public List<Credit> getCredits() {

        String jpql = "Select c FROM Credit c";
        List<Credit> creditList = (List<Credit>) entityManager.createQuery(jpql).getResultList();

        return creditList;
    }


    @Override
    public void closeManager() {
        entityManager.close();
    }

    /**
     * The method of processing the lists given as parameters in the credit list. Products, customers and credits are combined based on the primary keys
     * @param creditList creditList
     * @param productlist productlist
     * @param customerList customerList
     * @return Loan list
     */

    @Override
    public List<RestCreditModel> getAllCredits(List<Credit> creditList, List<Product> productlist, List<Customer> customerList) {

        Map<Integer, Product> productMap = new HashMap<>();
        for (Product product : productlist
        ) {
            productMap.put(product.getID(), product);
        }

        Map<Integer, Customer> customerMap = new HashMap<>();
        for (Customer customer : customerList
        ) {
            customerMap.put(customer.getID(), customer);
        }

        List<RestCreditModel> finalCreditList = new ArrayList<>();

        for (Credit credit : creditList
        ) {
            RestCreditModel model = new RestCreditModel();

            for (Customer customer : customerList
            ) {
                if (credit.getCustomerID() == customer.getID()) {
                    model.setUserFirstName(customer.getFirstName());
                    model.setPersonalNumber(customer.getPesel());
                    model.setUserSurname(customer.getSurname());
                }
                for (Product product : productlist
                ) {
                    model.setProductValue((long) product.getValue());
                    model.setProductName(product.getProductName());

                }
                model.setCreditName(credit.getCreditName());

                finalCreditList.add(model);


            }


        }

        return finalCreditList;
    }

}




