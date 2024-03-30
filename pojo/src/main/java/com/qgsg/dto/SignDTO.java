package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SignDTO implements Serializable {

    private String number;

    private String name;

    private int page;

    private int pageSize;

    private String signStatus;

    private String dormitoryNumber;

}
