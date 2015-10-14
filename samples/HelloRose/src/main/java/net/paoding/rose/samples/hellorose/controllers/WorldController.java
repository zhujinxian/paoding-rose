package net.paoding.rose.samples.hellorose.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.portal.Portal;

@Path("world")
public class WorldController {


    @Get("")
    public String index(Portal p) {
    	p.addWindow("p1", "/world/wp1");
        p.addWindow("p2", "/world/wp2");
        return "world";
    }

	
	@Get("/wp1")
	public String portal1() {
		return "@this is p1";
	}

	@Get("/wp2")
	public String portal2() {
		return "@this is p2";
	}
   
}
