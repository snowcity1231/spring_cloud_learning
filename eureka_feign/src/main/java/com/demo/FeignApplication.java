package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/** 
* @ClassName: FeignApplication 
* @Description: 通过Feign以接口和注解配置的方式，实现对compute_service服务的绑定，并实现负载均衡
* @author xuechen
* @date 2017年1月6日 下午5:08:43
*  
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignApplication.class, args);
	}
}
