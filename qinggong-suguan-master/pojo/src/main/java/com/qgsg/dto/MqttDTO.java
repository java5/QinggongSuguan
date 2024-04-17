package com.qgsg.dto;

import com.qgsg.entity.Dormitory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用于存放mqtt接收到的json数据
 */
@Data
@Getter
@Setter
public class MqttDTO implements Serializable {
    //private List<Dormitory> dormitory;//宿舍
//    private int signStatus;//签到状态
   private String fingerPrint;//指纹
//    private String name;//姓名
//    private String dormitoryNumber;//宿舍

}
