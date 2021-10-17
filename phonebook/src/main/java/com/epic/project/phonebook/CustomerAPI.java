package com.epic.project.phonebook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/customers")

public class CustomerAPI {
	private final CustomerService customerService;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CustomerAPI(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
    	LOGGER.info("About to display customer collection, if any..");
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        LOGGER.info("Details for Customer :");
        return ResponseEntity.ok(customerService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Customer> create(@Validated @RequestBody Customer customer) {
    	LOGGER.info("Creating Customer record :");
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update (@PathVariable Long id, @RequestBody Customer customerData) throws ResourceNotFoundException {
    	LOGGER.info("About to update customer record :");
    	Customer customer = customerService.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
    	customer.setFirstName(customerData.getFirstName());
    	customer.setLastName(customerData.getLastName());
    	customer.setEmailId(customerData.getEmailId());
        return ResponseEntity.accepted().body(customerService.save(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable Long id) {
    	LOGGER.info("Deleting Customer record :");
    	customerService.deleteById(id);

        return ResponseEntity.accepted().build();
    }
    
    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<Customer>> getCustomersByLastName(@PathVariable String lastName ) {
    	LOGGER.info("Searching Customer record using lastname :");
        return ResponseEntity.ok(customerService.getCustomersByLastName(lastName));
    }
    
    @GetMapping("/email/{emailId}")
    public ResponseEntity<Customer> getCustomersByEmailId(@PathVariable String emailId ) {
    	LOGGER.info("Searching Customer record using emailid :");
        return ResponseEntity.ok(customerService.getCustomersByEmailId(emailId));
    }
}
