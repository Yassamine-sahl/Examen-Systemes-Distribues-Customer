package ma.enset.customerservice.customerQuery.repositories;

import ma.enset.customerservice.customerQuery.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {

}