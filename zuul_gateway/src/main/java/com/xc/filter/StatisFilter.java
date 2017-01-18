package com.xc.filter;

import java.util.concurrent.atomic.LongAdder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xc.flowcontrol.consts.ZuulConsts;
import com.xc.flowcontrol.util.SentineUtil;
import com.xc.flowcontrol.util.ZuulUtil;

/** 
* @ClassName: StatisFilter 
* @Description: TODO
* @author xuechen
* @date 2017年1月18日 上午9:27:49
*  
*/
@Component
public class StatisFilter extends ZuulFilter {
	
	private static Logger log = Logger.getLogger(StatisFilter.class);
	
	@Override
	public String filterType() {
		return "post";
	}
	
	@Override
	public int filterOrder() {
		return 0;
	}
	
	@Override
	public boolean shouldFilter() {
		if(ZuulUtil.isPreException()) {
			log.error("pre filter is error");
			return false;
		}
		return true;
	}

	@Override
	public Object run() {
		log.info("-post- --- StatisFilter run!");
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();
		if(response == null) {
			return null;
		}
		response.setHeader(ZuulConsts.ALICP_HEADER_VERSION, "1.0.0");
		response.setHeader(ZuulConsts.ALICP_HEADER_COPYRIGHT, "alicp");
		log.info("header:" + response.getHeaderNames());
		
		//统计pv
		String uri = request.getRequestURI();
		LongAdder pv;
		if(ZuulConsts.pvMap.containsKey(uri)) {
			pv = ZuulConsts.pvMap.get(uri);
		} else {
			pv = new LongAdder();
		}
		SentineUtil.increment(uri, ZuulUtil.getRemoteIp(request));
		ZuulConsts.pvMap.put(uri, pv);
		return null;
	}
	

}
