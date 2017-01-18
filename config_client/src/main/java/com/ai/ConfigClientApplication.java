package com.ai;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/** 
* @ClassName: Application 
* @Description: 配置中心客户端主类，启动后可以在该应用中获取配置中心的配置信息
* @author xuechen
* @date 2017年1月13日 下午4:07:56
*  
*/
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigClientApplication.class).web(true).run(args);
	}
}
