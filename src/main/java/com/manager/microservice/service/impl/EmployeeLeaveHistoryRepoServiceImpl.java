package com.manager.microservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.microservice.entity.EmployeeLeaveHistory;
import com.manager.microservice.repository.EmployeeLeaveHistoryRepo;
import com.manager.microservice.service.EmployeeLeaveHistoryRepoService;


@Service
public class EmployeeLeaveHistoryRepoServiceImpl implements EmployeeLeaveHistoryRepoService {

	@Autowired
	EmployeeLeaveHistoryRepo empleavehistoryrepo;
	@Override
	public String addLeave(EmployeeLeaveHistory empleavehistory) {
		empleavehistoryrepo.save(empleavehistory);
		return "LEAVE APPLIED";
	}

	@Override
	public List<EmployeeLeaveHistory> getLeaveHistory(String id) {
		
		List<EmployeeLeaveHistory> employeeLeaveHistories=empleavehistoryrepo.getLeaveHistoryByEmpId(id);
		return employeeLeaveHistories;
	}

	@Override
	public String updateLeaveStatus(String status, String id) {
         empleavehistoryrepo.updateLeaveStatus(status, id);
		return "LEAVE HISTORY UPDATED";
	}

	@Override
	public List<String> getLeaveHistoryByManager(String manager_id) {
		return empleavehistoryrepo.getLeaveHistoryByManager(manager_id);
		
	}

	@Override
	public EmployeeLeaveHistory getLeaveHistoryById(String id) {
		// TODO Auto-generated method stub
		return empleavehistoryrepo.findById(id).get();
	}

}
