package app.dao;


import app.model.Credit;

public interface CreditDao {

     void saveCredit(Credit credit);
     Credit getCredit(int ID);
     void closeEntityManager();


}
