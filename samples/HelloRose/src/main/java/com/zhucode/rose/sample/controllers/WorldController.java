package com.zhucode.rose.sample.controllers;

import org.springframework.beans.factory.annotation.Value;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.portal.Portal;

@Path("hello")
public class WorldController {


	@Value("${test: w World}")
	public String test;
	
	
    @Get("w")
    public String hello(Portal p) {
    	return "@hello --- " + test;
    }
    
    @Get("")
    public String index(Portal p) {
    	p.addWindow("p1", "/hello/wp1");
        p.addWindow("p2", "/hello/wp2");
        return "world";
    }


	
	@Get("wp1")
	public String portal1() {
		return "@this is p1";
	}

	@Get("wp2")
	public String portal2() {
		return "@this is p2";
	}
	
	
	
	
   
}
