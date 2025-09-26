package cn.xiangstudy.generalproject.component.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

/**
 * @author zhangxiang
 * @date 2025-09-23 16:20
 */
@Slf4j
@Component
public class LogMsgHandler implements MqttMsgHandler{
    @Override
    public void handleMessage(String topic, MqttMessage message, String payload) {
        log.info("收到消息 -> 主题: {}, Qos: {}, 保留: {}, 内容: {}", topic, message.getQos(), message.isRetained(), payload);
    }
}
