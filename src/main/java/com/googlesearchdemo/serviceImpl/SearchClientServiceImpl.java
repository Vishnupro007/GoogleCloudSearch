package com.googlesearchdemo.serviceImpl;

import java.util.Base64;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlesearchdemo.dto.SearchResourcesResponse;
import com.googlesearchdemo.service.SearchClientService;

@Service("SearchClientService")
public class SearchClientServiceImpl implements SearchClientService{
	
	@Value("${api.url}")
	private String searchApiURI;
	
	@Value("${authorization}")
	private String authorization;
	
	@Value("${client.id}")
	private String clientID;
	
	@Value("${client_secret}")
	private String clientSecret;
	
	@Value("${key}")
	private String key;
	
	

	@Override
	public SearchResourcesResponse searchServiceResponseByCode(String searchKey) throws Exception {
		SearchResourcesResponse response = null;
		
		try {

			if (searchKey != null) {
				RestTemplate restTemplate = new RestTemplate();
				
				JSONObject main = new JSONObject();
				main.put("query", searchKey);
				JSONObject user = new JSONObject();
				user.put("searchApplicationId", "searchapplications/default");
				main.put("requestOptions", user);
				
				
				 HttpHeaders headers = createHttpHeaders();
				 System.out.println("Headers Created:"+headers.toString());
				 System.out.println("Json request body created:"+main.toString());
			     HttpEntity<String> entity = new HttpEntity<String>(main.toString(), headers);
			     
			     
			     ResponseEntity<String> responsee = restTemplate.exchange(searchApiURI, HttpMethod.POST, entity, String.class);
			     System.out.println("Result - status ("+ responsee.getStatusCode() + ")" );
			     
			     System.out.println("has body: " + responsee.getBody());
			     
				if (responsee != null && isJSONValid(responsee.getBody())) {
					System.out.println("Response Body is a valid Json Object: ");
					ObjectMapper mapper = new ObjectMapper();
					response = mapper.readValue(responsee.getBody(),SearchResourcesResponse.class);
					return response;
				}

			}
		} catch (Exception e) {
			System.out.println("Error in Content Retrive: "+ e);
		}

		return response;
	}
	
	private HttpHeaders createHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		 headers.add("Authorization", "Bearer " + authorization);
		 headers.add("client_id", clientID);
		 headers.add("client_secret", clientSecret);
		 headers.add("Key", key);
		return headers;
	}
	
	public boolean isJSONValid(String jsonString) {
		try {
			new JSONObject(jsonString);
		} catch (JSONException ex) {

			try {
				new JSONArray(jsonString);
			} catch (JSONException jsonex) {
				System.out.println("JSON ARRAY EXCEPTION :" + jsonex);
				return false;
			}
		}
		return true;
	}
	
}