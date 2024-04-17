package com.qgsg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sign implements Serializable {

    private int id;

    private String number;

    private String name;

    private int signStatus;

    private int reason;
    private String specificReason;


    private LocalDateTime signTime;

    private String dormitoryNumber;

}
