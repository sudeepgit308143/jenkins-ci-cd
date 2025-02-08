package com.sudeep.controller;

import com.sudeep.entity.Employee;
import com.sudeep.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Optum Solutions, your credentials are sent over the email!";
    }

    @PostMapping("/create")
    //@PreAuthorize("hasAuthority('ROLE_HR')")
    public Employee insertNewEmployee(@RequestBody Employee emp) {
        return service.insertNewEmployee(emp);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_HR') or hasAuthority('ROLE_MANAGER')")
    public List<Employee> fetchAllEmployees() {
        return service.fetchAllEmployees();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public Employee fetchEmployeeById(@PathVariable int id) {
        return service.fetchEmployeeById(id);
    }

    @PutMapping("/changerole")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public Employee changeRoleOfEmployee(@RequestBody Employee employee) {


        return service.changeRoleOfEmployee(employee);
    }
}
