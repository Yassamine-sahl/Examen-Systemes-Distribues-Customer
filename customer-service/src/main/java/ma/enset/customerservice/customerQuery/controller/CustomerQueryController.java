package ma.enset.customerservice.customerQuery.controller;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.query.GetAllCustomersQuery;
import ma.enset.commonapi.query.GetCustomerById;
import ma.enset.customerservice.customerQuery.entities.Customer;
import ma.enset.customerservice.customerQuery.repositories.CustomerRepository;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/queries")
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerQueryController {
    private QueryGateway queryGateway;
    private CustomerRepository customerRepository;

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllRadars(){
        return queryGateway.query(new GetAllCustomersQuery(),
                ResponseTypes.multipleInstancesOf(Customer.class)).join();
    }

    @QueryHandler
    public List<Customer> on(GetAllCustomersQuery query){
        return customerRepository.findAll();
    }

    @GetMapping("/getCustomer/{id}")
    public Customer getRadar(@PathVariable String id){
        return queryGateway.query(new GetCustomerById(id),
                ResponseTypes.instanceOf(Customer.class)).join();
    }

    @QueryHandler
    public Customer on(GetCustomerById query){
        return customerRepository.findById(query
                .getId()).get();
    }
}
