package com.example.DBS.Repository;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.example.DBS.DAO.ItemUpdateDao;
import com.example.DBS.Entity.Itementity;
import com.example.DBS.Exceptions.ItemNotFound;
import com.example.DBS.Exceptions.NoFeildsProvided;
import com.example.DBS.InputDTO.ItemupdateDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class ItemUpdateDaoimpl implements ItemUpdateDao {
	
	
	@Autowired
	EntityManager entitymanager;
	
	@Autowired
    ItemRepo itemrepo;
	
	@Override
	@Transactional
	public int updateItemByCategory(ItemupdateDTO input)
	{
        CriteriaBuilder cb= entitymanager.getCriteriaBuilder();
		
		CriteriaUpdate<Itementity>update=cb.createCriteriaUpdate(Itementity.class);
		
		
		Root<Itementity> root= update.from(Itementity.class);
		
		boolean feildupdated= false;
		
		
		if(input.getPrice()!=null && !input.getPrice().toString().trim().isEmpty())
		{
			System.out.println("inside price");
			
			update.set("price",input.getPrice());
			feildupdated = true;
		}
		
		if(input.getQuantity()!=null && !input.getQuantity().toString().trim().isEmpty())
		{
			System.out.println("inside quantity");
			update.set("quantityavailable", input.getQuantity());
			feildupdated=true;
		}
		
		if(!feildupdated)
		{
			throw new NoFeildsProvided("no feilds provided for updation");
		}
		 
		 itemrepo.findById(input.getItemid()).orElseThrow(()->new ItemNotFound("item not found"));
		 
		 update.where(cb.equal(root.get("itemid"),input.getItemid()));
		 
		 return entitymanager.createQuery(update).executeUpdate();
			
	}

}
