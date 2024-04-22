package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FangkeDTO implements Serializable {
    private String name;

    private String sex;

    private String phone;

    private String dizhi;

    private String beizhu;
}
