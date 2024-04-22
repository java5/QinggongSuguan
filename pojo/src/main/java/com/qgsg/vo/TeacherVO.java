package com.qgsg.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherVO implements Serializable {
    private Long id;

    private String username;

    private String name;
    private String sex;
    private int age;

    private String phone;

    private String password;

    private String authority;
}
