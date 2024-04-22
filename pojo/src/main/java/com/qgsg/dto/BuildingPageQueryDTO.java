package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BuildingPageQueryDTO implements Serializable {
    private int page;

    private int pageSize;

    private String buildingNumber;

    private String buildingName;
}
