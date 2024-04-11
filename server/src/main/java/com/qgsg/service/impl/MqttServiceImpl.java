package com.qgsg.service.impl;

import com.qgsg.dto.MqttDTO;
import com.qgsg.entity.Mqtt;
import com.qgsg.entity.Sign;
import com.qgsg.mapper.MqttMapper;
import com.qgsg.mapper.SignMapper;
import com.qgsg.mapper.StudentMapper;
import com.qgsg.service.MqttService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;


@Slf4j
    @Service
    public class MqttServiceImpl implements MqttService {

        @Autowired
        private MqttMapper mqttMapper;
        @Autowired
        private StudentMapper studentMapper;
        @Autowired
        private SignMapper signMapper;

        public void update(MqttDTO mqttDTO){
            Mqtt mqtt = new Mqtt();
            BeanUtils.copyProperties(mqttDTO,mqtt);
            mqtt.setSignTime(LocalDateTime.now());
            String number = mqtt.getNumber();
            int signStatus = mqtt.getSignStatus();
            LocalDateTime signTime= mqtt.getSignTime();
            log.info("mpl:number{},status{},time{}",number,signStatus,signTime);

//            Student student=studentMapper.getByNumber(number);
            List<Sign> sign = signMapper.getByNum(number);
            log.info("学生签到表信息{}",sign);

            int i=1;
            LocalDateTime date = null;
            for (Sign s : sign) {
                LocalDateTime signTimes = s.getSignTime();
                date = signTimes;
                log.info("{},更新签到时间：{},date:{}",i++, signTimes,date);
            }

            LocalDate dateOnly = date.toLocalDate();
            log.info("签到表日期{}",dateOnly);
            LocalDate currentDate = LocalDate.now();
            log.info("当前时间{}",currentDate);



            LocalDateTime now = LocalDateTime.now();

            // 设置判断范围的起始和结束时间
            LocalTime startTime = LocalTime.of(8, 0);
            LocalTime endTime = LocalTime.of(22, 0);

            // 检查当前时间的小时和分钟是否在指定范围内
            boolean isWithinRange = now.toLocalTime().isAfter(startTime) && now.toLocalTime().isBefore(endTime);

            if (isWithinRange) {
                if(Objects.equals(dateOnly, currentDate)) {
                }else {
                    mqttMapper.updatemqtt(number,signStatus,signTime);
                    mqttMapper.insert(mqtt);
                }
            } else {
                System.out.println("不在范围内，禁止签到");
            }

//            String name = student.getName();
//            String dormitoryNumber = student.getDormitoryNumber();
//            if (name != null) mqtt.setName(name);
//            if(dormitoryNumber!=null) mqtt.setDormitoryNumber(dormitoryNumber);

        }
}
