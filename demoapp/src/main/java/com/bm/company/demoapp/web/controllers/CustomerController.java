package com.bm.company.demoapp.web.controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bm.company.demoapp.constants.CommonConstants;
import com.bm.company.demoapp.exceptions.ResourceNotFoundException;
import com.bm.company.demoapp.facades.CustomerFacade;
import com.bm.company.demoapp.models.Customer;
import com.bm.company.demoapp.models.Note;
import com.bm.company.demoapp.service.CustomerService;

@CrossOrigin
(origins = CommonConstants.CLIENT_BASE_URI, maxAge = 3600)
@RestController
@RequestMapping("/customers")
public class CustomerController extends BaseController{
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerFacade customerFacade;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@GetMapping
	public ResponseEntity<List<Customer>> getCustomers(@RequestParam(required = false) String name, @RequestParam(required = false) String status,
			@RequestParam(required = false) String sortBy ) {
		return new ResponseEntity<List<Customer>>(customerFacade.getFilteredAndSortedCustomers(name, status, sortBy), HttpStatus.OK);
	}
	
	@GetMapping( value = "/{id}")
	public ResponseEntity<Customer> getCustomerDetails( @PathVariable UUID id ) throws ResourceNotFoundException {
		LOGGER.debug("Getting customer details for {}", id);
		Customer customer = customerService.getCustomerById( id );
		return new ResponseEntity<Customer>( customer, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		LOGGER.debug("Creating a new customer");
		return new ResponseEntity<>( customerFacade.createCustomer(customer), HttpStatus.CREATED );
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Customer> updateCustomerStatus( @PathVariable UUID id, @RequestBody Customer updatedCustomer) throws ResourceNotFoundException {
		LOGGER.debug("Updating customer {} with status {} ", id, updatedCustomer.getStatus());
		updatedCustomer = customerService.updateCustomerStatus( id, updatedCustomer.getStatus());
		return new ResponseEntity<>( updatedCustomer, HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}/note")
	public ResponseEntity<Customer> createCustomerNotes( @PathVariable(name="id") UUID id, @RequestBody Note note) throws ResourceNotFoundException {
		LOGGER.debug("Creating notes for customer {} ", id);
		Customer updatedCustomer = customerFacade.createNotesForCustomer(id, note.getContent());
		return new ResponseEntity<>( updatedCustomer, HttpStatus.OK) ;
	}
	
}
