package com.home.service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.home.common.CommonUtilities;
import com.home.exception.ConsoleJobLogger;
import com.home.exception.DatabaseJobLogger;
import com.home.exception.FileJobLogger;

@Service
public class JobLoggerServiceImpl implements JobLoggerService{

	private static Logger logger = Logger.getLogger("MyLog");
	
	
	public String sendMessageByDatabase(String strMessage, String statusMessage, Map<String,String> dbParams) 
				throws DatabaseJobLogger{
		Connection connection = null;
		Statement stmt = null;
		try {
			Properties connectionProps = new Properties();
			connectionProps.put("user", dbParams.get("userName"));
			connectionProps.put("password", dbParams.get("password"));

			String strUrlDbms= CommonUtilities.getUrlConnetionByDbms(dbParams);
			connection = DriverManager.getConnection(strUrlDbms, connectionProps);

			stmt = connection.createStatement();
			StringBuilder stringBuilder= new StringBuilder();
			stringBuilder.append("insert into Log_Values values('");
			stringBuilder.append(strMessage);
			stringBuilder.append("', ");
			stringBuilder.append(statusMessage);
			stringBuilder.append(")");
			stmt.execute(stringBuilder.toString());
		} catch (SQLException ex) {
			logger.warning(ex.getMessage());
			throw new DatabaseJobLogger("Error al procesar logger por base de datos");
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (stmt != null) {
					stmt.close();
				}	
			} catch (SQLException e) {
				logger.warning(e.getMessage());	
			}
		}
		return null;
	}

	public String sendMessageByConsole(String strMessage) throws ConsoleJobLogger{
		try {
			//eliminamos los handlers anteriormente creados
			ConsoleHandler ch = new ConsoleHandler();
			logger.addHandler(ch);
			logger.log(Level.INFO, strMessage);
		} catch (Exception ex) {
			throw new ConsoleJobLogger("Error al procesar logger por consola");
		}
		return null;
	}

	public String sendMessageByFile(String strMessage, String fileFolder) throws FileJobLogger{
		try {
			File logFile = new File(fileFolder + "/logFile.txt");
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			
			FileHandler fh = new FileHandler(fileFolder + "/logFile.txt");
			logger.addHandler(fh);
			logger.log(Level.INFO, strMessage);
		} catch (Exception ex) {
			throw new FileJobLogger("Error al procesar logger por archivo");
		}
		return null;
	}

}
