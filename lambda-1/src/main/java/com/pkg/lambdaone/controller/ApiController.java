package com.pkg.lambdaone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class ApiController {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(path = "/call-api", method = RequestMethod.GET)
    public String time(@RequestParam(name="name") String name) {
		// update API URL once lambda2 is deployed
		String time = restTemplate.getForObject("https://reu2le394j.execute-api.ap-south-1.amazonaws.com/Prod/time", String.class);
        return "Welcome:: "+name+ " at Time:: " +time;
    }
}
