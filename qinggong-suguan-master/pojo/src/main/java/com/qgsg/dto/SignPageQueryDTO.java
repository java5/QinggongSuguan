package com.qgsg.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SignPageQueryDTO  implements Serializable {
    private int page;

    private int pageSize;
    private LocalDateTime signTime;
    private String number;
    private String name;
}
