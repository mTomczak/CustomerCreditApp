package app.dao;


import app.model.Credit;

import java.util.List;

public interface CreditDao {

     void saveCredit(Credit credit);
     Credit getCredit(int ID);
     List<Credit> getCredits();
     void closeManager();


}
