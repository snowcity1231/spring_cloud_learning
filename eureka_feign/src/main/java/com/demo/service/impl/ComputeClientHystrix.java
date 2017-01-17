package com.demo.service.impl;

import org.springframework.stereotype.Component;

import com.demo.service.ComputeClient;

/** 
* @ClassName: ComputeClientHystrix 
* @Description: 回调类，即ComputeClient接口中映射的fallback函数
* @author xuechen
* @date 2017年1月9日 上午10:30:46
*  
*/
@Component
public class ComputeClientHystrix implements ComputeClient {

	/* (non-Javadoc)
	 * @see com.demo.service.ComputeClient#add(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer add(Integer a, Integer b) {
		return -9999;
	}

}
