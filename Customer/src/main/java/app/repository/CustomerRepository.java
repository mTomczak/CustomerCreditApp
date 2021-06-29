package app.repository;


import app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Customer return method
     * @param pesel = pesel
     * @return Credit
     */


    Customer findByPesel(String pesel);



}
