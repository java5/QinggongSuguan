package com.qgsg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dormitory {
    private int id;

    private String dormitoryNumber;
    private List<Student> students;

    private int accommodationCapacity;

    private int actualCapacity;
    private int buildingId;

    private String buildingNumber;

}
