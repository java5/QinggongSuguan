package com.qgsg.config.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * 实现了对 inboundtopic 中的主题监听，当有消息推送到 inboundtopic 主题上时可以接受
 * MQTT 消费端
 */
@Configuration
@IntegrationComponentScan
@Slf4j
public class MqttInboundConfiguration {
//    private static Logger LOGGER = LoggerFactory.getLogger(MqttInboundConfiguration.class);
//
//    @Getter
//    private volatile String lastReceivedMessage;
//
//    @Autowired
//    private MqttConfiguration mqttProperties;
//    @Autowired
//    private IOTSensorService sensorService;
//    @Autowired
//    private MqttService mqttService;
//
//    @Bean
//    public MessageChannel mqttInputChannel() {
//        return new DirectChannel();
//    }
//
//
//    @Bean
//    public MqttPahoClientFactory mqttInClient() {
//        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
//        String[] mqttServerUrls = mqttProperties.getUrl().split(",");
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setServerURIs(mqttServerUrls);
//        options.setUserName(mqttProperties.getUsername());
//        options.setPassword(mqttProperties.getPassword().toCharArray());
//        options.setKeepAliveInterval(2);
//        //不接受离线消息
//        options.setCleanSession(true);
//        factory.setConnectionOptions(options);
//
//        return factory;
//    }
//
//    /**
//     * @return MessageProducer 返回一个消息生产者，它是一个MqttPahoMessageDrivenChannelAdapter实例，
//     *         用于从MQTT服务器接收消息。
//     */
//    @Bean
//    public MessageProducer inbound() {
//        String[] inboundTopics = mqttProperties.getReceiveTopics().split(",");
//        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(mqttProperties.getClientId() + "_inbound",
//                mqttInClient(), inboundTopics);
//        adapter.setCompletionTimeout(1000 * 5);
//        adapter.setQos(0);
//        adapter.setConverter(new DefaultPahoMessageConverter());
//        adapter.setOutputChannel(mqttInputChannel());
//        return adapter;
//    }
//
//    /**
//     * 创建一个消息处理器，用于处理来自MQTT输入通道的消息。
//     * 这个处理器会检查接收到的消息的主题，如果主题以"？"开头，
//     * 则会处理消息
//     *
//     * @return MessageHandler 返回一个自定义的消息处理器，用于处理MQTT输入通道中的消息。
//     */
//    @Bean
//    @ServiceActivator(inputChannel = "mqttInputChannel")  // 异步处理
//    public MessageHandler handler() {
//        final String[] msg = {null};
//        return new MessageHandler() {
//
//            @Override
//            public void handleMessage(Message<?> message) throws MessagingException {
//                // 提取消息的负载和头信息
//                Object payload = message.getPayload();
//                MessageHeaders messageHeaders = message.getHeaders();
//                UUID packetId = messageHeaders.getId();
//                Object qos = messageHeaders.get(MqttHeaders.QOS);
//                String recvTopic = (String) messageHeaders.get(MqttHeaders.RECEIVED_TOPIC);
//                // 检查主题并处理消息
//                assert recvTopic != null;
//                if (recvTopic.startsWith("chat/room")) {
//                    String handMessage = (String) payload;//接收的
//                    lastReceivedMessage = (String) payload;
//                    LOGGER.debug(handMessage);
//                    System.out.println(lastReceivedMessage);
//                }
//                String json = lastReceivedMessage;
//                log.info("packet:{},qos:{},接收的json:{}", packetId,qos,json);
//                MqttDTO mqttDTO = new MqttDTO();
//
//                JSONObject jsonObject = JSONObject.parseObject(json);
//                log.info("jsonObject对象为:{}", jsonObject);
//
//                // 解析 message 字段
////                String messages = jsonObject.getString("message");
////                System.out.println("Message: " + messages);
////                LocalDate yesterday = LocalDate.now();
//                //开始解析
////                if(Objects.equals(messages, "reset_data")) {
////                    log.info("当天：{}",yesterday);//日期无误
////                }
////                MqttSend mqttSend = new MqttSend();
////                String date = String.valueOf(yesterday);
////                log.info("传入的串{}",date);
////                if (messages!=null){
////                    System.out.println("不为空执行清理程序");
////                    mqttSend.publish(date);
////                }else {
////                    System.out.println("为空不执行");
////                }
//
////                接收学生签到表
//                JSONObject dormitory = jsonObject.getJSONObject("dormitory");
//                log.info("dormitory:{}", dormitory);
//
//                if (dormitory != null) {
//                    log.info("宿舍为{}",dormitory);
//                    String dormitoryNumber = dormitory.getString("dormitoryNumber");
//                    log.info("宿舍号{}",dormitoryNumber);
//                    JSONArray studentsArray = dormitory.getJSONArray("students");
//                    for (int i = 0; i < studentsArray.size(); i++) {
//                        JSONObject student = studentsArray.getJSONObject(i);
//                        String studentId = student.getString("studentId");
//                        String name = student.getString("name");
//                        boolean checkInStatus = student.getBooleanValue("checkInStatus");
////                        boolean leaveStatus = student.getBooleanValue("leaveStatus");
//                        if (checkInStatus /*&& !leaveStatus*/) {
//                            log.info("签到成功的添加到签到表");
//                            mqttDTO.setDormitoryNumber(dormitoryNumber);
//                            mqttDTO.setNumber(studentId);
//                            mqttDTO.setName(name);
//                            mqttDTO.setSignStatus(1);
//                            log.info("准备添加的：宿号{},学号{},姓名{}",dormitoryNumber,studentId,name);
//                            mqttService.update(mqttDTO);
//                        }
//                    }
//
//                }else {
//                    log.info("宿舍为空！！！");
//                }
//            }
//
//        };
//    }
}
