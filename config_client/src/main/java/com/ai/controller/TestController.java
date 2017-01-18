package com.ai.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
	 */
	@RequestMapping(value = "post")
	public String post() {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		
		try {
			URL url = new URL("http://localhost:7002/refresh");
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			//post请求
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			out = new PrintWriter(conn.getOutputStream());
			out.println();
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line = in.readLine()) != null) {
				result += line;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null) {
					out.close();
				}
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
}
