package cn.xiangstudy.generalproject.component.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxiang
 * @date 2025-09-23 16:03
 */
@Slf4j
@Configuration
public class MqttConfig {

    private String broker = "tcp://broker.emqx.io:1883";
    private String clientId = "mqttx_59d35517";
    private String username;
    private String password;
    private int connectionTimeout = 10;
    private int keepAliveInterval = 20;

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{broker});
        options.setCleanSession(true);
        options.setConnectionTimeout(connectionTimeout);
        options.setKeepAliveInterval(keepAliveInterval);
        options.setAutomaticReconnect(true);

        if(username != null && !username.isEmpty()){
            options.setUserName(username);
            options.setPassword(password != null ? password.toCharArray() : new char[0]);
        }

        return options;
    }

    @Bean
    public MqttClient mqttClient(MqttConnectOptions options) {
        try {
            MqttClient client = new MqttClient(broker, clientId);
            client.connect(options);
            log.info("MQTT client connect success: {}", broker);
            return client;
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
}
