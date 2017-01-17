package com.ai.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
* @ClassName: TestController 
* @Description: 返回from属性
* @author xuechen
* @date 2017年1月13日 下午4:12:46
*  
*/
@RefreshScope
@RestController
public class TestController {

	@Value("${from}")
	private String from;
	
	@RequestMapping("/from")
	public String from() {
		return from;
	}
}
