package com.qgsg.service;

import com.qgsg.dto.MqttDTO;
import com.qgsg.dto.StudentDTO;

public interface MqttService {
    /**
     * 更新数据
     * @param mqttDTO
     */
    void update(MqttDTO mqttDTO);
}
