package com.qgsg.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用于存放mqtt接收到的json数据
 */
@Data
@Getter
@Setter
public class MqttDTO implements Serializable {
    private String number;//学号1
    private int signStatus;//签到状态3 1true 2feast

    private String name;//姓名2


    private String dormitoryNumber;//宿舍4

}
