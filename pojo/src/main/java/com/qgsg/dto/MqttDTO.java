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

    private int id;//学号
    private int zhuangtai;//状态

}
