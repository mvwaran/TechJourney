package com.mvwaran.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FromDatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException {
        var employeeOptional = employeeRepository.findById(Integer.valueOf(empId));
        if (employeeOptional.isPresent()) {
            var employee = employeeOptional.get();
            return User.builder()
                    .username(String.valueOf(employee.getId()))
                    .password(employee.getPassword())
                    .roles(employee.getRole())
                    .build();
        }
        throw new UsernameNotFoundException("Emp ID " + empId + " not found");
    }
}
