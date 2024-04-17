package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeacherDTO implements Serializable {
    private Long id;

    private String username;

    private String name;
    private String sex;
    private int age;

    private String phone;

    private String password;

    private String authority;

}
