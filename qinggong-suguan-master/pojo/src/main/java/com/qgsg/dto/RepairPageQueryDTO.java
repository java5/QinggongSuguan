package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RepairPageQueryDTO implements Serializable {
    private int page;

    private int pageSize;
    private String repairReason;
    private String dormitoryNumber;
}
