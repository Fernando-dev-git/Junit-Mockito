package com.sergiotrapiello.cursotesting.basico.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CustomerJdbcRepositoryTest {
	
	private CustomerJdbcRepository customerRepository;
	
	@BeforeEach
	void setup() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		customerRepository = new CustomerJdbcRepository(conn);
	}

	@Test
	void shouldList(){
		// GIVEN
		List<Customer> expecedCustomers = List.of(createDefaultCustomer(1000));
		
		// WHEN
		List<Customer> customers = customerRepository.list();
		
		// THEN
		assertEquals(expecedCustomers.size(), customers.size());
		assertEquals(expecedCustomers, customers);

	}
	
	@Test
	void shuldSave() {
		//GIVEN
		Customer customer = Customer.builder()
				.legalIdentifier(Mockito.anyString())
				.name(Mockito.anyString())
				.lastName(Mockito.anyString())
				.email(Mockito.anyString())
				.build();
		// WHEN 
		Integer generatedId = customerRepository.save(customer);
		
		// THEN
		customer.setId(generatedId);
		Customer insertedCustomer = customerRepository.get(generatedId);
		assertEquals(customer, insertedCustomer);
	}
	
	private Customer createDefaultCustomer(Integer id) {
		Customer customer = Customer.builder()
				.id(id)
				.legalIdentifier("12345678Z")
				.name("Michael")
				.lastName("Jordan")
				.email("michaeljordan@mail.com")
				.phoneNumber("611222333")
				.build();
		return customer;
	}

}
