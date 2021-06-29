package app.repository;

import app.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    /**
     * Credit return method
     * @param creditName = creditName
     * @return Credit
     */
    Credit findByCreditName(String creditName);



}
