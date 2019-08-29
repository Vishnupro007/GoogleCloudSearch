package com.googlesearchdemo.service;

import com.googlesearchdemo.dto.SearchResourcesResponse;

public interface SearchClientService {

	public SearchResourcesResponse searchServiceResponseByCode(String searchKey) throws Exception;
	
}