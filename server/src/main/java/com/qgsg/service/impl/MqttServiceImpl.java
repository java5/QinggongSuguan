package com.qgsg.service.impl;

import com.qgsg.dto.MqttDTO;
import com.qgsg.mapper.MqttMapper;
import com.qgsg.service.MqttService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


    @Slf4j
    @Service
    public class MqttServiceImpl implements MqttService {

        @Autowired
        private MqttMapper mqttMapper;

        public void add(MqttDTO mqtt){
            int id = mqtt.getId();
            int zhuangtai=mqtt.getZhuangtai();
            mqttMapper.addmqtt(id,zhuangtai);
        }
}
