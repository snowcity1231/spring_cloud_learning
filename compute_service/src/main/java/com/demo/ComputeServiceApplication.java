package com.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/** 
* @ClassName: ComputeServiceApplication 
* @Description: TODO
* @author xuechen
* @date 2017年1月6日 下午3:56:39
*  
*/
@EnableDiscoveryClient
@SpringBootApplication
public class ComputeServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ComputeServiceApplication.class).web(true).run(args);
	}
}
