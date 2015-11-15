package com.zhucode.rose.sample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.portal.Portal;

@Path("m")
public class MainController {
	

	@Value("${test:main World}")
	public String test;
	
	
    @Get("w")
    public String main() {
    	return "@heelo --- " + test;
    }

}
