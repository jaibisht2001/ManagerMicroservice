package com.manager.microservice.service;

import java.util.List;

import com.manager.microservice.entity.EmployeeLeaveHistory;

public interface EmployeeLeaveHistoryRepoService {
	public EmployeeLeaveHistory getLeaveHistoryById(String id);
	public String addLeave(EmployeeLeaveHistory empleavehistory);
	public List<EmployeeLeaveHistory> getLeaveHistory(String id);
	public String updateLeaveStatus(String status,String id);
	public List<String> getLeaveHistoryByManager(String manager_id);

}
