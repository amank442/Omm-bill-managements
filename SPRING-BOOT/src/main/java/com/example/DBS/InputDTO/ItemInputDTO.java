package com.example.DBS.InputDTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;



public class ItemInputDTO {
	
	
	
	@NotBlank(message="give proper name")
	@Size(min=4,max=15,message ="name should be between 5 and 15")
    private String name;
	
	@NotNull(message="cannot be null")
	@Positive(message="price cannot be negative it should be positive")
	private BigDecimal price;
	
	@NotBlank(message="give proper category")
	private String category;
	
	

	@NotNull(message="cannot be null")
	@PositiveOrZero(message="quantity cannot be negative")
	private Long quantityavailable;



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public BigDecimal getPrice() {
		return price;
	}



	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public Long getQuantityavailable() {
		return quantityavailable;
	}



	public void setQuantityavailable(Long quantityavailable) {
		this.quantityavailable = quantityavailable;
	}

}
