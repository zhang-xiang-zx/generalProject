package cn.xiangstudy.generalproject.config.security;

import cn.xiangstudy.generalproject.config.request.TokenFilter;
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用csrf
                .csrf(csrf -> csrf.disable())
                // 设置无状态会话
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(tokenFilter(),UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 自定义token验证过滤器
    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }
}
