package com.qgsg.dto;

import lombok.Data;

@Data
public class RepairPageQueryDTO {
    private int page;

    private int pageSize;
    private String dormitoryNumber;
}
