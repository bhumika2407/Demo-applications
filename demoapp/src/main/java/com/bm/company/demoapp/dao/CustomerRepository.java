package com.bm.company.demoapp.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.company.demoapp.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{

}
