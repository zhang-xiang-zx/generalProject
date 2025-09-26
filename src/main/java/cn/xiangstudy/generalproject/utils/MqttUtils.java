package cn.xiangstudy.generalproject.utils;

import cn.xiangstudy.generalproject.component.mqtt.MqttMsgHandler;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxiang
 * @date 2025-09-23 16:23
 */
@Slf4j
@Component
public class MqttUtils {
    private final MqttClient mqttClient;

    private final MqttMsgHandler defaultMsgHandler;

    private final Map<String, MqttMsgHandler> topicHandlerMap = new HashMap<>();

    public MqttUtils(MqttClient mqttClient, MqttMsgHandler defaultMsgHandler) {

        this.mqttClient = mqttClient;
        this.defaultMsgHandler = defaultMsgHandler;
    }

    @PostConstruct
    public void init(){
        log.info("mqtt utils init...");
        mqttClient.setCallback(new MqttCallback() {

            @Override
            public void connectionLost(Throwable throwable) {
                log.info("connectionLost -> {}", throwable.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                log.info("messageArrived -> Topic: {}, Qos: {},", topic, mqttMessage.getQos());

                MqttMsgHandler msgHandler = topicHandlerMap.getOrDefault(topic, defaultMsgHandler);

                try {
                    msgHandler.handleMessage(topic, mqttMessage, new String(mqttMessage.getPayload()));
                }catch (Exception e){
                    log.info("消息处理出错");
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                log.info("deliveryComplete -> {}", iMqttDeliveryToken.isComplete());
            }
        });
    }

    /**
     * mqtt是否连接
     * @author zhangxiang
     * @date 2025/9/23 16:48
     * @return boolean
     */
    public boolean isConnected(){
        log.info("mqtt isConnected: {}", mqttClient.isConnected());
        return mqttClient.isConnected();
    }

    /**
     * 订阅指定主题, 指定qos, 指定消息处理
     * @author zhangxiang
     * @date 2025/9/23 16:51
     * @param topic
     * @param qos
     * @param msgHandler
     */
    public void subscribe(String topic, int qos, MqttMsgHandler msgHandler){
        if(!isConnected()){
            throw new RuntimeException("mqtt client is not connected");
        }

        try {
            mqttClient.subscribe(topic, qos);
            topicHandlerMap.put(topic, msgHandler);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 订阅指定主题, 默认qos:1, 默认消息处理
     * @author zhangxiang
     * @date 2025/9/23 16:55
     * @param topic
     */
    public void subscribe(String topic){
        subscribe(topic, 1, defaultMsgHandler);
    }

    /**
     * 订阅指定主题, 指定qos, 默认消息处理
     * @author zhangxiang
     * @date 2025/9/23 16:56
     * @param topic
     * @param qos
     */
    public void subscribe(String topic, int qos){
        subscribe(topic, qos, defaultMsgHandler);
    }

    /**
     * 指定主题发消息, 指定qos
     * @author zhangxiang
     * @date 2025/9/23 17:01
     * @param topic
     * @param msg
     * @param qos
     */
    public void publish(String topic, String msg, int qos){
        if(!isConnected()){
            throw new RuntimeException("mqtt client is not connected");
        }

        MqttMessage mqttMessage = new MqttMessage(msg.getBytes());
        mqttMessage.setQos(qos);

        try {
            mqttClient.publish(topic, mqttMessage);
        } catch (MqttException e) {
            log.info("publish msg fail");
            throw new RuntimeException(e);
        }
    }

    /**
     * 指定主题发消息, 默认qos:1
     * @author zhangxiang
     * @date 2025/9/23 17:15
     * @param topic
     * @param msg
     */
    public void publish(String topic, String msg){
        publish(topic, msg, 1);
    }

    /**
     * 取消订阅
     * @author zhangxiang
     * @date 2025/9/25 11:57
     * @param topic
     */
    public void unsubscribe(String topic){
        if(!isConnected()){
            throw new RuntimeException("mqtt client is not connected");
        }

        try {
            mqttClient.unsubscribe(topic);
            topicHandlerMap.remove(topic);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
}
