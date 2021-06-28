package dao;


import model.Credit;

public interface CreditDao {

    public void saveCredit(Credit credit);
    public Credit getCredit(int ID);
    public void closeTransaction();

}
