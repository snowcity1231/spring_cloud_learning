package com.xc.filter;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.xc.flowcontrol.consts.ZuulConsts;
import com.xc.flowcontrol.util.SentineUtil;
import com.xc.flowcontrol.util.ZuulUtil;
import com.xc.flowcontrol.vo.ResourceVo;

/** 
* @ClassName: CircuitFilter 
* @Description: 限流过滤器，一定时间内超过流量则熔断
* @author xuechen
* @date 2017年1月17日 下午4:27:22
*  
*/
public class CircuitFilter extends ZuulFilter {
	
	private static final Logger log = Logger.getLogger(CircuitFilter.class);
	ThreadLocal<ResourceVo> threadLocal = new ThreadLocal<>();
	
	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		return "pre";
	}
	
	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 1;
	}
	
	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		ResourceVo resourceVo = SentineUtil.getResourceByUri(ZuulUtil.getURI());
		log.info("----resourceVo---" + resourceVo.toString());
		threadLocal.set(resourceVo);
		return resourceVo != null;
	}


	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		log.info("---CircuitFilter run---");
		ResourceVo resourceVo = threadLocal.get();
		if(SentineUtil.outOfFlow(ZuulUtil.getURI())) {
			String callbackUrl = resourceVo.getCallback();
			if(StringUtils.isEmpty(callbackUrl)) {
				callbackUrl = ZuulConsts.ALICP_FAILOVER_URL;
			}
			try {
				log.error("--out of flow , failover---");
				ZuulUtil.getResponse().setHeader(ZuulConsts.ALICP_HEADER_EXCEPTION, "outofflow");
				ZuulUtil.getResponse().sendRedirect(callbackUrl);
			} catch (IOException e) {
				log.error("send Redirect error", e);
			}
		}
		return null;
	}

}
