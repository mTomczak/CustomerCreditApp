package dao;



import app.dao.CreditDaoImpl;
import app.model.Credit;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class that tests the connection with the base. Inserts and reads data about the loan.
 */
@SpringBootTest(classes = {app.SpringRestBootApplicationCredit.class})
class CreditDaoImplTest {

    private CreditDaoImpl creditDao = new CreditDaoImpl();

    @Test
    void createCreditAndGetCreditTest() {
        Credit credit = new Credit();
        credit.setCreditName("Test Credit");
        creditDao.saveCredit(credit);

        Credit testCredit = creditDao.getCredit(1);
        creditDao.closeManager();
        assertEquals(credit.getCreditName(), testCredit.getCreditName());

    }



}