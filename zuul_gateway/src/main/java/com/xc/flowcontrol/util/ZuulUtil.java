package com.xc.flowcontrol.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.context.RequestContext;
import com.xc.flowcontrol.consts.ZuulConsts;
import com.xc.flowcontrol.vo.ResourceVo;


/** 
* @ClassName: ZuulUtil 
* @Description: TODO
* @author xuechen
* @date 2017年1月17日 下午4:36:46
*  
*/
public class ZuulUtil {
	
	public static final String API_A = "api-a";
	
	static {
		init();
	}
	
	static void init() {
		ZuulConsts.blackUrlList.add("/black/*");
		ZuulConsts.whiteUrlList.add("/white/*");
		
		ZuulConsts.blackIpList.add("127.0.0.1");
		initSentinalPool();	
	}
	
	public static void initSentinalPool() {
		ResourceVo resourceVo = new ResourceVo();
		resourceVo.setUrlPattern("/api-a");
		resourceVo.setTime(60);
		resourceVo.setCapacity(6);
		resourceVo.setCallback("/failover");
		resourceVo.setBizId(API_A);
		//初始化用户自定义限流业务策略
		ZuulConsts.sentineMap.put(API_A, resourceVo);
		//初始化lruMap
		ZuulConsts.lruMap.put(API_A, SentineUtil.getLRUMap(resourceVo));
	}
	
	public static String getURI() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String uri = request.getRequestURI();
		return uri;
	}
	
	public static boolean format(String path, String patternStr) {
		boolean tag = true;
		final Pattern pattern = Pattern.compile(patternStr);
		final Matcher matcher = pattern.matcher(path);
		if(!matcher.find()) {
			tag = false;
		}
		return tag;
	}
	
	public static HttpServletResponse getResponse() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return ctx.getResponse();
	}
	
	public static boolean isPreException() {
		return getResponse().containsHeader(ZuulConsts.ALICP_HEADER_EXCEPTION);
	}
	
	public static String getRemoteIp(HttpServletRequest request) {
		if(request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded");
	}
}
