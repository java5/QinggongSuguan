package com.qgsg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    int id;

    String number;

    String name;

    String sex;

    int age;

    String phone;

    String dormitory_number;

    String building_number;

    String finger_print;

    int sign_status;

    LocalDateTime sign_time;

}
