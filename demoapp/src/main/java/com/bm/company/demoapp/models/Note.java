package com.bm.company.demoapp.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7031515765860164266L;

	@Id
	@GeneratedValue
	@Type(type="uuid-char")
	private UUID id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	private Boolean status;
	
	private Date modifiedTime;
	
	@CreatedDate
	private Date creationTime;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
