package com.qgsg.service.impl;

import com.qgsg.dto.MqttDTO;
import com.qgsg.entity.Mqtt;
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

        public void add(){
            //MqttDTO mqtt=new MqttDTO();
            //String username = teacherLoginDTO.getUsername();
            //String password = teacherLoginDTO.getPassword();

            int id = MqttDTO.getId();
            int zhuangtai=MqttDTO.getZhuangtai();

            log.info(""+id);
            log.info(""+zhuangtai);
            mqttMapper.addmqtt(id,zhuangtai);

        }





}
