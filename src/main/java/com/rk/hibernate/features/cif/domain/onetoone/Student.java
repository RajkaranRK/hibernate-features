package com.rk.hibernate.features.cif.domain.onetoone;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
@Builder
public class Student {

    @Id
    @Column(name = "roll_number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rollNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    // mappedBy is used to refer here that this table won't hold any column related to address table
    // it should be in address table and look for the field i.e. student in Address class;
    @OneToOne(mappedBy = "student")  //"student" this is the field name which is present in the Address class related to Student
    private Address address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

}
