package com.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
* @ClassName: ComputeController 
* @Description: TODO
* @author xuechen
* @date 2017年1月6日 下午3:56:54
*  
*/
@RestController
public class ComputeController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@RequestMapping("/add")
	public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		Integer r = a + b;
		log.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
		return r;
	}
}
