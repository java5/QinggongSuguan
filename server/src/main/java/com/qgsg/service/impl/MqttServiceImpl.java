package com.qgsg.service.impl;

import com.qgsg.dto.MqttDTO;
import com.qgsg.entity.Mqtt;
import com.qgsg.entity.Student;
import com.qgsg.mapper.MqttMapper;
import com.qgsg.mapper.StudentMapper;
import com.qgsg.service.MqttService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
    @Service
    public class MqttServiceImpl implements MqttService {

        @Autowired
        private MqttMapper mqttMapper;
        @Autowired
        private StudentMapper studentMapper;

        public void update(MqttDTO mqttDTO){
            Mqtt mqtt = new Mqtt();
            BeanUtils.copyProperties(mqttDTO,mqtt);
            mqtt.setSignTime(LocalDateTime.now());
            String number = mqtt.getNumber();
            int signStatus = mqtt.getSignStatus();
            LocalDateTime signTime= mqtt.getSignTime();
            log.info("mpl:number{},status{},time{}",number,signStatus,signTime);
            Student student=studentMapper.getByNumber(number);
            log.info("{}",student);
            String name = student.getName();
            String dormitoryNumber = student.getDormitoryNumber();
            mqtt.setName(name);
            mqtt.setDormitoryNumber(dormitoryNumber);
            mqttMapper.updatemqtt(number,signStatus,signTime);
            mqttMapper.insert(mqtt);
        }
}
