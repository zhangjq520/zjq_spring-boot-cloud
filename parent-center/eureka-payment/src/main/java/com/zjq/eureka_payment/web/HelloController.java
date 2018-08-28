package com.zjq.eureka_payment.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {
	
	@Autowired
	private DiscoveryClient client;
	
	@Value("${server.port}")
    String port;
	
	@RequestMapping(value="hello", method=RequestMethod.GET)
	public String index() {
		ServiceInstance instance = client.getLocalServiceInstance();
		System.out.println(instance.getHost());
		System.out.println(instance.getServiceId());
		return "hello word, port: " + port;
	}
	
	@RequestMapping(value="test_post", method=RequestMethod.POST)
	public String testPost(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		if (null != request) {
			Set<String> paramsKey = request.getParameterMap().keySet();
			for (String key : paramsKey) {
				params.put(key, request.getParameter(key));
			}
		}
	    System.out.println(params);    
		return "success";
	}
}
