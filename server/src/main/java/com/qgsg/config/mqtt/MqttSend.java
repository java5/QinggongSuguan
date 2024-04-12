//package com.qgsg.config.mqtt;
//
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//
//import java.nio.charset.StandardCharsets;
//
//public class MqttSend {
//    public Boolean publish(String payload) {
//        try {
//            MqttClient mqttClient = new MqttClient("wss://uaac0158.ala.cn-hangzhou.emqxsl.cn:8084", "mqtt_qgsg_dev1");
//            MqttConnectOptions connOp = new MqttConnectOptions();
//            connOp.setCleanSession(true);
//            mqttClient.connect(connOp);
//
//            MqttMessage mqttMessage = new MqttMessage(payload.getBytes(StandardCharsets.UTF_8));
//            mqttMessage.setQos(1);
//
//            mqttClient.publish("emqx/esp32", mqttMessage);
//
//            mqttClient.disconnect();
//            return true;
//        } catch (MqttException e) {
//            e.printStackTrace();
//            return false;
//
//        }
//    }
//
//
//
//}
