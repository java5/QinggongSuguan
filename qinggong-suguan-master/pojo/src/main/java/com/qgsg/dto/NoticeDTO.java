package com.qgsg.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class NoticeDTO implements Serializable {
    private int id;

    private String title;

    private String author;

    private String content;

    private LocalDateTime releaseTime;
}
