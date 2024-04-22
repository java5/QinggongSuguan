package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BuildingDTO implements Serializable {
    private  int id;
    private String buildingNumber;
    private String buildingName;
    private String buildingDetail;

}
