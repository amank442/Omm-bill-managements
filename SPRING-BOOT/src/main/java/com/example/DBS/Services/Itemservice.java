package com.example.DBS.Services;

import java.math.BigDecimal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.DBS.ENUMS.Category;
import com.example.DBS.Entity.Itementity;
import com.example.DBS.Entity.Userentity;
import com.example.DBS.Exceptions.ItemNotFound;
import com.example.DBS.Exceptions.UserNotFound;
import com.example.DBS.InputDTO.ItemInputDTO;
import com.example.DBS.InputDTO.ItemupdateDTO;
import com.example.DBS.Repository.ItemRepo;
import com.example.DBS.Repository.ItemUpdateDaoimpl;
import com.example.DBS.Repository.Userrepo;


@Service
public class Itemservice {
	
	
	@Autowired
	ItemRepo itemrepo;
	
	@Autowired
	Userrepo userrepo;
	
	@Autowired
	ItemUpdateDaoimpl itemupdatedao;
	
	
	//**********************************************LOGIC TO ADD A NEW ITEM *****************************************************
	public Itementity addItem(ItemInputDTO input,Long userid)
	{
		
		Userentity u= userrepo.findById(userid).orElseThrow(()->new UserNotFound("user not found"));
		
		Category c= Category.valueOf(input.getCategory().toUpperCase());
		
		//inserting items to itementity
		
		Itementity item= new Itementity();
		item.setCategory(c);
		item.setName(input.getName().toLowerCase());
		item.setPrice(input.getPrice());
		item.setQuantityavailable(input.getQuantityavailable());
		item.setUser(u);
		
		
		return itemrepo.save(item);
		
	}
	
	//*****************************************************UPDATE THE ITEM*************************************************************
	public int updateItem(ItemupdateDTO input)
	{
		return itemupdatedao.updateItemByCategory(input);
	}
	
	
	//******************************************************DELETE ITEM***********************************************************
	public boolean deleteItem(Long itemid)
	{
		Itementity i=itemrepo.findById(itemid).orElseThrow(()->new ItemNotFound("item not found"));
		itemrepo.delete(i);
		return true;
	}
	
	//****************************************************GETTING PAGINATED ITEMS LIST***********************************************
	public Page<Itementity> userItems(int page, int size, Long user_id) 
	{
		userrepo.findById(user_id).orElseThrow(()->new UserNotFound("user not found"));
	
        Pageable pageable= PageRequest.of(page, size);
		
		Page<Itementity> p=itemrepo.findAllPaginatedItemsByQuery(pageable,user_id);
		
		return p;
	}
	

}
