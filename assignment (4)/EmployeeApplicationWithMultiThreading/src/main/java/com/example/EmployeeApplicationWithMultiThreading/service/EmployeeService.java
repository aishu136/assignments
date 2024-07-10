package com.example.EmployeeApplicationWithMultiThreading.service;

import com.example.EmployeeApplicationWithMultiThreading.dao.EmployeeDao;
import com.example.EmployeeApplicationWithMultiThreading.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeRepository;

    @Async
    public CompletableFuture<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return CompletableFuture.completedFuture(employees);
    }

    @Async
    public CompletableFuture<Optional<Employee>> getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return CompletableFuture.completedFuture(employee);
    }

    @Async
    public CompletableFuture<Employee> createEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return CompletableFuture.completedFuture(savedEmployee);
    }

    @Async
    public CompletableFuture<Optional<Employee>> updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(employeeDetails.getName());
            employee.setDepartment(employeeDetails.getDepartment());
            employee.setSalary(employeeDetails.getSalary());
            Employee updatedEmployee = employeeRepository.save(employee);
            return CompletableFuture.completedFuture(Optional.of(updatedEmployee));
        }
        return CompletableFuture.completedFuture(Optional.empty());
    }

    @Async
    public CompletableFuture<Void> deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }
}
