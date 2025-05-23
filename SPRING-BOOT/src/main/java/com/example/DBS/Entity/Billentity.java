package com.example.DBS.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "bills")
public class Billentity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates IDs
	private Long billid;

	@Column(nullable = false)
	private String customername;

	@Column(nullable = false)
	@PastOrPresent(message = "Date cannot be in the future")
	private LocalDate date;

	@Column(nullable = false)
	private BigDecimal totalamount;

	@Column(nullable = false)
	private BigDecimal taxamount;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private Userentity user;

	@OneToMany(mappedBy = "bill", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Billitementity> billitems;

	public Long getBillid() {
		return billid;
	}

	public void setBillid(Long billid) {
		this.billid = billid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(BigDecimal totalamount) {
		this.totalamount = totalamount;
	}

	public BigDecimal getTaxamount() {
		return taxamount;
	}

	public void setTaxamount(BigDecimal taxamount) {
		this.taxamount = taxamount;
	}

	public Userentity getUser() {
		return user;
	}

	public void setUser(Userentity user) {
		this.user = user;
	}

	public List<Billitementity> getBillitems() {
		return billitems;
	}

	public void setBillitems(List<Billitementity> billitems) {
		this.billitems = billitems;
	}

}
