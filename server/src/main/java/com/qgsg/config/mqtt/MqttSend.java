package com.qgsg.config.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@Slf4j
public class MqttSend {

    public void publish(String payload) {
        log.info("开始执行上传：payload:{}",payload);
        String brokerUrl = "wss://uaac0158.ala.cn-hangzhou.emqxsl.cn:8084";
        String clientId = "mqtt_qgsg_dev6";
        String sendTopic = "chat/room/8101";

        try {
            MqttClient client = new MqttClient(brokerUrl, clientId);
            MqttConnectOptions options = new MqttConnectOptions();
             options.setUserName("emqx");
             options.setPassword("public".toCharArray());

            client.connect(options);

            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setQos(0); // 设置QoS级别

            client.publish(sendTopic, message);

            System.out.println("Message published to topic: " + sendTopic);

            // 可选，保持连接并在发布后断开
//            client.disconnect();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
