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
public class Fangke implements Serializable {
    private String name;

    private String sex;

    private String phone;

    private String dizhi;

    private String beizhu;

    private LocalDateTime localDateTime;

}
