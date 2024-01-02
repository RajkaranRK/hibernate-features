package com.rk.hibernate.features;

import com.rk.hibernate.features.service.EmployeeService;
import com.rk.hibernate.features.two.domain.Employee;
import com.rk.hibernate.features.two.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Reference:
//https://www.youtube.com/watch?v=kXhYu939_5s&t=432s
@RunWith(SpringRunner.class)
@SpringBootTest

//@RunWith(MockitoJUnitRunner.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    private Employee employee = null;

    private List<Employee> employees = null;

    @Before
    public void populateData(){
        employee = new Employee(1L,"Raj","raj@gmail.com",27,"16/09/1996");
        employees = new ArrayList<>();
        employees.add(employee);
        employees.add(new Employee(2L,"Rajkaran","raj@gmail.com",22,"16/09/2000"));
        employees.add(new Employee(3L,"Raj","raj@gmail.com",20,"16/09/2000"));
        employees.add(new Employee(4L,"Pintu","raj@gmail.com",18,"16/09/2001"));
        employees.add(new Employee(5L,"Chintu","raj@gmail.com",10,"16/09/2002"));
    }

    @Test
    public void testForFindById(){
        Long employeeId = 1L;
        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.ofNullable(employee));
        Assert.assertEquals(employee.getId() ,employeeService.findById(employeeId).getId());
    }

    @Test
    public void testForFindByName(){
        String name = "Raj";
        Mockito.when(employeeRepository.findByName(name)).thenReturn(employees.stream().filter(emp ->emp.getName().equals(name)).collect(Collectors.toList()));
        Assert.assertEquals(2, employeeService.findByName(name).size());
    }


    @Test
    public void deleteUserTest(){
        Long id = 4L;
        employeeService.deleteById(id);
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(id);
    }


}
