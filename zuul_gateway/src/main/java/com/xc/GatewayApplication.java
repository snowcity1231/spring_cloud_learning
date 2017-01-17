package com.xc;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.xc.filter.AccessFilter;

/** 
* @ClassName: GatewayApplication 
* @Description: 整合zuul服务网关应用主类
* @author xuechen
* @date 2017年1月16日 上午11:39:26
*  
*/
@EnableZuulProxy
@EnableDiscoveryClient
@SpringCloudApplication			//SpringCloudApplication注解，整合@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
@EnableCircuitBreaker		// 开启断路器
public class GatewayApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(GatewayApplication.class).web(true).run(args);
	}
	
	/**
	 * 实例化过滤器，使过滤器生效
	 * @return
	 */
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
	
	/**
	 * 实例化RestTemplate开启负载均衡
	 * @return
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
