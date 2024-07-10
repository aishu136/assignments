package com.example.EmployeeApplicationWithMultiThreading.service;



import com.example.EmployeeApplicationWithMultiThreading.dao.EmployeeDao;
import com.example.EmployeeApplicationWithMultiThreading.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeDao employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() throws ExecutionException, InterruptedException {
        List<Employee> employees = Arrays.asList(
                new Employee("John Doe", "Engineering", 75000),
                new Employee("Jane Doe", "Marketing", 65000)
        );

        when(employeeRepository.findAll()).thenReturn(employees);

        CompletableFuture<List<Employee>> future = employeeService.getAllEmployees();
        List<Employee> result = future.get();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    public void testGetEmployeeById() throws ExecutionException, InterruptedException {
        Employee employee = new Employee("John Doe", "Engineering", 75000);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        CompletableFuture<Optional<Employee>> future = employeeService.getEmployeeById(1L);
        Optional<Employee> result = future.get();

        assertEquals(true, result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    public void testCreateEmployee() throws ExecutionException, InterruptedException {
        Employee employee = new Employee("John Doe", "Engineering", 75000);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        CompletableFuture<Employee> future = employeeService.createEmployee(employee);
        Employee result = future.get();

        assertEquals("John Doe", result.getName());
    }

    @Test
    public void testUpdateEmployee() throws ExecutionException, InterruptedException {
        Employee employee = new Employee("John Doe", "Engineering", 75000);
        Employee updatedEmployee = new Employee("John Doe", "Engineering", 80000);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        CompletableFuture<Optional<Employee>> future = employeeService.updateEmployee(1L, updatedEmployee);
        Optional<Employee> result = future.get();

        assertEquals(true, result.isPresent());
        assertEquals(80000, result.get().getSalary());
    }

    @Test
    public void testDeleteEmployee() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = employeeService.deleteEmployee(1L);
        future.get();
    }
}
