package com.boot.web.starter.template.springboottemplate;

import com.boot.web.starter.template.springboottemplate.config.ApplicationConfigurer;
import com.boot.web.starter.template.springboottemplate.config.SecurityConfigurer;
import com.boot.web.starter.template.springboottemplate.rest.resources.TemplateResource;
import com.boot.web.starter.template.springboottemplate.rest.services.TemplateService;
import com.boot.web.starter.template.springboottemplate.rest.services.TemplateServiceImpl;
import com.boot.web.starter.template.springboottemplate.rest.validators.TemplateRequestVerifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@ComponentScan(basePackageClasses = { ApplicationConfigurer.class, SecurityConfigurer.class, TemplateService.class, TemplateServiceImpl.class,
									  TemplateResource.class, CommandLineAppStartupRunner.class,
									  TemplateRequestVerifier.class })
@EnableConfigurationProperties
@EnableWebSecurity
public class SpringboottemplateApplication extends SpringBootServletInitializer {

	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringboottemplateApplication.class, args);
	}
}
