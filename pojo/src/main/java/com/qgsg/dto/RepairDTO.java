package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class RepairDTO implements Serializable {
    private  int id;

    private String dormitoryNumber;

    private String buildingNumber;

    private String repairReason;

}
