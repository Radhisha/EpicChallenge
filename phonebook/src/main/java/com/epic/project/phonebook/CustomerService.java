package com.epic.project.phonebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRespository;

    @Autowired
    public CustomerService(CustomerRepository customerRespository) {
        this.customerRespository = customerRespository;
    }

    public List<Customer> findAll() {
        return (List<Customer>) customerRespository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRespository.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRespository.save(customer);
    }

    public void deleteById(Long id) {
    	customerRespository.deleteById(id);
    }
    
    public List<Customer> getCustomersByLastName(String lastName){
    	return customerRespository.findByLastName(lastName);
    }
    
    public Customer getCustomersByEmailId(String emailId){
    	return customerRespository.findByEmailId(emailId);
    }
}