package com.manager.microservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.microservice.entity.EmployeeLeave;
import com.manager.microservice.repository.EmployeeLeaveRepo;
import com.manager.microservice.service.EmployeeLeaveRepoService;
@Service
public class EmployeeLeaveRepoServiceImpl  implements EmployeeLeaveRepoService{
	@Autowired
	EmployeeLeaveRepo employeeLeaveRepo;

	@Override
	public EmployeeLeave getEmpLeaveByEmailandType(String email, String type) {
		// TODO Auto-generated method stub
		return employeeLeaveRepo.getLeaveByEmailandType(email, type);
	}

	
	public void updateRemainingLeaveByEmailandType(int rmleave, String email, String type) {
		// TODO Auto-generated method stub
		employeeLeaveRepo.updateRemainingLeaveByEmailandType(rmleave, email, type);
	}

	

}
