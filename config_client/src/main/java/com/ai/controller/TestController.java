package com.ai.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/** 
* @ClassName: TestController 
* @Description: 返回from属性
* @author xuechen
* @date 2017年1月13日 下午4:12:46
*  
*/
@RefreshScope
@RestController
public class TestController {

	@Value("${from}")
	private String from;
	
	@RequestMapping("/from")
	public String from() {
		return from;
	}
	
	/**
	 * 模拟post请求到http://localhost:7002/refresh，刷新配置值
	 * @return
	 */
	@RequestMapping("/doPost")
	public static String doPost() {
		String response = "";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		HttpEntity httpEntity = null;
		
		try {
			httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost("http://localhost:7002/refresh");
			httpResponse = httpClient.execute(httpPost);
			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * 提交/bus/refresh实时更新消息总线上的属性配置
	 * 只要一处客户端提交更新请求，所有config-client客户端的配置信息都会更改
	 * @param destination 指定刷新范围，例如destination=democonfig:7002可以刷新具体实例，或者destination=democonfig:**利用路径匹配，刷新所有democonfig服务
	 * @return
	 */
	@RequestMapping("/doBusRefresh")
	public static String doBusRefresh(String destination) {
		String response = "";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		HttpEntity httpEntity = null;
		
		//可以通过给/bus/refresh接口配置参数，指定刷新反范围
		String url = "http://localhost:7002/bus/refresh";
		if(!StringUtils.isBlank(destination)){
			url += "?destination=" + destination;
		}
		
		try {
			httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpResponse = httpClient.execute(httpPost);
			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity, "UTF-8");
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
}
