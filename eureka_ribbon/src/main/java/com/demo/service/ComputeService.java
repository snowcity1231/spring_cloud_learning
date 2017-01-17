package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/** 
* @ClassName: ComputeService 
* @Description: TODO
* @author xuechen
* @date 2017年1月9日 上午10:07:32
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
	
	/**
	 * 失败回调方法参数要与原方法一致
	 * @param a
	 * @param b
	 * @return
	 */
	public String addServiceFallback(Integer a, Integer b) {
		return "error";
	}
}
