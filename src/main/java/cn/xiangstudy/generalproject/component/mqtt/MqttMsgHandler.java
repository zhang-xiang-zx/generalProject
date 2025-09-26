package cn.xiangstudy.generalproject.component.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 消息处理接口
 * @author zhangxiang
 * @date 2025/9/23 16:18
 */
@FunctionalInterface
public interface MqttMsgHandler {

    /**
     * 处理接收MQTT消息
     * @author zhangxiang
     * @date 2025/9/23 15:08
     * @param topic     主题
     * @param message   原始MQTT消息 (包含QOS, 保留标志等信息)
     * @param payload   消息内容字符串
     */
    void handleMessage(String topic, MqttMessage message, String payload);
}
