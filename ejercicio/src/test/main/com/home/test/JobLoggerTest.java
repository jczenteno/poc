package com.home.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import com.home.client.RestClient;
import com.home.common.MessageLogger;
import com.home.common.MessageStatusType;
import com.home.common.MessageTargetType;
import com.home.component.JobLoggerComponent;
import com.home.config.TestContextService;
import com.home.exception.JobLoggerException;




public class JobLoggerTest extends TestContextService{
	
	private static Logger logger = Logger.getLogger("JobLoggerTest");
	
	RestClient restClient;
	static List<MessageLogger> lstMessageLogger;
	static Map<String, String> mpDbConfig;
	
	@Autowired
	JobLoggerComponent objJobLoggerComponent;
	
	@Rule
	public ExpectedException exception= ExpectedException.none();
	
	@BeforeClass
	public static void confugureInifiatlParams() {
		//configuracion de BD
		mpDbConfig= new HashMap<>();
		mpDbConfig.put("userName", "IDEPOSITARY");
		mpDbConfig.put("password", "IDEPOSITARY01");
		mpDbConfig.put("dbms", "oracle");
		mpDbConfig.put("serverName", "192.168.1.11");
		mpDbConfig.put("portNumber", "1525");
		mpDbConfig.put("logFileFolder", "D:/");
		mpDbConfig.put("sid", "xe");
		
		lstMessageLogger= new ArrayList<>();
		
		for (MessageTargetType messageTargetType: MessageTargetType.values()) {
			for (MessageStatusType messageStatusType: MessageStatusType.values()) {
				String message= messageTargetType.getDescription();
				message += " - " + messageStatusType.getDescription();
				MessageLogger objMessageLogger= setMessageLoggerData(message, messageTargetType.getCode(), messageStatusType.getCode());
				lstMessageLogger.add(objMessageLogger);
			}
		}
	}
	
	private static MessageLogger setMessageLoggerData(String message, Integer target, Integer status) {
		MessageLogger objMessageLogger= new MessageLogger();
		objMessageLogger.setMessageStatus(status);
		objMessageLogger.setMessageTarget(target);
		objMessageLogger.setDbParams(mpDbConfig);
		//mensaje
		objMessageLogger.setStrMessage(message);
		return objMessageLogger;
	}
	
	@Test
	public void testJobLoggerRestClient(){
		restClient= new RestClient();
		logger.info("Probando testJobLoggerRestClient()");
		for (MessageLogger objMessageLogger: lstMessageLogger) {
			//exception.expect(DatabaseLoggerException.class);
			String strResult= restClient.sendJobLogger(objMessageLogger);
			Assert.assertNotNull(strResult);
			Assert.assertNotEquals(strResult,objMessageLogger.getStrMessage());
		}
	}

	@Test
	public void testJobLoggerController() {
		System.out.println("Probando testJobLoggerController()");
		for (MessageLogger objMessageLogger: lstMessageLogger) {
			try {
				exception.expect(JobLoggerException.class);
				exception.expectMessage(JUnitMatchers.containsString("base de datos"));
				String strResult= objJobLoggerComponent.sendLogMessage(objMessageLogger.getStrMessage(), 
																		objMessageLogger.getMessageTarget(), 
																		objMessageLogger.getMessageStatus(), 
																		objMessageLogger.getDbParams());
				Assert.assertNotEquals(strResult,objMessageLogger.getStrMessage());
			} catch (JobLoggerException ex) {
				logger.warning(ex.getMessage());
			}
		}
	}
	
}
