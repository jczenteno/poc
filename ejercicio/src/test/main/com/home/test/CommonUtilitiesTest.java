package com.home.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.common.CommonUtilities;
import com.home.config.TestContextService;
import com.home.model.JavaXml;

import junit.framework.Assert;

public class CommonUtilitiesTest extends TestContextService{

	private static Logger logger = Logger.getLogger("CommonUtilitiesTest");
	
	@Test
	public void converObjectToXmlTest1() {
		JavaXml javaXml= new JavaXml();
		javaXml.setId(123L);
		javaXml.setName("jose");
		javaXml.setDescription("el papi");
		Map<String,String> fieldsAlias= new HashMap<>();
		fieldsAlias.put("id", "DATA_ID");
		fieldsAlias.put("name", "DATA_NAME");
		fieldsAlias.put("description", "DATA_DESCRIPTION");
		String xml= CommonUtilities.convertObjectToXml(javaXml, "data", fieldsAlias);
		logger.info(xml);
		Assert.assertNotNull(xml);
	}
	
	@Test
	public void converObjectToXmlTest2() {
		Map<String,String> fieldsAlias= new HashMap<>();
		fieldsAlias.put("id", "DATA_ID");
		fieldsAlias.put("name", "DATA_NAME");
		fieldsAlias.put("description", "DATA_DESCRIPTION");
		String xml= CommonUtilities.convertObjectToXml(new JavaXml(), "data", fieldsAlias);
		logger.info(xml);
		Assert.assertNotNull(xml);
	}
	
	@Test
	public void converObjectToXmlTest3() {
		JavaXml javaXml= new JavaXml();
		javaXml.setId(0L);
		javaXml.setName("");
		javaXml.setDescription("");
		Map<String,String> fieldsAlias= new HashMap<>();
		fieldsAlias.put("id", "DATA_ID");
		fieldsAlias.put("name", "DATA_NAME");
		fieldsAlias.put("description", "DATA_DESCRIPTION");
		String xml= CommonUtilities.convertObjectToXml(javaXml, "data", fieldsAlias);
		logger.info(xml.replaceAll("0", ""));
		logger.info(xml.replaceAll("__", "_"));
		Assert.assertNotNull(xml);
	}
	
	@Test
	public void convertMapToJsonTest() {
		Map<String,String> mpString= new HashMap<>();
		mpString.put("value1", "descp1");
		mpString.put("value2", "descp2");
		mpString.put("value3", "descp3");
		
		List<Map<String,String>> mpList= new ArrayList<>();
		for (int i=0; i<3; ++i) {
			Map<String,String> mpString2= new HashMap<>();
			mpString2.put("value"+i, "descp"+i);
			mpList.add(mpString2);
		}
		
		ObjectMapper mapper= new ObjectMapper();
		try {
			String strJson= mapper.writeValueAsString(mpString);
			String strJson2= mapper.writeValueAsString(mpList);
			logger.info(strJson);
			logger.info(strJson2);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
