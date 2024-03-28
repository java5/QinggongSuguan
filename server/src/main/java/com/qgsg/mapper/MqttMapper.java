package com.qgsg.mapper;

import lombok.Setter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface MqttMapper {
    //@Insert("INSERT INTO student(number,sign_status,sign_time) VALUES(#{number}, #{signStatus},#{singTime})")
    void updatemqtt(String number, int signStatus, LocalDateTime signTime);
}
