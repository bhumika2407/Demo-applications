package com.bm.company.demoapp.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import com.bm.company.demoapp.enums.CustomerStatus;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1999498437964771216L;

	@Id
	@GeneratedValue
	@Type(type="uuid-char")
	private UUID id;

	@Enumerated(EnumType.ORDINAL)
	private CustomerStatus status;

	private String name;
	
	private String contactPersonName;

	private String emailAddress;

	private String contactNumber;

	private String address;

	@CreatedDate
	private Date creationDate;


	@OneToMany( fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Note> notes;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	


}
