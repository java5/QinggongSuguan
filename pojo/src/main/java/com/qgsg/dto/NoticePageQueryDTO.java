package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class NoticePageQueryDTO implements Serializable {
    private int page;

    private int pageSize;

    private String title;


}
