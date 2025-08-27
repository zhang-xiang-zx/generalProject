package cn.xiangstudy.generalproject.config.security;

import cn.xiangstudy.generalproject.config.request.LogFilter;
import cn.xiangstudy.generalproject.config.request.TokenFilter;
import cn.xiangstudy.generalproject.config.response.JsonAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zhangxiang
 * @date 2025-07-11 10:24
 */
@Configuration
@EnableWebSecurity
@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class, // spring Boot自动配置的基本安全设置
        UserDetailsServiceAutoConfiguration.class // 自动配置内存用户
})
public class SecurityConfig {

    private final JsonAuthenticationEntryPoint entryPoint;


    @Autowired
    public SecurityConfig(JsonAuthenticationEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 禁用csrf
                .formLogin(form -> form.disable()) // 强烈推荐明确禁用表单登录
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 设置无状态会话
                .addFilterBefore(tokenFilter(),UsernamePasswordAuthenticationFilter.class) // 自定义token过滤器
                .addFilterBefore(logFilter(), TokenFilter.class); // 拦截日志


        return http.build();
    }

    // 自定义token验证过滤器
    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter(entryPoint);
    }

    // 记录拦截日志
    @Bean
    public LogFilter logFilter(){
        return new LogFilter();
    }
}
