package com.boot.web.starter.template.springboottemplate.rest.services;

import com.boot.web.starter.template.springboottemplate.rest.assembler.TemplateAssembler;
import com.boot.web.starter.template.springboottemplate.rest.models.TemplateInfo;
import com.boot.web.starter.template.springboottemplate.rest.models.Templates;
import com.boot.web.starter.template.springboottemplate.rest.services.exceptions.TemplateAlreadyExistsException;
import com.boot.web.starter.template.springboottemplate.rest.services.exceptions.TemplateServiceRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	TemplateAssembler templateAssembler;
	List<TemplateInfo> templates = new ArrayList<>();

	@Override
	public Templates getAllTemplates() {
		Templates tmplts = new Templates();
		tmplts.setTemplates(templates);
		return tmplts;
	}

	@Override
	public TemplateInfo getInfo(String id) {
		for(TemplateInfo info : templates) {
			if(info.getTempId().equals(id)) return info;
		}
		return null;
	}

	@Override
	public TemplateInfo createInfo(TemplateInfo info) throws TemplateServiceRuntimeException {
		for(TemplateInfo tempInfo : templates) {
			if(tempInfo.getTempId().equals(info.getTempId())) throw new TemplateAlreadyExistsException("Template ID : "+info.getTempId()+" Already Exists.");
		}
		templates.add(info);
		return getInfo(info.getTempId());
	}

	@Override
	public TemplateInfo updateInfo(TemplateInfo template) throws TemplateServiceRuntimeException {
		for(TemplateInfo info : templates) {
			if(info.getTempId().equals(template.getTempId())) {
				info.setInfo(template.getInfo());
				info.setName(template.getName());
				return getInfo(template.getTempId());
			}
		}
		return null;
	}

	@Override
	public TemplateInfo deleteInfo(String id) throws TemplateServiceRuntimeException {
		TemplateInfo delInfo = getInfo(id);
		for(TemplateInfo info : templates) {
			if(info.getTempId().equals(id)) {
				templates.remove(info);
				return delInfo;
			}
		}
		return new TemplateInfo();
	}
}
