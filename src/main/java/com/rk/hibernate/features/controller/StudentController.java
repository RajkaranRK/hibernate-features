package com.rk.hibernate.features.controller;

import com.rk.hibernate.features.one.domain.onetoone.Student;
import com.rk.hibernate.features.request.StudentDO;
import com.rk.hibernate.features.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Object> createStudentRecord(@RequestHeader Map<String,String> headers, @RequestBody StudentDO request){
        Student student = studentService.createStudentRecord(request);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/{roll_number}")
    private ResponseEntity<Student> getStudentDetails(@RequestHeader Map<String,String> headers, @PathVariable("roll_number") Long rollNumber){
        Student student = studentService.studentDetails(rollNumber);
        if(Objects.nonNull(student)){
            return new ResponseEntity<>(student, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
