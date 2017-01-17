package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.ComputeClient;

/** 
* @ClassName: ConsumerController 
* @Description: TODO
* @author xuechen
* @date 2017年1月6日 下午5:19:54
*  
*/
@RestController
public class ConsumerController {
	
	@Autowired
	ComputeClient computeClient;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public Integer add(Integer a, Integer b) {
		return computeClient.add(a, b);
	}

}
