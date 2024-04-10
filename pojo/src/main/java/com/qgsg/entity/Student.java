package com.qgsg.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Student {
    private String id;

    private String number;

    private String name;

    private String sex;

    private int age;

    private String phone;

    private String dormitoryNumber;

    private String buildingNumber;

    private int signStatus;

    private LocalDateTime signTime;

}
