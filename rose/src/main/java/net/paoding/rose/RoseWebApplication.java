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


@EnableAutoConfiguration(exclude = { VelocityAutoConfiguration.class })
public class RoseWebApplication {
	
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
