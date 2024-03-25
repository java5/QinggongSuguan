package com.qgsg.config.mqtt;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qgsg.dto.MqttDTO;
import com.qgsg.service.IOTSensorService;
import com.qgsg.service.impl.MqttServiceImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
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

import java.io.IOException;
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

    @Getter
    private volatile String lastReceivedMessage;

    @Autowired
    private MqttConfiguration mqttProperties;
    @Autowired
    private IOTSensorService sensorService;
    @Autowired
    private MqttServiceImpl mqttServiceimpl;

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }


    @Bean
    public MqttPahoClientFactory mqttInClient() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        // 分割并设置MQTT服务器的URL
        String[] mqttServerUrls = mqttProperties.getUrl().split(",");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(mqttServerUrls);

        // 设置认证信息
        options.setUserName(mqttProperties.getUsername());
        options.setPassword(mqttProperties.getPassword().toCharArray());
        // 设置心跳间隔为2分钟
        options.setKeepAliveInterval(2);

        //接受离线消息
        options.setCleanSession(false);
        factory.setConnectionOptions(options);

        return factory;
    }

    /**
     * 创建一个用于接收MQTT消息的消息生产者。
     * 这个方法配置了MqttPahoMessageDrivenChannelAdapter，它从指定的MQTT主题中消费消息，
     * 然后将这些消息发送到应用内部的消息通道。
     *
     * @return MessageProducer 返回一个消息生产者，它是一个MqttPahoMessageDrivenChannelAdapter实例，
     *         用于从MQTT服务器接收消息。
     */
    @Bean
    public MessageProducer inbound() {
        // 根据配置的接收主题进行分割，生成主题数组
        String[] inboundTopics = mqttProperties.getReceiveTopics().split(",");
        // 创建MqttPahoMessageDrivenChannelAdapter实例，用于从MQTT接收消息
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(mqttProperties.getClientId() + "_inbound",
                mqttInClient(), inboundTopics);
        // 设置完成超时时间
        adapter.setCompletionTimeout(1000 * 5);
        // 设置QoS等级为0，保证消息至少发送一次
        adapter.setQos(0);
        // 设置消息转换器，用于将MQTT消息转换为Spring消息
        adapter.setConverter(new DefaultPahoMessageConverter());
        // 设置输出通道，将接收到的消息发送到这个通道
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * 创建一个消息处理器，用于处理来自MQTT输入通道的消息。
     * 这个处理器会检查接收到的消息的主题，如果主题以"qgsg"开头，
     * 则会记录并打印出消息的内容。
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
                if (recvTopic.startsWith("qgsg")) {
                    String handMessage = "接收到的消息为"+payload;
                    lastReceivedMessage= (String) payload;
                    LOGGER.debug(handMessage);
                    System.out.println(lastReceivedMessage);
                }
                String json = lastReceivedMessage;
                MqttDTO mqtt = JSON.parseObject(json, MqttDTO.class);
                mqttServiceimpl.add(mqtt);
            }

        };
    }
}
