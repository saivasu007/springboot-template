package com.boot.web.starter.template.springboottemplate.rest.services;

import com.boot.web.starter.template.springboottemplate.rest.models.TemplateInfo;
import com.boot.web.starter.template.springboottemplate.rest.models.Templates;
import com.boot.web.starter.template.springboottemplate.rest.services.exceptions.TemplateServiceRuntimeException;

public interface TemplateService {

	public Templates getAllTemplates();
	public TemplateInfo getInfo(String id);
	public TemplateInfo createInfo(TemplateInfo info) throws TemplateServiceRuntimeException;
	public TemplateInfo updateInfo(TemplateInfo info) throws TemplateServiceRuntimeException;
	public TemplateInfo deleteInfo(String id) throws TemplateServiceRuntimeException;
}
