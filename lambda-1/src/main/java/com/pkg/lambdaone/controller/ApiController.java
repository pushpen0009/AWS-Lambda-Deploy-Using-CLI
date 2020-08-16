package com.pkg.lambdaone.controller;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;
@RestController
@EnableWebMvc
public class ApiController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("classpath:api-body.json")
	private Resource resourceFile;

	@RequestMapping(path = "/call-api", method = RequestMethod.GET)
    public String time(@RequestParam(name="name") String functionName) {
		
		String result = "";
        // String functionName = "lambda2-Lambda2Function-1DB2RXWC5V3RC";

        InvokeResult invokeResult = null;

        try {
        	InvokeRequest invokeRequest = new InvokeRequest()
                    .withFunctionName(functionName)
                    .withPayload(new String(
                    	      Files.readAllBytes(resourceFile.getFile().toPath())));
        	
            AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
                    .withCredentials(new DefaultAWSCredentialsProviderChain())
                    .withRegion(Regions.AP_SOUTH_1).build();

            System.out.println("Before Invoking Lambda2");
            invokeResult = awsLambda.invoke(invokeRequest);
            System.out.println("After Invoking Lambda2");

            String ans = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);

            //write out the return value
            System.out.println(ans);
            result = ans;

        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(invokeResult.getStatusCode());

        
		// update API URL once lambda2 is deployed
//		String time = restTemplate.getForObject("https://reu2le394j.execute-api.ap-south-1.amazonaws.com/Prod/time", String.class);
//        return "Welcome:: "+name+ " at Time:: " +time;
        
        return result;
    }
}
