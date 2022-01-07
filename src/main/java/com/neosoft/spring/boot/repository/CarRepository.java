package com.neosoft.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.neosoft.spring.boot.model.AddCar;

public interface CarRepository extends JpaRepository<AddCar, Integer>{
	
	
	//issued part
 	@Transactional
	@Modifying
	@Query("UPDATE AddCar c SET c.issued = :issued WHERE c.carno = :carno")
	void updateCarIssueStatus(@Param("issued") int issued, @Param("carno") int carno); 
 	
 	
	@Query("SELECT c.issued FROM AddCar c WHERE c.carno = :carno")
 	int getIssuedCarValue(@Param("carno") int carno);
	
	@Query("SELECT c.quantity FROM AddCar c WHERE c.carno = :carno")
 	int getCarQuantityValue(@Param("carno") int carno);
	
	
	//return car part
	
	@Transactional
	@Modifying
	@Query("UPDATE AddCar c SET c.returnstatus = :returnstatus WHERE c.carno = :carno")
	void returnIssuedCar(@Param("returnstatus") String returnstatus, @Param("carno") int carno); 
 	
 	
	//@Query("SELECT c.returnstatus FROM AddCar c WHERE c.carno = :carno")
 	//String getReturnCarValue(@Param("carno") int carno);
	

}
