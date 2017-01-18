package com.xc.flowcontrol.util;

import java.time.Instant;
import java.util.Map;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.EvictionListener;
import com.xc.flowcontrol.consts.ZuulConsts;
import com.xc.flowcontrol.vo.ResourceVo;

/** 
* @ClassName: SentineUtil 
* @Description: TODO
* @author xuechen
* @date 2017年1月17日 下午4:38:31
*  
*/
public class SentineUtil {
	
	/**
	 * 根据资源路径找到资源对象
	 * @param uri
	 * @return
	 */
	public static ResourceVo getResourceByUri(String uri) {
		for(Map.Entry<String, ResourceVo> entry : ZuulConsts.sentineMap.entrySet()) {
			ResourceVo resourceVo = entry.getValue();
			//regex
			boolean isMatch = ZuulUtil.format(uri, resourceVo.getUrlPattern());
			if(isMatch) {
				return resourceVo;
			}
		}
		return null;
	}
	
	public static boolean outOfFlow(String uri) {
		ResourceVo resourceVo = getResourceByUri(uri);
		ConcurrentLinkedHashMap<String, Long> lruMap = getLRUMapByBizId(resourceVo.getBizId());
		System.out.println("lruMap=" + lruMap.ascendingMap().toString());
		
		Long pre = lruMap.ascendingMap().entrySet().iterator().next().getValue();
//      Object[] keyArr = lruMap.keySet().toArray();
//      Long pre = lruMap.get(keyArr[0]);
//      Long last = lruMap.get(keyArr[keyArr.length - 1]);
      //校验第一个节点加上用户设定的时间是否大于当前时间,如果大于说明超过阈值.      如果lru队列已满,且首节点时间在距离当前时间的limittime内,说明溢出
		return lruMap.size() == resourceVo.getCapacity() && Instant.ofEpochMilli(pre).plusSeconds(resourceVo.getTime()).isAfter(Instant.now());
		
		//获取第一个元素
//		try {
//			Long pre = lruMap.ascendingMap().entrySet().iterator().next().getValue();
//			//检验第一个节点加上用户设定的时间是否大于当前时间，如果大于说明超过阙值
//			//如果lru队列已满，且首节点时间在距离当前时间的limittime内，说明溢出
//			return lruMap.size() == resourceVo.getCapacity() && Instant.ofEpochMilli(pre).plusSeconds(resourceVo.getTime()).isAfter(Instant.now());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		
	}
	
	public static ConcurrentLinkedHashMap<String, Long> getLRUMapByBizId(String uri) {
		return ZuulConsts.lruMap.get(uri);
	}
	
	public static ConcurrentLinkedHashMap<String, Long> getLRUMap(ResourceVo resourceVo) {
		Integer capacity = resourceVo.getCapacity();
		final int limitTime = resourceVo.getTime();
		//key ip; value timestamp
		EvictionListener<String, Long> listener = new EvictionListener<String, Long>() {
			//失效监听
			@Override
			public void onEviction(String key, Long value) {	
				System.out.println("Evicted key=" + key + ", value=" + value);
				//当触发失效，说明队列满了，这时判断被失效的元素的时间戳是否在距离当前时间的limit时间内，在则说明在Limit时间内访问次数过多，需要限流
				boolean outOfFlow = Instant.ofEpochMilli(value).plusMillis(limitTime).isAfter(Instant.now());
				System.out.println("outOfFlow=" + outOfFlow);
			}
		};
		ConcurrentLinkedHashMap<String, Long> map = new ConcurrentLinkedHashMap.Builder<String, Long>().maximumWeightedCapacity(capacity).listener(listener).build();
		return map;
	}
	
	public static void increment (String uri, String ip) {
		ResourceVo resourceVo = getResourceByUri(uri);
		if(resourceVo != null) {
			resourceVo.getCurrentViewCount().increment();
		}
		ConcurrentLinkedHashMap<String, Long> lruMap = getLRUMapByBizId(resourceVo.getBizId());
		if(lruMap == null) {
			lruMap = getLRUMap(resourceVo);
			ZuulConsts.lruMap.put(resourceVo.getBizId(), lruMap);
		}
		//key以url和ip和时间戳拼接，防止同一个ip狂刷，value为时间戳
		String key = uri + "-" + ip + "-";
		key += Instant.now().toString();
		lruMap.put(key, Instant.now().toEpochMilli());
	}

}
