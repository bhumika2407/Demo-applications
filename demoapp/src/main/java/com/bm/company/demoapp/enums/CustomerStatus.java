package com.bm.company.demoapp.enums;

public enum CustomerStatus {

	PROSPECTIVE("Prospective"), CURRENT("Current"), NON_ACTIVE("Non-active");
	
	private String name;
	
	private CustomerStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
