package com.qgsg.config.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;

@Slf4j
public class MqttSend {
    // 发布消息到MQTT服务器
    public Boolean publish(String payload) {
        log.info("准备发送：{}",payload);//数据传递无误
        try {
            // 创建一个MqttClient实例
            MqttClient mqttClient = new MqttClient("wss://uaac0158.ala.cn-hangzhou.emqxsl.cn:8084", "mqtt_qgsg_dev1");
            // 创建一个MqttConnectOptions实例
            MqttConnectOptions connOp = new MqttConnectOptions();
            // 设置cleanSession为true
            connOp.setCleanSession(true);
            // 连接到MQTT服务器
            mqttClient.connect(connOp);

            // 创建一个MqttMessage实例
            MqttMessage mqttMessage = new MqttMessage(payload.getBytes(StandardCharsets.UTF_8));
            // 设置Qos为1
            mqttMessage.setQos(1);

            // 发布消息到MQTT服务器
            mqttClient.publish("emqx/esp32", mqttMessage);

            // 断开与MQTT服务器的连接
            mqttClient.disconnect();
            return true;
        } catch (MqttException e) {
            e.printStackTrace();
            return false;

        }
    }



}
