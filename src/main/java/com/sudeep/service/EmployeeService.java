package com.sudeep.service;

import com.sudeep.entity.Employee;
import com.sudeep.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private static final String DEFAULT_ROLE = "ROLE_EMPLOYEE";
    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Employee insertNewEmployee(Employee emp) {
        emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        emp.setRoles(DEFAULT_ROLE);
        return repo.save(emp);
    }

    public List<Employee> fetchAllEmployees() {
        return repo.findAll();
    }

    public Employee fetchEmployeeById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not found with given id: " + id));
    }


    public Employee changeRoleOfEmployee(Employee employee) {
        Employee existingemployee = fetchEmployeeById(employee.getId());
        existingemployee.setRoles(employee.getRoles());
        return repo.save(existingemployee);
    }
}
