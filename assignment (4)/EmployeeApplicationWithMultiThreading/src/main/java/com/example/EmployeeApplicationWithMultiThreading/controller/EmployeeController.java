package com.example.EmployeeApplicationWithMultiThreading.controller;

import com.example.EmployeeApplicationWithMultiThreading.model.Employee;
import com.example.EmployeeApplicationWithMultiThreading.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Employee>>> getAllEmployees() {
        return employeeService.getAllEmployees()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Employee>> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .thenApply(employee -> employee.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Employee>> createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee)
                .thenApply(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Employee>> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails)
                .thenApply(employee -> employee.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id)
                .thenApply(aVoid -> ResponseEntity.noContent().build());
    }
}
