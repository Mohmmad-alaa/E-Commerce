package com.zabdwi.ecommerce.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Inventory {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;

	@OneToOne(optional = false, orphanRemoval = true)
	@JoinColumn(name = "product_id", nullable = false, unique = true)
	private Product product;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
