package com.pkg.lambdaone.controller;

import java.time.Instant;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class TimeController {

	@RequestMapping(path = "/time", method = RequestMethod.GET)
    public String time() {
        return Instant.now().toString();
    }
}
