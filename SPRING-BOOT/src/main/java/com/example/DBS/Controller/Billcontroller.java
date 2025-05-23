package com.example.DBS.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DBS.Entity.Billentity;
import com.example.DBS.InputDTO.BillinputDTO;
import com.example.DBS.Responses.ApiSuccess;
import com.example.DBS.Services.Billservice;

import jakarta.validation.Valid;

@RestController
public class Billcontroller {
	
	
	@Autowired
	Billservice billservice;
	
	
	
	//***********************************************CREATING A BILL*****************************************************
	@PostMapping("/createBill/{userid}")
	public ResponseEntity<ApiSuccess<Billentity>> createBill(@Valid @RequestBody BillinputDTO billdetails,@PathVariable Long userid)
	{
		 Billentity bill=billservice.createBill(billdetails,userid);
		 
		 ApiSuccess<Billentity> response= new ApiSuccess<>();
			
		    
    	response.setData(bill);
    	
    	response.setMessage("bill created successfully");
    	
    	response.setStatuscode(HttpStatus.CREATED.value());
		    
			
		return new ResponseEntity<>(response,HttpStatus.CREATED);
			
	}
	
	//**********************************************GET SALES  REPORT OF EACH MONTH************************************************
	@GetMapping("/monthly-sales-report/user/{userid}")
	public ResponseEntity<ApiSuccess<Page<Billentity>>> getMonthlyReport(@PathVariable Long userid,
			                                                             @RequestParam(required=false) Integer month,
			                                                             @RequestParam(required=false) Integer year,
			                                                             @RequestParam(defaultValue="0") int page,
			                                                             @RequestParam(defaultValue="2") int size
			                                                             )
	{
		Page<Billentity> bills=billservice.getMonthlyReport(month, year, page, size, userid);
		
		ApiSuccess<Page<Billentity>> response= new ApiSuccess<>();
		
	    
    	response.setData(bills);
    	
    	response.setMessage("Monthly sales reports fetched successfully");
    	
    	response.setStatuscode(HttpStatus.OK.value());
    	
    	return new ResponseEntity<>(response,HttpStatus.OK);
	}
	

}
