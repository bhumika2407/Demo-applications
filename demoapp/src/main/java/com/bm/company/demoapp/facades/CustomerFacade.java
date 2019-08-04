package com.bm.company.demoapp.facades;

import java.util.List;
import java.util.UUID;

import com.bm.company.demoapp.exceptions.ResourceNotFoundException;
import com.bm.company.demoapp.models.Customer;

public interface CustomerFacade {

	Customer createNotesForCustomer(UUID customerId, String notesContent) throws ResourceNotFoundException;

	Customer createCustomer( Customer customer );

	List<Customer> getFilteredAndSortedCustomers(String name, String status, String sortBy);

}
