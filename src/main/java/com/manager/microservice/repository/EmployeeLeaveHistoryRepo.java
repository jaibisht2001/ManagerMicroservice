package com.manager.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.manager.microservice.entity.EmployeeLeaveHistory;

import jakarta.transaction.Transactional;

public interface EmployeeLeaveHistoryRepo  extends JpaRepository<EmployeeLeaveHistory,String>{
    @Query(value="SELECT * FROM employee_leave_history where id=?",nativeQuery = true) 
	public List<EmployeeLeaveHistory> getLeaveHistoryByEmpId(String id);
    
    @Modifying
    @Transactional
    @Query(value="UPDATE employee_leave_history SET status=? WHERE leave_history_id=?",nativeQuery = true)
    void updateLeaveStatus(String status,String id);
    
    @Query(value="SELECT elh.leave_history_id,elh.email,elh.from_date,elh.to_date,elh.id,elh.leave_type,elh.reason,elh.status"
    		+ " FROM employee_leave_history  AS  elh JOIN employee_details AS ed "
    		+ "ON elh.email=ed.email WHERE ed.manager_id=?",nativeQuery=true)
    public List<String> getLeaveHistoryByManager(String manager_id) ;
    
}
