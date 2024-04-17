package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class RepairDTO implements Serializable {
    private  int id;

    private int repairStatus;
    private String dormitoryNumber;
    private String content;
    private String buildingNumber;

    private String repairReason;

}
