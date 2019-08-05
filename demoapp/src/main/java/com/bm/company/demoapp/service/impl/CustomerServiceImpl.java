package com.bm.company.demoapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bm.company.demoapp.dao.CustomerRepository;
import com.bm.company.demoapp.enums.CustomerStatus;
import com.bm.company.demoapp.exceptions.ResourceNotFoundException;
import com.bm.company.demoapp.models.Customer;
import com.bm.company.demoapp.models.Note;
import com.bm.company.demoapp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
	
	@Override
	public List<Customer> getCustomersByExample( final Customer customer, final String sortBy) {
		List<Customer> customers = null;
		ExampleMatcher nameMatcher = ExampleMatcher
	            .matchingAll()
	            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
	            .withIgnoreCase();
		Example<Customer> exampleCustomer = Example.of(customer, nameMatcher);
		
		if(StringUtils.isNotEmpty(sortBy)) {
			Sort sort = new Sort(Direction.ASC, sortBy);
			customers = customerRepository.findAll( exampleCustomer,sort);
		}else {
			customers =  customerRepository.findAll( exampleCustomer );
		}
		return customers;
	}
	
	@Override
	public Customer getCustomerById(final UUID id) throws ResourceNotFoundException {
		Optional<Customer> customer = customerRepository.findById(id);
		if(!customer.isPresent() ) {
			throw new ResourceNotFoundException("Customer with id " + id + " doesn't exist");
		}
		return customer.get();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	@Override
	public List<Customer> createCustomers(List<Customer> customers) {
		return (List<Customer>) customerRepository.saveAll(customers);
	}
	
	@Override
	public Customer updateCustomerStatus(final UUID id, CustomerStatus status) throws ResourceNotFoundException {
		LOGGER.debug("Updating a status of customer {}", id );
		Optional<Customer> existingCustomerOptional = customerRepository.findById(id);
		if(!existingCustomerOptional.isPresent()) {
			throw new ResourceNotFoundException("Customer with the UUId  "+ id +" doesn't exist");
		}
		Customer existingCustomer = existingCustomerOptional.get();
		existingCustomerOptional.get().setStatus(status);
		return customerRepository.save(existingCustomer);
	}

	@Override
	public Customer addCustomerNotes(final Customer customer, final Note note) throws ResourceNotFoundException {
		LOGGER.debug("Adding notes  for customer {}", customer.getId() );
		List<Note> customerNotes =  customer.getNotes(); 
		customerNotes.add(note);
		customer.setNotes(customerNotes);
		return customerRepository.save(customer);
	}


}
