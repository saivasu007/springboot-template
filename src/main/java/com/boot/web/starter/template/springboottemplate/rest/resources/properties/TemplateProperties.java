package com.boot.web.starter.template.springboottemplate.rest.resources.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties("springboot.template.properties")
@RefreshScope
public class TemplateProperties {

	private String domain;
	private String env;
	private String desc;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("TemplateProperties{");
		sb.append("domain='").append(domain).append('\'');
		sb.append(", env='").append(env).append('\'');
		sb.append(", desc='").append(desc).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
