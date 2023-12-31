package com.rk.hibernate.features.cif.domain.manytomany;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "time_in_min")
    private Integer timeInMin;

    @ManyToMany(mappedBy = "courses")
    private List<Instructor> instructors;


}
