package cn.xiangstudy.generalproject.component.mqtt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxiang
 * @date 2025-09-23 16:36
 */
@Configuration
public class MsgHandler {

    @Bean
    public MqttMsgHandler defaultMsgHandler() {
        return new LogMsgHandler();
    }
}
