package com.qgsg.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class StudentPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    private String number;

    private String name;



}
