package com.example.DBS.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.DBS.Entity.Itementity;



@Repository
public interface ItemRepo extends JpaRepository<Itementity, Long> {
	
	//JPQL
	@Query("SELECT i FROM Itementity i WHERE i.user.id=:id")
	Page<Itementity> findAllPaginatedItemsByQuery(Pageable pageable,Long id);
}
