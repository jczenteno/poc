package com.home.common;

public enum MessageTargetType {
	
	DATA_BASE(1, "database"),
	CONSOLE(2, "console"),
	FILE(3, "file");
	
	private final Integer code;
	private final String description;
	
	MessageTargetType(Integer code, String description) {
		this.code= code;
		this.description= description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}
