package com.home.component;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.home.common.MessageStatusType;
import com.home.common.MessageTargetType;
import com.home.exception.JobLoggerException;
import com.home.service.JobLoggerService;

@Component
public class JobLoggerComponent {
	
	@Autowired
	private JobLoggerService objJobLoggerService;
	
	/**
	 * 
	 * @param String strMessage
	 * @param Integer targetMessage
	 * @param Integer statusMessage
	 * @param Map dbParams
	 * @return
	 * @throws JobLoggerException exepcion de JobLogger
	 */
	public synchronized String sendLogMessage(String strMessage, Integer targetMessage, Integer statusMessage, Map<String, String> dbParams) throws JobLoggerException {
		StringBuilder newMessageText= new StringBuilder();
		try {
			//creamos el nuevo mesaje deacuerdo al estado
			newMessageText.append(MessageStatusType.get(statusMessage).getDescription())
						.append(DateFormat.getDateInstance(DateFormat.LONG).format(new Date()))
						.append(" ").append(strMessage);
			//procesamos mensaje por consola
			if (MessageTargetType.CONSOLE.getCode().equals(targetMessage)) {
				objJobLoggerService.sendMessageByConsole(newMessageText.toString());
			}
			//procesamos mensaje por base de datos
			if (MessageTargetType.DATA_BASE.getCode().equals(targetMessage)) {
				objJobLoggerService.sendMessageByDatabase(newMessageText.toString(), statusMessage.toString(), dbParams);
			}
			//procesamos mensaje por archivo
			if (MessageTargetType.FILE.getCode().equals(targetMessage)) {
				objJobLoggerService.sendMessageByFile(newMessageText.toString(), dbParams.get("logFileFolder"));
			}
		} catch (JobLoggerException ex) {
			throw new JobLoggerException(ex);
		}
		return newMessageText.toString();
	}
	
}
