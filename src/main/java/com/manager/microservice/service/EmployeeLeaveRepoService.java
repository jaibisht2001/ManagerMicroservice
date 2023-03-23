package com.manager.microservice.service;

import java.util.List;

import com.manager.microservice.entity.EmployeeLeave;

public interface EmployeeLeaveRepoService {
	public EmployeeLeave getEmpLeaveByEmailandType(String email,String type);
	public void updateRemainingLeaveByEmailandType(int rmleave,String email,String type); 

}
