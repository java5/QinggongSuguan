package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignDTO implements Serializable {

    private String number;

    private String name;

    private int page;

    private int pageSize;

    private int signStatus;

    private String dormitoryNumber;

}
