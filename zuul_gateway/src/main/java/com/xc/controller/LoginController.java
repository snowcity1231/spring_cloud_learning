package com.xc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @ClassName: LoginController 
* @Description: 登录控制器
* @author xuechen
* @date 2017年1月16日 下午4:04:03
*  
*/
@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
