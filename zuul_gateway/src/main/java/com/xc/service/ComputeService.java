package com.xc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

/** 
* @ClassName: ComputeService 
* @Description: 计算服务
* @author xuechen
* @date 2017年1月17日 上午11:29:01
*  
*/
@Service
public class ComputeService {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "addServiceFallback")
	public String addService(Integer a, Integer b) {
		return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=" + a + "&b=" + b, String.class).getBody();
	}
	
	public String addServiceFallback(Integer a, Integer b) {
		return "error";
	}
}
