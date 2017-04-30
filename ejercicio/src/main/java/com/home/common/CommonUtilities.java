package com.home.common;

import java.util.Map;

import com.home.exception.DatabaseJobLogger;
import com.thoughtworks.xstream.XStream;

public class CommonUtilities {

	
	private CommonUtilities() {
		
	}
	
	/**
	 * Devuelve la url de conexion de acuerdo a la base de datos
	 * @param Map dbParams
	 * @return String
	 * @throws DatabaseJobLogger 
	 * @throws Exception
	 */
	public static String getUrlConnetionByDbms(Map<String, String> dbParams) throws DatabaseJobLogger {
		if (dbParams != null && !dbParams.isEmpty()) {
			StringBuilder stringBuilder= new StringBuilder("jdbc:");
			if (dbParams.containsKey("dbms")) {
				String dbms= dbParams.get("dbms");
				switch (dbms) {
					case GeneralConstant.MYSQL: 
						stringBuilder.append(dbms).append("://");
						stringBuilder.append(dbParams.get("serverName")).append(":");
						stringBuilder.append(dbParams.get("portNumber")).append("/");
						break;
					case GeneralConstant.ORACLE: 
						stringBuilder.append(dbms).append(":thin:@");
						stringBuilder.append(dbParams.get("serverName")).append(":");
						stringBuilder.append(dbParams.get("portNumber")).append(":");
						stringBuilder.append(dbParams.get("sid"));
						break;
					default: break;	
				}
				return stringBuilder.toString();
			} else {
				throw new DatabaseJobLogger("No se tiene configuracion para el dbms: "+dbParams.get("dbms"));
			}
		} else {
			throw new DatabaseJobLogger("No se ingresaron datos de configuracion para la BD");
		}
	}
	
	public static String convertObjectToXml(Object object, String objectAlias, Map<String,String> fieldsAlias) {
		XStream xStream= new XStream();
		//xStream.autodetectAnnotations(Boolean.TRUE);
		xStream.alias(objectAlias, object.getClass());
		if (fieldsAlias != null && !fieldsAlias.isEmpty()) {
			fieldsAlias.entrySet().stream().forEach(field -> xStream.aliasField(field.getValue(), object.getClass(), field.getKey()));
		}	
		return xStream.toXML(object);
	}
}
