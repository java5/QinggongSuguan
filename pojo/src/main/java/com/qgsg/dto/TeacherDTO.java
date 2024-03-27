package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeacherDTO implements Serializable {

    private String username;

    private String name;

    private String phone;

    private String password;

    private String authority;

}
