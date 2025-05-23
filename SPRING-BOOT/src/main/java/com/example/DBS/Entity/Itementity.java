package com.example.DBS.Entity;



import java.math.BigDecimal;
import java.util.List;

import com.example.DBS.ENUMS.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="items")
public class Itementity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemid;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@Column(nullable=false)
	private BigDecimal price;
	
	@Column(nullable=false)
	private Long quantityavailable;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	@JsonIgnore
	private Userentity user;
	
	@OneToMany(mappedBy="item", fetch = FetchType.LAZY)
	private List<Billitementity> billitem;
	
	

	public Long getItemid() {
		return itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getQuantityavailable() {
		return quantityavailable;
	}

	public void setQuantityavailable(Long quantityavailable) {
		this.quantityavailable = quantityavailable;
	}

	public Userentity getUser() {
		return user;
	}

	public void setUser(Userentity user) {
		this.user = user;
	}

}
