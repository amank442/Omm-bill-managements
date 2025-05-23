package com.example.DBS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DBS.Entity.Userentity;


@Repository
public interface Userrepo extends JpaRepository<Userentity, Long> {
	
	
	
	
	//DERIVED QUERY METHOD TO CHECK PROVIDED EMAIL IS THERE OR NOT
	Userentity findByEmail(String email);
	
	

}
