package com.bm.company.demoapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bm.company.demoapp.web.controllers.CustomerController;


@SpringBootTest
public class CompanyDemoAppApplicationTests {
	
	@Autowired
	private CustomerController customerController;
	
	@Test
	public void contextLoads() {
		assertThat(customerController).isNotNull();
	}

}
