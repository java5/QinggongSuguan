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

    private String id;

    private String number;

    private String name;

    private String signStatus;

    private String signTime;

    private String dormitoryNumber;

}