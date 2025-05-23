package com.example.DBS.InputDTO;

import java.util.List;



import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class BillinputDTO {
	
	
	@NotBlank(message="Customer anme cannot be null")
	@Size(min=4,max=15,message ="length of name should be between 5 and 15")
	private String customername;
	
	
	
	
	@NotNull(message="bill items cannot be null")
	@Size(min=1,message="Atleast 1 item should be there")
	@Valid
	private List<BillItemDTO> billitemdtos;


	public String getCustomername() {
		return customername;
	}


	public void setCustomername(String customername) {
		this.customername = customername;
	}


	


	public List<BillItemDTO> getBillitemdtos() {
		return billitemdtos;
	}


	public void setBillitemdtos(List<BillItemDTO> billitemdtos) {
		this.billitemdtos = billitemdtos;
	}
	

}
