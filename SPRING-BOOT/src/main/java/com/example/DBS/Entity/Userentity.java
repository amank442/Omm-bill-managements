package com.example.DBS.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class Userentity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false,unique=true)
	private String email;
	
	@Column(nullable=false)
	private String shopname;
	
	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	
	@OneToMany(mappedBy="user",cascade = {CascadeType.ALL}, orphanRemoval = true,fetch = FetchType.LAZY)
	private List<Itementity> items;
	
	
	@OneToMany(mappedBy="user",cascade = {CascadeType.ALL}, orphanRemoval = true,fetch = FetchType.LAZY)
	private List<Billentity> bills;
	
	@Column(nullable=false)
	private LocalDateTime accountcreatedat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Itementity> getItems() {
		return items;
	}

	public void setItems(List<Itementity> items) {
		this.items = items;
	}

	public List<Billentity> getBills() {
		return bills;
	}

	public void setBills(List<Billentity> bills) {
		this.bills = bills;
	}
	
	

	public LocalDateTime getAccountcreatedat() {
		return accountcreatedat;
	}

	public void setAccountcreatedat(LocalDateTime accountcreatedat) {
		this.accountcreatedat = accountcreatedat;
	}

}
