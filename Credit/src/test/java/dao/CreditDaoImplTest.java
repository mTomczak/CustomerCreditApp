package dao;

import model.Credit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class that tests the connection with the base. Inserts and reads data about the loan.
 */
class CreditDaoImplTest {

//    @Autowired
//    CreditDaoImpl creditDao;

    CreditDao creditDao = new CreditDaoImpl();

    @Test
    void saveCredit() {
        Credit credit = new Credit();
        credit.setCreditName("Test Credit");
        creditDao.saveCredit(credit);

        Credit testCredit = creditDao.getCredit(1);

        creditDao.closeEntityManager();

        assertEquals(credit.getCreditName(), testCredit.getCreditName());



    }

}