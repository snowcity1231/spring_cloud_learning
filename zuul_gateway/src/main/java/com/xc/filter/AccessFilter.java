package com.xc.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/** 
* @ClassName: AccessFilter 
* @Description: 实现在请求前检查是否包含accesstoken参数，没有的话就返回401错误
* @author xuechen
* @date 2017年1月16日 下午2:15:41
*  
*/
public class AccessFilter extends ZuulFilter{
	
	private static Logger log = Logger.getLogger(AccessFilter.class);

	/* (non-Javadoc)
	 * 过滤器具体逻辑
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURI().toString()));
		
		Object accessToken = request.getParameter("accessToken");
		if(accessToken == null) {
			log.warn("access token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			return null;
		}
		log.info("access token ok");
		return null;
	}

	/* (non-Javadoc)
	 * 过滤器开关，通过返回决定过滤器是否执行
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/* (non-Javadoc)
	 * 通过返回的字符串来定义过滤器执行顺序
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/* (non-Javadoc)
	 * 过滤器类型，pre-请求前；routing-请求时；error-请求发生错误时；post-在routing和error过滤器之后
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		return "pre";
	}

}
