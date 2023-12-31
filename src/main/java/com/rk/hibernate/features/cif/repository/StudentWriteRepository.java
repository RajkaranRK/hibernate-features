package com.rk.hibernate.features.cif.repository;


import com.rk.hibernate.features.cif.domain.onetoone.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentWriteRepository extends JpaRepository<Student,Long> {

}
