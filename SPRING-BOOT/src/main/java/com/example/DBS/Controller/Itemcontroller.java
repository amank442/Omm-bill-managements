
package com.example.DBS.Controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DBS.Entity.Itementity;
import com.example.DBS.InputDTO.ItemInputDTO;
import com.example.DBS.InputDTO.ItemupdateDTO;
import com.example.DBS.Responses.ApiSuccess;
import com.example.DBS.Services.Itemservice;


import jakarta.validation.Valid;

@RestController
public class Itemcontroller {
	
	
	@Autowired
	Itemservice itemservice;
	
	//************************************ADD A ITEM TO DB*************************************
	@PostMapping("/addItem/{user_id}")
	public ResponseEntity<ApiSuccess<Itementity>> addItem(@Valid @RequestBody ItemInputDTO input,@PathVariable Long user_id)
	{
		Itementity i=itemservice.addItem(input,user_id);
		ApiSuccess<Itementity> response= new ApiSuccess<>();
		
		response.setStatuscode(HttpStatus.CREATED.value());
		response.setMessage("Item added successfully");
		response.setData(i);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}
	
	//****************************************UPDATE A ITEM EITHER BY PRICE/QUANTITY OR BOTH AS DEPEND ON USER****************************
	
	@PatchMapping("/updateItem")
	public ResponseEntity<ApiSuccess<Boolean>> updateItem(@Valid @RequestBody ItemupdateDTO input)
	{
		ApiSuccess<Boolean> response= new ApiSuccess<>();
		
	    if(itemservice.updateItem(input)>0)
	    {
	    	response.setData(true);
	    	response.setMessage("item updated successfully");
	    	response.setStatuscode(HttpStatus.OK.value());
	    }
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	//****************************************************DELETE ITEM*************************************************************
	
	@DeleteMapping("/deleteItem/{itemid}")
	public ResponseEntity<ApiSuccess<Boolean>> deleteItem(@PathVariable Long itemid)
	{
		ApiSuccess<Boolean> response= new ApiSuccess<>();
		
		if(itemservice.deleteItem(itemid)) {
			
			response.setStatuscode(HttpStatus.OK.value());
			
			response.setMessage("Item deleted successfully");
			
			response.setData(true);
			
			
		}
		
		return new ResponseEntity<>(response,HttpStatus.OK);
			
	}
	
	//**********************************************************GETTING PAGINTED ITEMS ****************************************************
	
	@GetMapping("/user/{user_id}/items")
	public ResponseEntity<ApiSuccess<Page<Itementity>>> getPaginatedOrders(
			@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="2") int size,@PathVariable Long user_id)
	{
		
		Page<Itementity> items=itemservice.userItems(page, size, user_id);
		
		ApiSuccess<Page<Itementity>> response= new ApiSuccess<>();
		response.setData(items);
		response.setMessage("items fetched successfully");
		response.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<>(response,HttpStatus.OK);
		
		
	}
	

}
