package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DormitoryPageQueryDTO implements Serializable {
    private int page;

    private int pageSize;

    private String dormitoryNumber;

    private String buildingNumber;
}
