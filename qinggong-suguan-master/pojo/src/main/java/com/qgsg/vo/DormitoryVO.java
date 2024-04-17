package com.qgsg.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryVO {
    private int id;

    private String dormitoryNumber;

    private int accommodationCapacity;

    private int actualCapacity;

    private String buildingNumber;
}
