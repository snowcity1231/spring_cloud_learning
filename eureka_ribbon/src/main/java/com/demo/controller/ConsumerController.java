package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.ComputeService;

/** 
* @ClassName: ConsumerController 
* @Description: TODO
* @author xuechen
* @date 2017年1月6日 下午2:57:39
*  
*/
@RestController
public class ConsumerController {

//	@Autowired
//	RestTemplate restTemplate;
	@Autowired
	ComputeService computeService;
	
	@RequestMapping("/add")
	public String add(Integer a, Integer b) {
		//直接调用compute-service服务的add方法
//		return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=" + a + "&b=" + b, String.class).getBody();
		//引入了断路器，通过注入的service方法调用
		return computeService.addService(a, b);
	}
}
