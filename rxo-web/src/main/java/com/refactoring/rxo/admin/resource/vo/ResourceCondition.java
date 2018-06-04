package com.refactoring.rxo.admin.resource.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



public class ResourceCondition  {


	private String id;
	private Integer version;

	private String systemCode;
    private String name;
    private String type;
    private String orderIndex;
    private String parentId;
    private String url;


    public String getName(){
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }


    public String getType(){
    	return type;
    }
    public void setType(String type) {
    	this.type = type;
    }


    public String getOrderIndex(){
    	return orderIndex;
    }
    public void setOrderIndex(String orderIndex) {
    	this.orderIndex = orderIndex;
    }


    public String getParentId(){
    	return parentId;
    }
    public void setParentId(String parentId) {
    	this.parentId = parentId;
    }


    public String getUrl(){
    	return url;
    }
    public void setUrl(String url) {
    	this.url = url;
    }

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	/**
	* @return the id
	*/
	public String getId() {
		return id;
	}

	/**
	* @param id the id to set
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**
	* @return the version
	*/
	public Integer getVersion() {
		return version;
	}

	/**
	* @param version the version to set
	*/
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
