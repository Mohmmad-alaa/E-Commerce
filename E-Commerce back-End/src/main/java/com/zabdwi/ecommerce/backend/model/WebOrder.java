package com.zabdwi.ecommerce.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class WebOrder {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private LocalUser user;

	@ManyToOne(optional = false)
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;

	public LocalUser getUser() {
		return user;
	}

	public void setUser(LocalUser user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}