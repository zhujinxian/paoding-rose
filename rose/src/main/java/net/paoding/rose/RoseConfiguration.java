/**

The MIT License (MIT)

Copyright (c) <2015> <author or authors>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/
package net.paoding.rose;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhu jinxian
 * @date  2015年10月14日
 * 
 */

@Configuration
@EnableAutoConfiguration(exclude = { VelocityAutoConfiguration.class })
public class RoseConfiguration {
	@Bean
	public FilterRegistrationBean intiRose() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		RoseFilter roseFilter = new RoseFilter();
		registrationBean.setFilter(roseFilter);
		registrationBean.setUrlPatterns(getUrlPatterns());
		registrationBean.setOrder(1);
		return registrationBean;
	}

	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	    return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				customizeSeverContainer(container);
			}
	    };
	}
	
	protected void customizeSeverContainer(ConfigurableEmbeddedServletContainer container) {
		URL url = this.getClass().getResource("/");
		container.setDocumentRoot(new File(url.getFile()));
	}
	
	protected Set<String> getUrlPatterns() {
		Set<String> urlPatternsSet = new HashSet<String>();
		urlPatternsSet.add("/*");
		return urlPatternsSet;
	}
}
