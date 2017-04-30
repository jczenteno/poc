package com.home.client;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.home.common.MessageLogger;

public class RestClient {

	private final static String REST_URI= "http://localhost:8080/ejercicio";
	
	/**
	 * Envia un mensaje atraves de un servicio POST  
	 * @param MessageLogger objMessageLogger
	 * @return String mensaje modificado
	 */
	public String sendJobLogger(MessageLogger objMessageLogger) {
		RestTemplate restTemplate= new RestTemplate();
				
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String strResult= restTemplate.postForObject(REST_URI+"/servicios"+"/sendMessage", objMessageLogger, String.class);
		return strResult;
	}
}
