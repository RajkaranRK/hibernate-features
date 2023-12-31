package com.rk.hibernate.features.one.repository;


import com.rk.hibernate.features.one.domain.onetoone.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentWriteRepository extends JpaRepository<Student,Long> {

}
