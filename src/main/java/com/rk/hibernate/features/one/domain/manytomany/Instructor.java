package com.rk.hibernate.features.one.domain.manytomany;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(name = "instructor_course_mapping",
            joinColumns = @JoinColumn(name = "instructor_ids"),
            inverseJoinColumns = @JoinColumn(name = "course_ids")
    )
    private List<Course> courses;

}
