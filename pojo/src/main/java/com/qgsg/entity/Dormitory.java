package com.qgsg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dormitory {
    private int id;

    private String dormitoryNumber;

    private int accommodationCapacity;

    private int actualCapacity;

    private String buildingNumber;

}
