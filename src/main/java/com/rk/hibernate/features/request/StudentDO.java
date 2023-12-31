package com.rk.hibernate.features.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Long rollNumber;

    private AddressDO address;

}
