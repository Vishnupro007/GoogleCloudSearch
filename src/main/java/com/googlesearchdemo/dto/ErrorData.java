package com.googlesearchdemo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * POJO Class for ErrorData
 * 
 */
@JsonSerialize
public class ErrorData implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty
	private String errorCode;
   
	@JsonProperty
	private String errorDesc;
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	@JsonIgnore
	private transient  List<String> errorList;
	
	

}
