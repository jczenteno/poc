package com.home.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.home.common.MessageLogger;
import com.home.component.JobLoggerComponent;
import com.home.exception.JobLoggerException;

@RestController
@RequestMapping("/servicios")
public class JobLoggerController {

	@Autowired
	private JobLoggerComponent objJobLoggerComponent;
	
	
	@RequestMapping(value="/sendMessageGet", method= RequestMethod.GET)
	public ResponseEntity<String> sendMessageLoggerG() {	
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	/**
	 * Servicio POST que procesa el mensaje enviado por el cliente 
	 * @param MessageLogger objMessageLogger
	 * @return String mensaje procesado
	 */
	@RequestMapping(value="/sendMessage", method= RequestMethod.POST)
	public ResponseEntity<String> sendMessageLogger(@RequestBody MessageLogger objMessageLogger) {
		String strMessage= null;
		try {
			strMessage= objJobLoggerComponent.sendLogMessage(objMessageLogger.getStrMessage(), 
					objMessageLogger.getMessageTarget(), 
					objMessageLogger.getMessageStatus(),
					objMessageLogger.getDbParams());
		} catch (JobLoggerException ex) {
			strMessage= ex.getMessage();
		}
		return new ResponseEntity<>(strMessage, HttpStatus.OK);
	}
}

