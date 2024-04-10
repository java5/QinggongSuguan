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
public class StudentVO implements Serializable {

    private int id;

    private String number;

    private String name;

    private String sex;

    private int age;

    private String phone;

    private String dormitoryNumber;

    private String buildingNumber;

    private String signStatus;

}
