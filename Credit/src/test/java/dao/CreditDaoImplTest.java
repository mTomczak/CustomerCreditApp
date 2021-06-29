package dao;

import app.dao.CreditDao;
import app.dao.CreditDaoImpl;
import app.model.Credit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class that tests the connection with the base. Inserts and reads data about the loan.
 */
class CreditDaoImplTest {

//    @Autowired
//    CreditDaoImpl creditDao;

    private CreditDao creditDao = new CreditDaoImpl();

    @Test
    void saveCredit() {
        Credit credit = new Credit();
        credit.setCreditName("Test Credit");
        creditDao.saveCredit(credit);

        Credit testCredit = creditDao.getCredit(1);
        creditDao.closeManager();
        assertEquals(credit.getCreditName(), testCredit.getCreditName());



    }

}