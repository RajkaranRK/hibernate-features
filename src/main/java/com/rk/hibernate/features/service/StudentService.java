package com.rk.hibernate.features.service;

import com.rk.hibernate.features.cif.domain.onetoone.Address;
import com.rk.hibernate.features.cif.domain.onetoone.Student;
import com.rk.hibernate.features.cif.repository.StudentWriteRepository;
import com.rk.hibernate.features.request.StudentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentWriteRepository studentWriteRepository;

    @Autowired
    private AddressService addressService;

    @Transactional
    public Student createStudentRecord(StudentDO studentDO){
        Address address = addressService.createAddress(studentDO.getAddress());
        Student student = Student.builder()
                .firstName(studentDO.getFirstName())
                .lastName(studentDO.getLastName())
                .email(studentDO.getEmail())
                .phoneNumber(studentDO.getPhoneNumber())
                .address(address)
                .build();
        return studentWriteRepository.save(student);
    }

    @Transactional
    public Student studentDetails(Long rollNumber){
        Optional<Student> optStudent = studentWriteRepository.findById(rollNumber);
        if(optStudent.isPresent()){
            Student student = optStudent.get();
            student.setAddress(student.getAddress());
            return student;
        }
        return null;
    }
}
