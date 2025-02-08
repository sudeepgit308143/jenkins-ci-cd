package com.sudeep.service;

import com.sudeep.entity.Employee;
import com.sudeep.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public class EmployeeUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> emp = repo.findByUserName(username);
        System.out.println("employee details are:"+emp.get().getUserName());

        return emp.map(EmployeeUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found with name:" + username));
    }
}
