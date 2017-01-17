package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/** 
* @ClassName: RibbonApplication 
* @Description: Ribbon实现负载均衡应用主类
* @author xuechen
* @date 2017年1月6日 下午2:48:31
*  
*/
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
public class RibbonApplication {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RibbonApplication.class, args);
	}
}
