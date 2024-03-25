package com.qgsg.mapper;

import lombok.Setter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MqttMapper {
    @Insert("INSERT INTO mqtt(id, zhuangtai) VALUES(#{id}, #{zhuangtai})")
    void addmqtt(int id, int zhuangtai);
}
