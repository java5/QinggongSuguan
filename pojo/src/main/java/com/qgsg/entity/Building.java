package com.qgsg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Building {
    private  int id;
    private String buildingNumber;
    private String buildingName;
    private String buildingDetail;
}
