package app.repository;

import app.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * A class that handles database queries for the Credit.class object
 */

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    /**
     * Credit return method
     * @param creditName = creditName
     * @return Credit
     */
    Credit findByCreditName(String creditName);





}
