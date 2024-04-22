package com.qgsg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mqtt {
    private int id;
    private String number;//学号
    private int signStatus;//签到状态
    private LocalDateTime signTime;//签到时间

    private String name;//姓名
    private String dormitoryNumber;//宿舍
}
