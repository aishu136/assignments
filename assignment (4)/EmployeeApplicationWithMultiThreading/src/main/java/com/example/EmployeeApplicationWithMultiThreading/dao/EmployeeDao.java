package com.example.EmployeeApplicationWithMultiThreading.dao;

import com.example.EmployeeApplicationWithMultiThreading.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
}
