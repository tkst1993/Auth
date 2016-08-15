package com.run.auth.entity;

import java.io.Serializable;

import com.run.auth.common.BaseEntity;

public class Functions extends BaseEntity implements Serializable{
	
	private String name;
	private Long parentId;
	private String url;
	private Integer serialNum;
	private Integer accordion;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getserialNum() {
		return serialNum;
	}
	public void setserialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}
	public Integer getAccordion() {
		return accordion;
	}
	public void setAccordion(Integer accordion) {
		this.accordion = accordion;
	}

}
