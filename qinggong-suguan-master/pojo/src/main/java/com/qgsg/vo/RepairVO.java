package com.qgsg.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairVO {
    private  int id;

    private String dormitoryNumber;

    private String buildingNumber;

    private String repairReason;

    private int repairStatus;
    private String content;
    private LocalDateTime repairTime;
    private LocalDateTime completionTime;
}
