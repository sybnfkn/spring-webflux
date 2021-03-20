package com.zhangyan.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Spring Boot 应用启动类
 *
 * Created by bysocket on 09/29/2017.
 */
// Spring Boot 应用的标识
@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
@EnableWebFlux
//@RibbonClient
public class WebFluxApplication {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(WebFluxApplication.class,args);
    }
}
