package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DormitoryDTO implements Serializable {
    private  int id;

    private String dormitoryNumber;

    private String buildingNumber;

    private int accommodationCapacity;
    private int buildingId;

    private int actualCapacity;
}
