package com.home.service;

import java.util.Map;

import com.home.exception.ConsoleJobLogger;
import com.home.exception.DatabaseJobLogger;
import com.home.exception.FileJobLogger;

public interface JobLoggerService {

	/**
	 * Procesa el mensaje por base de datos 
	 * @param String strMessage
	 * @param String statusMessage
	 * @param Map dbParams
	 * @return String
	 * @throws DatabaseJobLogger
	 */
	public String sendMessageByDatabase(String strMessage, String statusMessage, Map<String,String> dbParams) throws DatabaseJobLogger;
	
	/**
	 * Procesa el mensaje por consola
	 * @param String strMessage
	 * @return String 
	 * @throws ConsoleJobLogger
	 */
	public String sendMessageByConsole(String strMessage) throws ConsoleJobLogger;
	
	/**
	 * Procesa el mensaje por archivo
	 * @param String strMessage
	 * @param String fileFolder
	 * @return String
	 * @throws FileJobLogger
	 */
	public String sendMessageByFile(String strMessage, String fileFolder) throws FileJobLogger;
}
