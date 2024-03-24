package com.qgsg.service;

import com.qgsg.config.mqtt.MqttInboundConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttMessageRetrieverService {

    private final MqttInboundConfiguration mqttInboundConfig;

    @Autowired
    public MqttMessageRetrieverService(MqttInboundConfiguration mqttInboundConfig) {
        this.mqttInboundConfig = mqttInboundConfig;
    }

    public void retrieveAndOutputLastMessage() {
        System.out.println("serves运行中");
        String lastMessage = mqttInboundConfig.getLastReceivedMessage();
        System.out.println("MQTT message: " + lastMessage);
    }
}
