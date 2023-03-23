package com.manager.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.microservice.entity.EmployeeLogin;

public interface EmployeeLoginRepo extends JpaRepository<EmployeeLogin,String> {

}
