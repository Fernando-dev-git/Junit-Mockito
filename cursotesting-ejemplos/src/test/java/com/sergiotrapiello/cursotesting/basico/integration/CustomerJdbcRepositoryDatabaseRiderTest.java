package com.sergiotrapiello.cursotesting.basico.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.connection.ConnectionHolderImpl;
import com.github.database.rider.junit5.DBUnitExtension;

@ExtendWith(DBUnitExtension.class)
@DataSet("customers.yml")
class CustomerJdbcRepositoryDatabaseRiderTest {
	
	private CustomerJdbcRepository customerRepository;
	
	private ConnectionHolder connectionHolder;
	
	@BeforeEach
	void setup() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:h2:mem:test;INIT=runscript from 'classpath:customers-schema.sql' ", "sa", "");
		customerRepository = new CustomerJdbcRepository(conn);
		connectionHolder = new ConnectionHolderImpl(conn);
	}

	@Test
	void shouldList(){
		// GIVEN
		List<Customer> expecedCustomers = List.of(createDefaultCustomer(1));
		
		// WHEN
		List<Customer> customers = customerRepository.list();
		
		// THEN
		assertEquals(expecedCustomers.size(), customers.size());
		assertEquals(expecedCustomers, customers);

	}
	
	@Test
	@ExpectedDataSet(value = "customer_expected_save.yml", ignoreCols = "id")
	void shuldSave() {
		//GIVEN
		Customer customer = Customer.builder()
				.legalIdentifier("12345678901")
				.name("Fernando")
				.lastName("Sanchez")
				.email("algo@dominio.com")
				.phoneNumber("5542333503")
				.build();
		
		// WHEN 
		customerRepository.save(customer);
		
		// THEN
		// See annotation 
	}
	
	@Test
	@ExpectedDataSet(value = "customer_get_success.yml" )
	void shouldGet() {
		// GIVEN
		int consultedId = 1;

		// WHEN
		customerRepository.get(consultedId);

		// THEN
	}
	
	@Test
	void shouldGet_FAIL() {

		// GIVEN
		int consultedId = 12;

		// WHEN
		Customer customer = customerRepository.get(consultedId);

		// THEN
		assertNull(customer);
	}
	
	@Test
	@ExpectedDataSet(value = "customer_del_success.yml")
	void shouldDelete() {

		// GIVEN
		Integer id = 1;

		// WHEN
		customerRepository.delete(id);

		// THEN
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
