package carmelo.spring.service;

import java.util.List;

import carmelo.spring.model.Customer;

public interface CustomerService {

    List<Customer> findAll();
}
