/**

The MIT License (MIT)

Copyright (c) <2015> <author or authors>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/
package net.paoding.rose.web.portal;

import java.util.concurrent.Executors;

import net.paoding.rose.web.paramresolver.ParamResolver;
import net.paoding.rose.web.portal.impl.MergeWindowAttributesToModelInterceptor;
import net.paoding.rose.web.portal.impl.PipeInterceptor;
import net.paoding.rose.web.portal.impl.PortalBeanPostProcessor;
import net.paoding.rose.web.portal.impl.PortalFactoryImpl;
import net.paoding.rose.web.portal.impl.PortalResolver;
import net.paoding.rose.web.portal.impl.PortalWaitInterceptor;
import net.paoding.rose.web.portal.impl.WindowResolver;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhu jinxian
 * @date  2015年10月14日
 * 
 */
@Configuration
public class PortalConfiguration {

	@Bean
	ParamResolver getPoertalResolver() {
		PortalFactoryImpl pf = new PortalFactoryImpl();
		pf.setWindowListener(new WindowListeners());
		pf.setExecutorService(Executors.newCachedThreadPool());
		PortalResolver pr = new PortalResolver();
		pr.setPortalFactory(pf);
	
		return pr;
	}
	
	@Bean
	WindowResolver getWindowResolver() {
		return new WindowResolver();
	}
	
	@Bean
	PortalWaitInterceptor getPortalWaitInterceptor() {
		return new PortalWaitInterceptor();
	}
	
	@Bean
	MergeWindowAttributesToModelInterceptor getMergeWindowAttributesToModelInterceptor() {
		return new MergeWindowAttributesToModelInterceptor();
	}
	
	@Bean
	PipeInterceptor getPipeInterceptor() {
		return new PipeInterceptor();
	}
}
