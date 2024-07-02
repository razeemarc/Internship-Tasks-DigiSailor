package com.stech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stech.Entity.Employee;
import com.stech.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
  
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }
  
    public List<Employee> saveEmployees(List<Employee> employees) {
        return repository.saveAll(employees);
    }
  
    public List<Employee> getEmployees() {
        return repository.findAll();
    }
  
    public Employee getEmployeeById(int empId) {
        return repository.findById(empId).orElse(null);
    }
  
      
      public Employee getEmployeeByFirstName(String name) { return
      repository.findByFirstName(name); }
       
  
    public String deleteEmployee(int id) {
        repository.deleteById(id);
        return "Employee removed !! " + id;
    }
  
    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = repository.findById(employee.getEmpId()).orElse(null);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        return repository.save(existingEmployee);
    }
}
