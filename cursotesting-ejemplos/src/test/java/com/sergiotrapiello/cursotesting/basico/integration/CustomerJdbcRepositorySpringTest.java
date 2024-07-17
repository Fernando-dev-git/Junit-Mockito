package com.sergiotrapiello.cursotesting.basico.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.jdbc.JdbcTestUtils;

class CustomerJdbcRepositorySpringTest {

	private CustomerJdbcRepository customerRepository;
	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setup() {
		EmbeddedDatabase db = new EmbeddedDatabaseBuilder().generateUniqueName(true).setType(EmbeddedDatabaseType.H2)
				.setScriptEncoding("UTF-8").addScripts("customers-schema.sql", "customer-dataset.sql").build();
		customerRepository = new CustomerJdbcRepository(db);
		jdbcTemplate = new JdbcTemplate(db);
	}

	@Test
	void shouldList() throws SQLException {
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
		// GIVEN
		Customer customer = Customer.builder().legalIdentifier("22222342N").name("fernando").lastName("sanchez")
				.email("milo@dominio.com").build();

		int rowsBefore = countRows();

		// WHEN
		Integer generatedId = customerRepository.save(customer);

		// THEN
		assertEquals(rowsBefore + 1, countRows());
		Map<String, Object> insertedMap = jdbcTemplate.queryForMap("select * from customer where id = ?",
				generatedId);
		assertEquals(customer.getLegalIdentifier(), insertedMap.get("LEGAL_IDENTIFIER"));
		assertEquals(customer.getName(), insertedMap.get("NAME"));
		assertEquals(customer.getLastName(), insertedMap.get("LASTNAME"));
		assertEquals(customer.getEmail(), insertedMap.get("EMAIL"));
		assertEquals(customer.getPhoneNumber(), insertedMap.get("PHONE"));
	}
	
	@Test
	void shouldGet() {
		// GIVEN
		String legalIdentifier = "12345678Z";
		Map<String, Object> insertedMap = 
				jdbcTemplate.queryForMap("select * from customer where LEGAL_IDENTIFIER = ?",
						legalIdentifier);	
		Integer consultedId = (Integer) insertedMap.get("id");
		
		// WHEN 
		Customer customer = customerRepository.get(consultedId);

		// THEN
		assertNotNull(customer);
		assertEquals(customer.getName(), insertedMap.get("NAME"));
	}
	
	@Test
	void shouldGet_null() {

		// GIVEN
		Integer idNotExist = 10001;
		
		// WHEN
		Customer customer = customerRepository.get(idNotExist);
		
		// THEN
		assertNull(customer);
	}

	private int countRows() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, "CUSTOMER");
	}

	private Customer createDefaultCustomer(Integer id) {
		Customer customer = Customer.builder().id(id).legalIdentifier("12345678Z").name("Michael").lastName("Jordan")
				.email("michaeljordan@mail.com").phoneNumber("611222333").build();
		return customer;
	}

}
