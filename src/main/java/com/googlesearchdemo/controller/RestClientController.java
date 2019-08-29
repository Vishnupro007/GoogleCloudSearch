package com.googlesearchdemo.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlesearchdemo.dto.ApplicationConstants;
import com.googlesearchdemo.dto.ErrorData;
import com.googlesearchdemo.dto.SearchResourcesResponse;
import com.googlesearchdemo.service.SearchClientService;


@RestController
@RequestMapping("/api")
public class RestClientController {
	
	@Autowired
	private SearchClientService searchClientService;
	
	@Autowired
	private MessageSource messageSource;
 
//  @GetMapping("/result")
//  public List<String> getAllUsers() {
//    return userRepository.findAll();
//  }
  
  @RequestMapping(value = "/search", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<String> getGroupAdminReportByStatus(@RequestParam("searchKey") String searchKey, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SearchResourcesResponse sotcServiceRespnceByCode =null;
		try{
			
			sotcServiceRespnceByCode = searchClientService.searchServiceResponseByCode(searchKey);
			if(sotcServiceRespnceByCode!=null){
				return new ResponseEntity<>(createResponseStr(sotcServiceRespnceByCode), HttpStatus.OK);
			}else{
				ErrorData createErrorData = createErrorData(ApplicationConstants.CONTENT_NOT_FOUND);
				return new ResponseEntity<>(createResponseStr(createErrorData),HttpStatus.NO_CONTENT);
			}
		}catch(Exception se){
			System.out.println("Exception in Google Search API  Response ");
			return new ResponseEntity<>(createResponseStr(se.getMessage()), HttpStatus.FORBIDDEN);
		}
	}
  
  
  public String createResponseStr(Object responseObj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(responseObj);
		} catch (JsonProcessingException e) {
			System.out.println("Object to json string conversion problem"+ e);
		}
		return null;
	}
  
  private ErrorData createErrorData(String errorCode) {
		ErrorData error = new ErrorData();
		error.setErrorCode(errorCode);
		error.setErrorDesc(messageSource.getMessage(errorCode, null, Locale.ENGLISH));
		return error;
	}
 
}