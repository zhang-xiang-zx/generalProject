package cn.xiangstudy.generalproject.config.mybatis;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxiang
 * @date 2025-07-25 15:04
 */
@Configuration
public class MyBatisConfig {

//    @Bean
//    public PaginationInterceptor myPageInterceptor() {
//        return new PaginationInterceptor();
//    }

    @Bean
    public MyPageInterceptor myPageInterceptor() {
        return new MyPageInterceptor();
    }

    /**
     * ConfigurationCustomizer 是 MyBatis-Spring-Boot-Starter提供的扩展点
     *
     * @author zhangxiang
     * @date 2025/9/2 14:53
     * @param paginationInterceptor
     * @return org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer
     */
//    @Bean
//    public ConfigurationCustomizer myConfigurationCustomizer(PaginationInterceptor paginationInterceptor) {
//        // 它会在 MyBatis Configuration 初始化时调用你这里的 lambda
//        return (org.apache.ibatis.session.Configuration configuration) -> configuration.addInterceptor(paginationInterceptor);
//        // 上面这一行就是给 MyBatis Configuration 加上你的自定义拦截器
//    }

    @Bean
    public ConfigurationCustomizer myConfigurationCustomizer(MyPageInterceptor paginationInterceptor) {
        // 它会在 MyBatis Configuration 初始化时调用你这里的 lambda
        return (org.apache.ibatis.session.Configuration configuration) -> configuration.addInterceptor(paginationInterceptor);
        // 上面这一行就是给 MyBatis Configuration 加上你的自定义拦截器
    }
}
