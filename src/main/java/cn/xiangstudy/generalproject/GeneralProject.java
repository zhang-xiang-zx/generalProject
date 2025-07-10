package cn.xiangstudy.generalproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangxiang
 * @date 2025-07-10 16:11
 */
@SpringBootApplication
@MapperScan("cn.xiangstudy.generalproject.mapper")
public class GeneralProject {

    public static void main(String[] args) {
        SpringApplication.run(GeneralProject.class, args);
    }
}
