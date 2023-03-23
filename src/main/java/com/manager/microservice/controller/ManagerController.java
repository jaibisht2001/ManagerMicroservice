package com.manager.microservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.microservice.entity.EmployeeLogin;
import com.manager.microservice.security.EmployeeLoginDetails;
import com.manager.microservice.security.EmployeeLoginDetailsService;
import com.manager.microservice.entity.EmployeeDetails;
import com.manager.microservice.entity.EmployeeLeave;
import com.manager.microservice.entity.EmployeeLeaveHistory;
import com.manager.microservice.entity.EmployeeTask;
import com.manager.microservice.jwt.JwtUtils;
import com.manager.microservice.repository.EmployeeDetailsRepo;
import com.manager.microservice.repository.EmployeeLeaveHistoryRepo;
import com.manager.microservice.service.impl.EmployeeDetailsRepoServiceImpl;
import com.manager.microservice.service.impl.EmployeeLeaveHistoryRepoServiceImpl;
import com.manager.microservice.service.impl.EmployeeLeaveRepoServiceImpl;
import com.manager.microservice.service.impl.EmployeeTaskRepoServiceImpl;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/manager")
public class ManagerController {
	@Autowired
	EmployeeDetailsRepoServiceImpl empreposerviceimpl;
	@Autowired
	EmployeeDetailsRepo employeeRepo;
	@Autowired
	EmployeeTaskRepoServiceImpl  emptaskreposerviceimpl;
	@Autowired
	EmployeeLeaveRepoServiceImpl employeeLeaveRepoServiceImpl;
	@Autowired
	EmployeeLeaveHistoryRepoServiceImpl empleavehistoryserviceimpl;
	
	@Autowired
	AuthenticationManager authmanager;
	
	@Autowired
	JwtUtils jwtutils;
	
	@Autowired
	EmployeeLoginDetailsService emplogindetailsservice;
	
	
	@PostMapping("/authenticate")
	public String authenticate(@RequestBody EmployeeLogin emplogin)
	{
		String TOKEN=null;
		try {
		Authentication auth=authmanager.authenticate(new UsernamePasswordAuthenticationToken(emplogin.getId(), emplogin.getPassword()));
		 if(auth.isAuthenticated())
		 {
			 EmployeeLoginDetails emplogindetails=(EmployeeLoginDetails) emplogindetailsservice.loadUserByUsername(emplogin.getId()) ;
			  TOKEN =jwtutils.generateJwtToken(emplogindetails);
			 
		 }
		 return TOKEN;
		
		}catch(Exception e) {
			return "AUTHENTICATION FAILED";
		}
		
	}
	
	@GetMapping("/get/{manager}")
	public List<EmployeeDetails> getEmployeeByManager(@PathVariable("manager") String manager) {
		List<EmployeeDetails> empman = empreposerviceimpl.getEmployeeByManager(manager);
		return empman;
	}
	
	@PostMapping("/task/add")
	public String addTask(@RequestBody EmployeeTask emptask)
	{
		return emptaskreposerviceimpl.addTask(emptask);
	
	}
	
	
	

	@GetMapping("/task/get/alltask")
	public List<EmployeeTask> getAllTask()
	{
		return emptaskreposerviceimpl.getAllTask();
	}
	
	@PutMapping("/leave/update/{status}/{id}")
	public String updateLeaveStatus(@PathVariable("status") String status,@PathVariable("id") String id) throws ParseException
	{
		EmployeeLeaveHistory leavehistory=empleavehistoryserviceimpl.getLeaveHistoryById(id);
		SimpleDateFormat dateFormate=new SimpleDateFormat("MM-dd-yyyy HH:mm");
		Date toDate=dateFormate.parse(leavehistory.getToDate().replace("T"," "));
		Date fromDate=dateFormate.parse(leavehistory.getFromDate().replace("T"," "));
		long time_diff=toDate.getTime()-fromDate.getTime();
		long day_diff=((time_diff / (1000*60*60*24))/365)+1;
		if(status.equals("APPROVED")) {
			EmployeeLeave empLeave=employeeLeaveRepoServiceImpl.getEmpLeaveByEmailandType(leavehistory.getEmail(),leavehistory.getLeaveType());
			int remainingLeave=empLeave.getLeaveRemaining();
			if(remainingLeave<day_diff) {
				return "REMAINING LEAVE IS LESS THAN APPLIED LEAVE";
			}
			else {
				remainingLeave=remainingLeave-(int)day_diff;
				employeeLeaveRepoServiceImpl.updateRemainingLeaveByEmailandType(remainingLeave, leavehistory.getEmail(),leavehistory.getLeaveType());
			}
		}
		return empleavehistoryserviceimpl.updateLeaveStatus(status, id);
	}
	
	@GetMapping("/task/get/taskid/{taskId}")
	public EmployeeTask getTaskByTaskId(@PathVariable("taskId") String taskId)
	{
		return emptaskreposerviceimpl.getTaskByTaskId(taskId);
	}
	
	@GetMapping("/task/get/managerid/{id}")
	public List<EmployeeTask> getTaskByEmpId(@PathVariable("id") String id)
	{
		return emptaskreposerviceimpl.getTaskByEmpId(id);
	}
	
	@GetMapping("/leave/getmanager/{manager_id}")
	public List<EmployeeLeaveHistory> getLeaveHistoryByManager(@PathVariable("manager_id") String manager_id)
	{
		List<String> listData=empleavehistoryserviceimpl.getLeaveHistoryByManager(manager_id);
		List<EmployeeLeaveHistory> employeeLeaveHistories=new ArrayList<EmployeeLeaveHistory>();
		for(String s:listData)
		{
			
			String[] splitData=s.split(",");
			employeeLeaveHistories.add(new EmployeeLeaveHistory(Integer.parseInt(splitData[0]), splitData[4], splitData[1], splitData[5], splitData[2], splitData[3], splitData[6], splitData[7]));
			
		}
		return employeeLeaveHistories ;
	}
	@PutMapping("/update/task")
	public String updateTask(@RequestBody EmployeeTask emptask)
	{
		return emptaskreposerviceimpl.updateTask(emptask);
		
	}

}
