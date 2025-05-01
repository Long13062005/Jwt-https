package com.hunglevi.jwthttpscookie.security.reqres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq {
    private String username;
    private String password;
    private String role;
    private String typeCustomer;
    private String name;
    private String dob;
    private String idCard;
    private int gender;
    private Double salary;
    private String phoneNumber;
    private String email;
    private String address;

    private boolean deleted = false;
}
