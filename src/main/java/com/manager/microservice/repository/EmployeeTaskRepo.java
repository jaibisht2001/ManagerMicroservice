package com.manager.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.manager.microservice.entity.EmployeeTask;

public interface EmployeeTaskRepo extends JpaRepository<EmployeeTask, String> {
	
	 @Query(value="SELECT * FROM employee_task where manager_id=?",nativeQuery = true) 
		public List<EmployeeTask> getTaskByEmpId(String id);
	 
	 

}
