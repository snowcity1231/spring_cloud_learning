package com.xc.flowcontrol.vo;

import com.netflix.hystrix.util.LongAdder;

/** 
* @ClassName: ResourceVo 
* @Description: 这需要记录每一个时间点的访问量,因为用户请求是按时间点过来的,可以用ConcurrentLinkedHashMap 或者ringbuffer思想
* @author xuechen
* @date 2017年1月17日 下午4:32:08
*  
*/
public class ResourceVo extends BaseDo {

	private static final long serialVersionUID = -6914633155521447254L;
	
	private String bizId;
	private String urlPattern;
	private Integer capacity = 5;	//访问次数阙值
	private LongAdder currentViewCount = new LongAdder();	//当前访问次数
	private int time = 60;	//限定时间
	private String callback;	//限流后回调地址
	private volatile boolean outOfFlow;
	/**
	 * @return the bizId
	 */
	public String getBizId() {
		return bizId;
	}
	/**
	 * @param bizId the bizId to set
	 */
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	/**
	 * @return the urlPattern
	 */
	public String getUrlPattern() {
		return urlPattern;
	}
	/**
	 * @param urlPattern the urlPattern to set
	 */
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}
	/**
	 * @return the capacity
	 */
	public Integer getCapacity() {
		return capacity;
	}
	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	/**
	 * @return the currentViewCount
	 */
	public LongAdder getCurrentViewCount() {
		return currentViewCount;
	}
	/**
	 * @param currentViewCount the currentViewCount to set
	 */
	public void setCurrentViewCount(LongAdder currentViewCount) {
		this.currentViewCount = currentViewCount;
	}
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	/**
	 * @return the callback
	 */
	public String getCallback() {
		return callback;
	}
	/**
	 * @param callback the callback to set
	 */
	public void setCallback(String callback) {
		this.callback = callback;
	}
	/**
	 * @return the outOfFlow
	 */
	public boolean isOutOfFlow() {
		return outOfFlow;
	}
	/**
	 * @param outOfFlow the outOfFlow to set
	 */
	public void setOutOfFlow(boolean outOfFlow) {
		this.outOfFlow = outOfFlow;
	}
	
	public boolean outOfFlow() {
		return currentViewCount.longValue() >= capacity.longValue();
	}

}
