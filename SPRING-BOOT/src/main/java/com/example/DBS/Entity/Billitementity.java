package com.example.DBS.Entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="billitems")
public class Billitementity {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates IDs 
	private Long billitemid;
	
	 @Column(nullable=false)
	private String name;
	
	 @Column(nullable=false)
	private Long quantity;
	
	 @Column(nullable=false)
	private BigDecimal priceperunit;
	
	 @Column(nullable=false)
	private BigDecimal subtotal;
	
	
	@ManyToOne
	@JoinColumn(name="bill_id",nullable=false)
	@JsonIgnore
	private Billentity bill;
	
	
	@ManyToOne
	@JoinColumn(name="item_id",nullable=true)
	@JsonIgnore
	private Itementity item;


	public Long getBillitemid() {
		return billitemid;
	}


	public void setBillitemid(Long billitemid) {
		this.billitemid = billitemid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getPriceperunit() {
		return priceperunit;
	}


	public void setPriceperunit(BigDecimal priceperunit) {
		this.priceperunit = priceperunit;
	}


	public BigDecimal getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}


	public Billentity getBill() {
		return bill;
	}


	public void setBill(Billentity bill) {
		this.bill = bill;
	}


	public Itementity getItem() {
		return item;
	}


	public void setItem(Itementity item) {
		this.item = item;
	}
}
