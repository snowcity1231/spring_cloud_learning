package com.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/** 
* @ClassName: Application 
* @Description: 服务注册中心应用
* @author xuechen
* @date 2017年1月6日 上午11:51:34
*  
*/
@EnableEurekaServer
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
}
