package com.googlesearchdemo.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://localhost:8080/spring-rest/foos";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
		//assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		System.out.println(response.getStatusCode()+":"+HttpStatus.OK);
	}

}
