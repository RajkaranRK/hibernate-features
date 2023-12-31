package com.rk.hibernate.features.one.domain.onetoone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
@Builder
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="zip_code")
    private String zipCode;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="house_number")
    private String houseNumber;

    // this join column is used to give the custom column name of foreign key in Address table related to student table.
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
