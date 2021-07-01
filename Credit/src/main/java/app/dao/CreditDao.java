package app.dao;


import app.model.Credit;
import app.model.Customer;
import app.model.Product;
import app.model.RestCreditModel;

import java.util.List;

/**
 * A interface that supports the Credit model
 */
public interface CreditDao {

     void saveCredit(Credit credit);
     Credit getCredit(int ID);
     List<Credit> getCredits();
     void closeManager();

     List<RestCreditModel> getAllCredits(List<Credit> creditList, List<Product> productlist, List<Customer> customerList);


}
