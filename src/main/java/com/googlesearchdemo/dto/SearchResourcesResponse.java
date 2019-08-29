package com.googlesearchdemo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown= true)
public class SearchResourcesResponse implements Serializable{
	
	private static final long serialVersionUID = -570353617857042358L;
	
//	@JsonProperty
//	private List<SOTCResources> assetInfo;
	
	@JsonProperty
	private Integer count;
	
	@JsonProperty
	private Integer statusCode;
	
	@JsonProperty
	private String message;
	
	@JsonProperty
	private String[] results;
	
	@JsonProperty
	private String[] fields;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getResults() {
		return results;
	}

	public void setResults(String[] results) {
		this.results = results;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	
	

}
