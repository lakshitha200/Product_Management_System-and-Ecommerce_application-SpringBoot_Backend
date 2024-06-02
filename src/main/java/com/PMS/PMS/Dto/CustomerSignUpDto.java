package com.PMS.PMS.Dto;

import lombok.Data;

@Data
public class CustomerSignUpDto {
    private Long cId;
    private Long cusNumber;
    private String name;
    private String username;
    private String location;
    private String email;
    private String password;
    private int phone;
    private String img;
    private String role = "ROLE_CUSTOMER";


}
