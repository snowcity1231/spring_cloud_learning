package com.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.service.impl.ComputeClientHystrix;

/** 
* @ClassName: ComputeClient 
* @Description: 定义compute_service服务的接口
* @author xuechen
* @date 2017年1月6日 下午5:16:18
*  
*/
@FeignClient(value = "compute-service", fallback = ComputeClientHystrix.class)
public interface ComputeClient {

	@RequestMapping("/add")
	Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}
