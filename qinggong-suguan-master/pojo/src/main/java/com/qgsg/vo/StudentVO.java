package com.qgsg.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    private String fingerPrint;

    private String signStatus;

    private LocalDateTime signTime;

}
