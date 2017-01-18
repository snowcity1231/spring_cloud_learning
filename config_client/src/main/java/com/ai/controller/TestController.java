package com.ai.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
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
	
}
