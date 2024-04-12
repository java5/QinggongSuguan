package com.qgsg.config.mqtt;

//import lombok.extern.slf4j.Slf4j;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.nio.charset.StandardCharsets;
//
//@Slf4j
//public class MqttSend {
//    @Autowired
//    private MqttConfiguration mqttProperties;
//    // 发布消息到MQTT服务器
//    public Boolean publish(String payload) {
//        log.info("准备发送：{}",payload);//数据传递无误
//        try {
//            // 创建一个MqttClient实例
////            MqttClient mqttClient = new MqttClient("wss://uaac0158.ala.cn-hangzhou.emqxsl.cn:8084", "mqtt_qgsg_dev1");
//            MqttClient mqttClient = new MqttClient(mqttProperties.getClientId(), mqttProperties.getClientId());
//            // 创建一个MqttConnectOptions实例
//            MqttConnectOptions connOp = new MqttConnectOptions();
//            // 设置cleanSession为true
//            connOp.setCleanSession(true);
//            // 连接到MQTT服务器
//            mqttClient.connect(connOp);
//
//            // 创建一个MqttMessage实例
//            MqttMessage mqttMessage = new MqttMessage(payload.getBytes(StandardCharsets.UTF_8));
//            // 设置Qos为1
//            mqttMessage.setQos(1);
//
//            // 发布消息到MQTT服务器
//            mqttClient.publish("emqx/esp32", mqttMessage);
//
//            // 断开与MQTT服务器的连接
//            mqttClient.disconnect();
//            log.info("发送成功");
//            return true;
//        } catch (MqttException e) {
//            e.printStackTrace();
//            log.info("发送失败");
//            return false;
//
//        }
//    }
//
//
//
//}

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@Slf4j
public class MqttSend {

    public void publish(String payload) {
        log.info("payload:{}",payload);
        String brokerUrl = "wss://uaac0158.ala.cn-hangzhou.emqxsl.cn:8084";
        String clientId = "mqtt_qgsg_dev1";
        String sendTopic = "emqx/esp32";

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
            client.disconnect();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
