package com.rk.hibernate.features.service;


import com.rk.hibernate.features.two.domain.Employee;
import com.rk.hibernate.features.two.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> findByName(String name){
        return employeeRepository.findByName(name);
    }

    public Employee findById(Long id){
        return employeeRepository.findById(id).get();
    }


    public void deleteById(Long id) {employeeRepository.deleteById(id);}
}
