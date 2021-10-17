package com.epic.project.phonebook;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerTests {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  CustomerRepository repository;

  @Test
  public void shouldFindNoCustomerIfRepositoryIsEmpty() {
      Iterable<Customer> customers = repository.findAll();
      assertThat(customers).isEmpty();
  }

  @Test
  public void shouldStoreACustomer() {
	
      Customer customer = new Customer("Matt", "Hooper", "mhoope@gmail.com");
      repository.save(customer);
      assertThat(customer).hasFieldOrPropertyWithValue("lastName", "Hooper");
  }

  @Test
  public void shouldFindAllCustomers() {
	  Customer customer1 = new Customer("Matt", "Hooper", "mhoope@gmail.com");
      entityManager.persist(customer1);

	  Customer customer2 = new Customer("Grace", "Daniel", "gdaniel@gmail.com");
      entityManager.persist(customer2);

	  Customer customer3 = new Customer("Linda", "Jones", "ljones@gmail.com");
      entityManager.persist(customer3);

      Iterable<Customer> customers = repository.findAll();
      assertThat(customers).hasSize(3).contains(customer1, customer2, customer3);
  }

  @Test
  public void shouldFindCustomerById() {
	  Customer customer1 = new Customer("Matt", "Hooper", "mhoope@gmail.com");
	  entityManager.persist(customer1);

	  Customer customer2 = new Customer("Grace", "Daniel", "gdaniel@gmail.com");
	  entityManager.persist(customer2);

	  Customer foundCustomer =repository.findById(customer2.getId()).get();	   
      assertThat(foundCustomer).isEqualTo(customer2);
  }

  @Test
  public void shouldFindCustomerByEmailid() {
	  Customer customer1 = new Customer("Matt", "Hooper", "mhoope@gmail.com");
	  entityManager.persist(customer1);

	  Customer customer2 = new Customer("Grace", "Daniel", "gdaniel@gmail.com");
	  entityManager.persist(customer2);

	  Customer customer3 = new Customer("Linda", "Jones", "ljones@gmail.com");
	  entityManager.persist(customer3);

      Customer customer = repository.findByEmailId(customer2.getEmailId());
      assertThat(customer).isEqualTo(customer2);
  }

  @Test
  public void shouldFindCustomerByLastname() {
	  Customer customer1 = new Customer("Matt", "Jones", "mhoope@gmail.com");
	  entityManager.persist(customer1);

	  Customer customer2 = new Customer("Grace", "Daniel", "gdaniel@gmail.com");
	  entityManager.persist(customer2);

	  Customer customer3 = new Customer("Linda", "Jones", "ljones@gmail.com");
	  entityManager.persist(customer3);

	  Iterable<Customer> customers = repository.findByLastName("Jones");
	  assertThat(customers).hasSize(2).contains(customer1,customer3);
  }

  @Test
  public void shouldUpdateCustomerById() {
	  Customer customer1 = new Customer("Matt", "Hooper", "mhoope@gmail.com");
	  entityManager.persist(customer1);

	  Customer customer2 = new Customer("Grace", "Daniel", "gdaniel@gmail.com");
	  entityManager.persist(customer2);

	  Customer updatedCustomer = new Customer("Linda", "Jones", "ljones@gmail.com");
		  
	  Customer custmr = repository.findById(customer2.getId()).get();
	  custmr.setFirstName(updatedCustomer.getFirstName());
	  custmr.setLastName(updatedCustomer.getLastName());
	  repository.save(custmr);

	  Customer checkMobile = repository.findById(customer2.getId()).get();
    
	  assertThat(checkMobile.getId()).isEqualTo(customer2.getId());
	  assertThat(checkMobile.getFirstName()).isEqualTo(updatedCustomer.getFirstName());
	  assertThat(checkMobile.getLastName()).isEqualTo(updatedCustomer.getLastName());
  }

  @Test
  public void shouldDeleteCustomerById() {
	  Customer customer1 = new Customer("Matt", "Hooper", "mhoope@gmail.com");
	  entityManager.persist(customer1);

	  Customer customer2 = new Customer("Grace", "Daniel", "gdaniel@gmail.com");
	  entityManager.persist(customer2);

	  Customer customer3 = new Customer("Linda", "Jones", "ljones@gmail.com");
	  entityManager.persist(customer3);

	  repository.deleteById(customer2.getId());

	  Iterable<Customer> customers = repository.findAll();
	  assertThat(customers).hasSize(2).contains(customer1,customer3);
  }

  @Test
  public void shouldDeleteAllCustomers() {
	  Customer customer1 = new Customer("Matt", "Hooper", "mhoope@gmail.com");
	  entityManager.persist(customer1);

	  Customer customer2 = new Customer("Grace", "Daniel", "gdaniel@gmail.com");
	  entityManager.persist(customer2);

	  Customer customer3 = new Customer("Linda", "Jones", "ljones@gmail.com");
	  entityManager.persist(customer3);

      repository.deleteAll();
      assertThat(repository.findAll()).isEmpty();
  }
}