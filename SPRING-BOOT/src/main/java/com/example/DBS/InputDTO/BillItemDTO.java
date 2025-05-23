package com.example.DBS.InputDTO;

import java.math.BigDecimal;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;



public class BillItemDTO {
	
	
	
	@NotBlank(message="Item name cannot be null or empty string")
	@Size(min=4,max=15,message ="name should be between 5 and 15")
	private String name;
	
	@NotNull(message="Quantity cannot be null")
	@Positive(message="Quantity should be greater than or equal to 1 cannot be zero or negative")
	private Long quantity;
	
	@NotNull(message="Priceperunit cannot be null")
	@Positive(message="Priceperunit should be greater than or equal to 1 cannot be zero or negative")
	private BigDecimal priceperunit;
	
	@NotNull(message="subtotal cannot be null")
	@Positive(message="subtotal should be greater than or equal to 1 cannot be zero or negative")
	private BigDecimal subtotal;
	
	@NotNull(message="Item id cannot be null")
	private Long itemid;
	
	
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

	public Long getItemid() {
		return itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	

	

}
