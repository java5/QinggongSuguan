package com.qgsg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Repair implements Serializable {

    /**
     * 报修状态 1报修中 2已完成
     */
    public static final Integer REPAIRING = 1;
    public static final Integer COMPLETED = 2;

    private  int id;

    private String dormitoryNumber;

    private String buildingNumber;

    private String repairReason;

    private int repairStatus;
    private String content;
    private LocalDateTime repairTime;
    private LocalDateTime completionTime;
}
