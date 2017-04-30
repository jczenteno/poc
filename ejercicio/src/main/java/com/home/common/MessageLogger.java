package com.home.common;

import java.io.Serializable;
import java.util.Map;

public class MessageLogger implements Serializable{

	private static final long serialVersionUID = 1L;
	private String strMessage;
	private Integer messageTarget;
	private Integer messageStatus;
	private Map<String, String> dbParams;
	
	public MessageLogger() {
		super();
	}


	public String getStrMessage() {
		return strMessage;
	}


	public void setStrMessage(String strMessage) {
		this.strMessage = strMessage;
	}


	public Integer getMessageTarget() {
		return messageTarget;
	}


	public void setMessageTarget(Integer messageTarget) {
		this.messageTarget = messageTarget;
	}


	public Integer getMessageStatus() {
		return messageStatus;
	}


	public void setMessageStatus(Integer messageStatus) {
		this.messageStatus = messageStatus;
	}


	public Map<String, String> getDbParams() {
		return dbParams;
	}


	public void setDbParams(Map<String, String> dbParams) {
		this.dbParams = dbParams;
	}

	
}
