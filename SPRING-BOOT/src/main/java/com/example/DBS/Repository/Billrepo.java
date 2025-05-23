package com.example.DBS.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.DBS.Entity.Billentity;


public interface Billrepo extends JpaRepository<Billentity, Long> {

	
	@Query(value="SELECT * FROM bills WHERE MONTH(date)=:month AND YEAR(date)=:year AND user_id=:userid ORDER BY date",nativeQuery=true)
	Page<Billentity> getSalesReport(Integer month,Integer year,Long userid,Pageable pagerequest);
}
