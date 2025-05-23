package com.example.DBS.InputDTO;

import java.math.BigDecimal;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ItemupdateDTO {
	
	
	@NotNull(message="item id can,t be null")
	private Long itemid;
	
	
	@Positive(message="price cannot be negative it should be positive")
	private BigDecimal price;
	
	
	@PositiveOrZero(message="quantity cannot be negative")
	private Long quantity;
	
	public Long getItemid() {
		return itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	

	

}
