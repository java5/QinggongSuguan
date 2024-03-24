package com.qgsg.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannelIOT")
public interface MqttGateWayService {
    void sendMessageToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

}
