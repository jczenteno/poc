package com.home.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class JavaXml implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	
	public JavaXml() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
