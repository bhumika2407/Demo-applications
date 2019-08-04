package com.bm.company.demoapp.service;

import java.util.List;
import java.util.UUID;

import com.bm.company.demoapp.enums.CustomerStatus;
import com.bm.company.demoapp.exceptions.ResourceNotFoundException;
import com.bm.company.demoapp.models.Customer;
import com.bm.company.demoapp.models.Note;

public interface CustomerService {

	List<Customer> getCustomers();
	
	Customer createCustomer(final Customer customer);
	
	List<Customer> createCustomers(List<Customer> customers);
	
	Customer getCustomerById(final UUID id) throws ResourceNotFoundException;
	
	Customer updateCustomerStatus(UUID id, CustomerStatus status) throws ResourceNotFoundException;

	Customer addCustomerNotes(Customer customer, Note note) throws ResourceNotFoundException;

	List<Customer> getCustomersByExample( Customer customer, String sortBy);

	
}
