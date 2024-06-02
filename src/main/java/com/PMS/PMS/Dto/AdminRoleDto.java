package com.PMS.PMS.Dto;

import lombok.Data;


@Data
public class AdminRoleDto {
    private Long id;
    private int number;
    private String name;
    private String username;
    private String email;
    private String password;
    private int phone;
    private String img;
    private String roles;

}
