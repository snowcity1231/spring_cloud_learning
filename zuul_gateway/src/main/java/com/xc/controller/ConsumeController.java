package com.xc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xc.service.ComputeService;

/** 
* @ClassName: ConsumeController 
* @Description: TODO
* @author xuechen
* @date 2017年1月17日 上午11:25:07
*  
*/
@RestController
public class ConsumeController {

	@Autowired
	private ComputeService computeService;
	
	@RequestMapping("/add")
	public String add(Integer a, Integer b) {
		return computeService.addService(a, b);
	}
}
