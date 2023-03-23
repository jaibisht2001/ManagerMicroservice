package com.manager.microservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.microservice.entity.EmployeeTask;
import com.manager.microservice.repository.EmployeeTaskRepo;
import com.manager.microservice.service.EmployeeTaskRepoService;


@Service
public class EmployeeTaskRepoServiceImpl implements EmployeeTaskRepoService{
	
	@Autowired
	EmployeeTaskRepo emptaskrepo;

	@Override
	public String addTask(EmployeeTask emptask) {
		
		emptaskrepo.save(emptask);
		return "TASK ADDED";
	}

	@Override
	public EmployeeTask getTaskByTaskId(String taskId) {
       
		return emptaskrepo.findById(taskId).get();
	}

	@Override
	public List<EmployeeTask> getTaskByEmpId(String id) {
	List<EmployeeTask> employeeTasks=emptaskrepo.getTaskByEmpId(id);
		return employeeTasks;
	}

	@Override
	public String updateTask(EmployeeTask emptask) {
		
		 emptaskrepo.save(emptask);
		 return "TASK UPDATED";
	}

	@Override
	public List<EmployeeTask> getAllTask() {
		
		return emptaskrepo.findAll();
	}

}
