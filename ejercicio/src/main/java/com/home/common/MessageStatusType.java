package com.home.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum MessageStatusType {

	MESSAGE(1, "message"),
	ERROR(2, "error"),
	WARNING(3, "warning");
	
	private final Integer code;
	private final String description;
	
	private static final Map<Integer, MessageStatusType> lookup = new HashMap<>();
	
	MessageStatusType(Integer code, String description) {
		this.code= code;
		this.description= description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	static {
		for (MessageStatusType messageStatusType: EnumSet.allOf(MessageStatusType.class)) {
			lookup.put(messageStatusType.code, messageStatusType);
		}
	}
	
	public static MessageStatusType get(Integer code) {
		return lookup.get(code);
	}
}
