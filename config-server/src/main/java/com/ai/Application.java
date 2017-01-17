package com.ai;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/** 
* @ClassName: Application 
* @Description: 配置中心 服务端程序主类
* @author xuechen
* @date 2017年1月13日 下午2:31:53
*  
*/
@EnableConfigServer
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

}
