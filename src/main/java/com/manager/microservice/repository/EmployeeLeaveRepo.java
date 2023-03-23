package com.manager.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.manager.microservice.entity.EmployeeLeave;



public interface EmployeeLeaveRepo extends JpaRepository<EmployeeLeave,String> {
	@Query(value="SELECT * FROM employee_leave WHERE email=? AND leave_type=?",nativeQuery=true)
	public EmployeeLeave getLeaveByEmailandType(String email,String type);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE employee_leave SET leave_remaining=? WHERE email=? AND leave_type=?",nativeQuery = true)
	public void updateRemainingLeaveByEmailandType(int rmleave,String email,String type);

}
