package com.xc.flowcontrol.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


/** 
* @ClassName: BaseDo 
* @Description: TODO
* @author xuechen
* @date 2017年1月17日 下午4:30:36
*  
*/
public class BaseDo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
