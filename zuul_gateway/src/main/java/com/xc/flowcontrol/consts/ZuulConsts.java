package com.xc.flowcontrol.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.xc.flowcontrol.vo.ResourceVo;



/** 
* @ClassName: ZuulConsts 
* @Description: TODO
* @author xuechen
* @date 2017年1月17日 下午4:40:19
*  
*/
public class ZuulConsts {
	
	public static List<String> blackUrlList = new ArrayList<>();
	
	public static List<String> whiteUrlList = new ArrayList<>();
	
	public static List<String> blackIpList = new ArrayList<>();
	
	public static final String ur_prefix_pattern = "\\alicp\\.*";
	
	public static Map<String, LongAdder> pvMap = new ConcurrentHashMap<>();
	
	public static Map<String, ResourceVo> sentineMap = new HashMap<>();	//限流map，保存用户配置信息
	
	public static Map<String, ConcurrentLinkedHashMap<String, Long>> lruMap = new HashMap<>();	//保存资源的实际请求流水
	
	public static String ALICP_HEADER_VERSION = "x-ca-version";
	
	public static String ALICP_HEADER_COPYRIGHT = "x-ca-copyright";
	
	public static String ALICP_HEADER_EXCEPTION = "x-ca-exception";
	
	public static String ALICP_FAILOVER_URL = "/failover";

}
