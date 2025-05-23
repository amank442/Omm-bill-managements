package com.example.DBS.Services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.DBS.Entity.Billentity;
import com.example.DBS.Entity.Billitementity;
import com.example.DBS.Entity.Itementity;
import com.example.DBS.Entity.Userentity;
import com.example.DBS.Exceptions.Insuffiecientstock;
import com.example.DBS.Exceptions.ItemNotFound;
import com.example.DBS.Exceptions.UnauthorizedItemAcces;
import com.example.DBS.Exceptions.UserNotFound;
import com.example.DBS.InputDTO.BillItemDTO;
import com.example.DBS.InputDTO.BillinputDTO;

import com.example.DBS.Repository.Billrepo;
import com.example.DBS.Repository.ItemRepo;
import com.example.DBS.Repository.Userrepo;

import jakarta.transaction.Transactional;

@Service
public class Billservice {
	
	@Autowired
	Billrepo billrepo;
	
	@Autowired
	Userrepo userrepo;
	
	@Autowired
	ItemRepo itemrepo;
	
	
	//******************************************CREATE A BILL*******************************************************
	
	@Transactional
	public Billentity createBill(BillinputDTO billdetails,Long userid) {

	    //  Get user
	    Userentity user = userrepo.findById(userid)
	            .orElseThrow(() -> new UserNotFound("User account does not exist"));

	    // Check item availability & update stock
	    for(BillItemDTO billitem : billdetails.getBillitemdtos()) 
	    
	    {
	       
	        
	        Itementity item = itemrepo.findById(billitem.getItemid()).orElseThrow(() -> new ItemNotFound("Item does not exist"));
	        
	        //here i want to check wheather the entered item is matching with the user item list
	        
	        if(!user.getItems().contains(item)) {
	        	throw new UnauthorizedItemAcces("User does not own item with ID: " + item.getItemid());
	        }
	        

	        Long quantity = billitem.getQuantity();
	        
	        
	        //checking the quantity
	        if (item.getQuantityavailable()<quantity) 
	        
	        {
	            throw new Insuffiecientstock(item.getName() + " stock is insufficient");
	        }

	        
	        //updating the stock 
	        item.setQuantityavailable(item.getQuantityavailable() - quantity);
	        
	        
	        itemrepo.save(item);
	    }

	    //  Create bill and bill items
	    Billentity bill = new Billentity();
	    
	    bill.setCustomername(billdetails.getCustomername());
	    
	    bill.setDate(LocalDate.now());
	    
	    //link the bill to the user
	    bill.setUser(user);
	    
	    bill.setBillitems(new ArrayList<>());
	    
        BigDecimal totalamount=BigDecimal.ZERO;
        
	    for (BillItemDTO itemDTO : billdetails.getBillitemdtos())
	    
	    {
	        Billitementity billItem = new Billitementity();
	        
	        Itementity item= itemrepo.findById(itemDTO.getItemid()).orElse(null);
	        
	        billItem.setItem(item);
	        
	        billItem.setQuantity(itemDTO.getQuantity());
	        
	        billItem.setPriceperunit(itemDTO.getPriceperunit());
	        
	        billItem.setSubtotal(itemDTO.getSubtotal());
	        
	        billItem.setName(itemDTO.getName());
	        
	        billItem.setBill(bill);
	        
	       
	        
	        bill.getBillitems().add(billItem);
	        
	        //adding total amount of bill
	        totalamount=totalamount.add(itemDTO.getSubtotal());
	        
	    }
	    
	    //apply 10% GST
	    BigDecimal taxableamount= totalamount.multiply(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(100));
	    
	    bill.setTaxamount(taxableamount);
	    
	    //modifying total amount (add taxamount)
	    bill.setTotalamount(totalamount.add(taxableamount));
	    
	   

	    //  Save the bill once, after setting everything
	    Billentity savedBill = billrepo.save(bill);

	    //  Add to user's bill list and save user
	    if (user.getBills() == null) {
	    	
	        user.setBills(new ArrayList<>());
	        
	    }
	    
	    user.getBills().add(savedBill);
	    
	    userrepo.save(user);

	    return savedBill;
	}
	
	//******************************************************GET MONTHLY SALES REPORT****************************************************
	
	public Page<Billentity> getMonthlyReport(Integer month,Integer year,int page,int size,Long userid)
	{
		//check first user exist or not
		userrepo.findById(userid).orElseThrow(()->new UserNotFound("user do not exist"));
		
		Pageable pageable= PageRequest.of(page, size);
		
		
		//checking wheather the user have provided the month or not if not taking current month
		month= month==null?LocalDate.now().getMonthValue():month;
		
		//checking wheather the user have provided the year or not if not taking current year
		year= year==null?LocalDate.now().getYear():year;
		
			
	    return billrepo.getSalesReport(month, year, userid,pageable);
		
		

		
		
	}
}
