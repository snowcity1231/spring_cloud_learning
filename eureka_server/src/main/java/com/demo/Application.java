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

	/**
	 * 通过在启动时设置spring.profiles.active属性分别启动peer1、peer2
	 * @param args
	 */
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
}
