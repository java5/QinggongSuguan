package com.qgsg.dto;

import lombok.Data;

@Data
public class DormitoryDTO {
    private  int id;

    private String dormitoryNumber;

    private String buildingNumber;

    private int accommodationCapacity;

    private int buildingId;

    private int actualCapacity;
}
