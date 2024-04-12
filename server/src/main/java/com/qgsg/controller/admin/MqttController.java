//package com.qgsg.controller.admin;
//
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//
//import java.nio.charset.StandardCharsets;
//
//public class MqttController {
//    public  Boolean publish (String payload) {
//        try {
//            MqttClient mqttClient = new MqttClient("tcp://broker.emqx.io:1883","sadf1");
//            MqttConnectOptions connOp = new MqttConnectOptions();
//            connOp.setCleanSession(true);
//            mqttClient.connect(connOp);
//            MqttMessage mqttMessage = new MqttMessage(payload.getBytes(StandardCharsets.UTF_8));
//            mqttMessage.setQos(1);
//
//            mqttClient.publish("emqx/esp32",mqttMessage);
//
//            mqttClient.disconnect();
//            return true;
//        }catch (MqttException e){
//            e.printStackTrace();
//            return false;
//
//        }
//    }
//}
