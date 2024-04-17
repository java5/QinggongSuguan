package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SignDTO implements Serializable {
    private int id;
    private String number;
    private String name;

    private int signStatus;

    private int reason;
    private String specificReason;

    private String dormitoryNumber;

}
