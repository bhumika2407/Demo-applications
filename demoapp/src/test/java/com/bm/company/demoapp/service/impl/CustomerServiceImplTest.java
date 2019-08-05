package com.bm.company.demoapp.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.bm.company.demoapp.dao.CustomerRepository;
import com.bm.company.demoapp.enums.CustomerStatus;
import com.bm.company.demoapp.exceptions.ResourceNotFoundException;
import com.bm.company.demoapp.models.Customer;
import com.bm.company.demoapp.models.Note;

class CustomerServiceImplTest {
	
	
	@InjectMocks
	CustomerServiceImpl customerService;
	
	@Mock
	CustomerRepository customerRepository;
	
	private Customer customer;
	private UUID id;
	private UUID invalidId;
	
	@BeforeEach
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
		id =  UUID.fromString("86eacaa0-9e5e-4558-950b-a7892d35f05c");
		invalidId = UUID.fromString("86eacaa0-9e5e-4558-950b-a7892d35f05e");
		customer = new Customer();
		customer.setName("ABC Ltd");
		customer.setAddress("test address");
		customer.setContactNumber("0219999999");
		customer.setEmailAddress("abc@abc.com");
		customer.setCreationDate(new Date());
		customer.setStatus(CustomerStatus.CURRENT);
	}

	@Test
	@DisplayName("Testing get customers by example")
	void testGetCustomersByExample() {
		List<Customer> customers = new ArrayList<>();
		customers.add(customer);
		when(customerRepository.findAll(any(Example.of(customer).getClass()))).thenReturn(customers);
		when(customerRepository.findAll(any(Example.of(customer).getClass()), any(Sort.class))).thenReturn(customers);
		
		assertAll(() -> assertEquals( customers, customerService.getCustomersByExample(customer, null), " failed for call with sort parameters"),
				() -> assertEquals(customers, customerService.getCustomersByExample(customer, "name"), "failed for call without sort parameters"));
		
	}

	@Test
	@DisplayName("Testing get customer by id")
	void testGetCustomerById() throws ResourceNotFoundException {
		when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
		when(customerRepository.findById(invalidId)).thenReturn(Optional.empty());
		
		assertAll(() -> assertEquals( customer, customerService.getCustomerById(id), "failed for valid customer id"),
				() -> assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerById(invalidId), " failed for expecting ResourceNotFoundException"));
	}

	@Test
	@DisplayName("Testing create customer")
	void testCreateCustomer() {
		Customer createdCustomer = customer;
		createdCustomer.setId(id);
		when(customerRepository.save(any(Customer.class))).thenReturn(createdCustomer);
		assertEquals(createdCustomer, customerService.createCustomer(customer), "failed for creating a valid customer");
	}

	@Test
	@DisplayName("Test update customer status")
	void testUpdateCustomerStatus() {
		
		when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
		when(customerRepository.save(any(Customer.class))).thenReturn(customer);
		
		when(customerRepository.findById(invalidId)).thenReturn(Optional.empty());
		when(customerRepository.save(null)).thenReturn(Optional.empty());
		
		assertAll(() -> assertEquals(customer, customerService.updateCustomerStatus(id, CustomerStatus.CURRENT)),
				() -> assertThrows(ResourceNotFoundException.class,
						() -> customerService.updateCustomerStatus(invalidId, any(CustomerStatus.class))));

	}

	@Test
	@DisplayName(" Test add customer notes")
	void testAddCustomerNotes() throws ResourceNotFoundException {
		Customer customerWithNotes = new Customer();
		List<Note> notes = new ArrayList<>();
		notes.add(new Note());
		customerWithNotes.setNotes(notes);
		when(customerRepository.save(any(Customer.class))).thenReturn(customerWithNotes);
		
		assertEquals(customerWithNotes, customerService.addCustomerNotes(customerWithNotes, any(Note.class)));
	}

}
