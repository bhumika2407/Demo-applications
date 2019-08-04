package com.bm.company.demoapp.facades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bm.company.demoapp.enums.CustomerStatus;
import com.bm.company.demoapp.exceptions.ResourceNotFoundException;
import com.bm.company.demoapp.models.Customer;
import com.bm.company.demoapp.models.Note;
import com.bm.company.demoapp.service.CustomerService;
import com.bm.company.demoapp.service.NotesService;

@Component
public class CustomerFacadeImpl implements CustomerFacade {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private NotesService notesService;
	
	@Override
	public Customer createNotesForCustomer(final UUID customerId, final String notesContent) throws ResourceNotFoundException {
		Customer customer = customerService.getCustomerById( customerId );
		if(customer == null) {
			throw new ResourceNotFoundException("Customer with the UUId  "+ customerId +" doesn't exist");
		}
		Note note = notesService.createNote( notesContent );
		return customerService.addCustomerNotes(customer, note);
	}
	
	@Override
	public Customer createCustomer( Customer customer ) {
		
		if (CollectionUtils.isNotEmpty(customer.getNotes())) {
			List<Note> customerNotes =  new ArrayList<>();
			customer.getNotes().stream().forEach(note -> {
				if (note != null && StringUtils.isNoneEmpty(note.getContent())) {
					note.setCreationTime(new Date());
					note.setModifiedTime(new Date());
					note.setStatus(Boolean.TRUE);
					customerNotes.add(note);
				}
			});
			
			customer.setNotes(customerNotes);
		}
		
		customer.setCreationDate(new Date());
		return customerService.createCustomer(customer);
	}
	
	@Override
	public List<Customer> getFilteredAndSortedCustomers(final String name, final String status, final String sortBy) {
		if(StringUtils.isEmpty(name) && StringUtils.isEmpty(status) && StringUtils.isEmpty(sortBy)) {
			return customerService.getCustomers();
		}
		Customer exampleCustomer = new Customer();
		if (StringUtils.isNotEmpty(name)) {
			exampleCustomer.setName(name);
		}
		if (StringUtils.isNotEmpty(status)) {
			exampleCustomer.setStatus(CustomerStatus.valueOf(status));
		}
		return customerService.getCustomersByExample(exampleCustomer, sortBy);
		
	}
}
