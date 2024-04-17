package com.qgsg.config.mqtt;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qgsg.context.BaseContext;
import com.qgsg.dto.MqttDTO;
import com.qgsg.dto.MqttDateDTO;
import com.qgsg.dto.StudentDTO;
import com.qgsg.result.Result;
import com.qgsg.service.IOTSensorService;
import com.qgsg.service.MqttService;
import com.qgsg.service.StudentService;
import com.qgsg.service.impl.MqttServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

/**
 * 实现了对 inboundtopic 中的主题监听，当有消息推送到 inboundtopic 主题上时可以接受
 * MQTT 消费端
 */
@Configuration
@IntegrationComponentScan
@Slf4j

public class MqttInboundConfiguration {
    private static Logger LOGGER = LoggerFactory.getLogger(MqttInboundConfiguration.class);

    private  static int sendLog=0;
    @Getter
    private volatile String lastReceivedMessage;

    @Autowired
    private MqttConfiguration mqttProperties;
    @Autowired
    private IOTSensorService sensorService;
    @Autowired
    private MqttService mqttService;
    @Autowired
    private StudentService studentService;
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }


    @Bean
    public MqttPahoClientFactory mqttInClient() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        String[] mqttServerUrls = mqttProperties.getUrl().split(",");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(mqttServerUrls);
        options.setUserName(mqttProperties.getUsername());
        options.setPassword(mqttProperties.getPassword().toCharArray());
        options.setKeepAliveInterval(2);
        //接受离线消息
        options.setCleanSession(false);
        factory.setConnectionOptions(options);

        return factory;
    }

    /**
     * @return MessageProducer 返回一个消息生产者，它是一个MqttPahoMessageDrivenChannelAdapter实例，
     *         用于从MQTT服务器接收消息。
     */
    @Bean
    public MessageProducer inbound() {
        String[] inboundTopics = mqttProperties.getReceiveTopics().split(",");
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(mqttProperties.getClientId() + "_inbound",
                mqttInClient(), inboundTopics);
        adapter.setCompletionTimeout(1000 * 5);
        adapter.setQos(0);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * 创建一个消息处理器，用于处理来自MQTT输入通道的消息。
     * 这个处理器会检查接收到的消息的主题，如果主题以"qgsg"开头，
     * 则会处理消息
     *
     * @return MessageHandler 返回一个自定义的消息处理器，用于处理MQTT输入通道中的消息。
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")  // 异步处理
    public MessageHandler handler() {
        final String[] msg = {null};
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                // 提取消息的负载和头信息
                Object payload = message.getPayload();
                MessageHeaders messageHeaders = message.getHeaders();
                UUID packetId = messageHeaders.getId();
                Object qos = messageHeaders.get(MqttHeaders.QOS);
                String recvTopic = (String) messageHeaders.get(MqttHeaders.RECEIVED_TOPIC);
                // 检查主题并处理消息
                assert recvTopic != null;

                if (recvTopic.startsWith("emqx/qgxy")) {
                    String handMessage = "接收到的消息为" + payload;
                    lastReceivedMessage = (String) payload;
                    LOGGER.debug(handMessage);
                    System.out.println(lastReceivedMessage);

                }

                String json = lastReceivedMessage;
//                MqttDTO mqttDTO = JSON.parseObject(json, MqttDTO.class);
                MqttDTO mqttDTO = new MqttDTO();

                JSONObject jsonObject = JSONObject.parseObject(json);
                log.info("jsonObject对象为:{}", jsonObject);
                String jsonString = jsonObject.toJSONString();



            }
//            @Scheduled(cron = "0 23 * * * *")
//            @Scheduled(fixedDelay = 5000)
            public  void sendResetTime(){
                    log.info("准备发送");
                    try {
                        // 创建一个MqttClient实例
                        MqttClient mqttClient = new MqttClient(mqttProperties.getUrl(), mqttProperties.getClientId());
                        // 创建一个MqttConnectOptions实例
                        MqttConnectOptions connOp = new MqttConnectOptions();
                        // 设置cleanSession为true
                        connOp.setCleanSession(true);
                        // 连接到MQTT服务器
                        mqttClient.connect(connOp);

                        // 创建一个MqttMessage实例
                        JSONObject payLoad = new JSONObject();
//                        payLoad.put("message", "reset_data");
                        payLoad.put("today_date", LocalDate.now());
//                        String message1 = payLoad.toJSONString();
                        String resetTime = LocalDate.now().minusDays(1).toString();
                        MqttMessage mqttMessage = new MqttMessage(resetTime.getBytes(StandardCharsets.UTF_8));
                        // 设置Qos为1
                        mqttMessage.setQos(1);

                        // 发布消息到MQTT服务器
                        mqttClient.publish(mqttProperties.getSendTopics(), mqttMessage);

                        // 断开与MQTT服务器的连接
                        mqttClient.disconnect();
                    } catch (MqttException e) {
                        e.printStackTrace();

                    }
                }



        };
    }


}
