package com.zhangyan.server;

import com.zhangyan.server.db.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

/**
 * 服务A的启动类
 * @author zhonghuashishan
 *
 */
@SpringBootApplication
@EnableEurekaClient
@Import(DataSourceConfig.class)
@ServletComponentScan
public class DataServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataServerApplication.class, args);
	}

}