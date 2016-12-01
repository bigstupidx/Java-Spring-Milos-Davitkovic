package com.java.Rest;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.DataCollector.RestObject;
import com.google.gson.Gson;
import com.vdTinkerforge.APIServerTime;

@Controller
public class RestFn {
	
	public RestFn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestObject GET_Request(String url) {
		RestTemplate rest = new RestTemplate();
		RestObject restObject = rest.getForObject(url, RestObject.class);
		System.out.println("RestFn: Get resposne " + restObject.toString());
		return restObject;
	}
	
	public String GET_Request_Value(String url) {
		RestTemplate rest = new RestTemplate();
		RestObject restObject = rest.getForObject(url, RestObject.class);
		System.out.println("RestFn: Get resposne value" + restObject.values);
		return restObject.values;
	}
	
	public RestObject POST_Request(String url, String finalString) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(finalString, headers);
		RestObject response = restTemplate.postForObject(url, entity, RestObject.class);
		System.out.println("RestFn: Post resposne " + response.toString());
		return response;
	} 
	
	public String POST_Request_Value(String url, String finalString) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(finalString, headers);
		RestObject response = restTemplate.postForObject(url, entity, RestObject.class);
		System.out.println("RestFn: Post resposne value" + response.values);
		return response.values;
	} 
	
	/**
	 * 
	 * @param url URL of Rest Server
	 * @param jsonObj Object for senting towards Rest Server
	 * @return
	 */
	public String POST_Request(String url, JSONObject jsonObj) {
		JSONObject objectForSending = jsonObj;
		String finalString = objectForSending.toJSONString();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
		HttpEntity<String> entity = new HttpEntity<String>(finalString, headers);
		RestObject response = restTemplate.postForObject(url, entity, RestObject.class);
		System.out.println("RestFn: Post resposne value" + response.values);
		return response.values;
	} 
	
	public Object POST_Request(String url, String objectName, Object tmpObj) {
		JSONObject objectForSending = new JSONObject();
		String tmpObjJSON = new Gson().toJson(tmpObj);
		objectForSending.put(objectName, tmpObjJSON);
		String finalString = objectForSending.toJSONString();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(finalString, headers);
		RestObject response = restTemplate.postForObject(url, entity, RestObject.class);
		System.out.println("RestFn: Post resposne value" + response.values);
		return response.values;
	} 
	
	
}
