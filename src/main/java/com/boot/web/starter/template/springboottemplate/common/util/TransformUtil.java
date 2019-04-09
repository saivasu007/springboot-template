package com.boot.web.starter.template.springboottemplate.common.util;

import com.boot.web.starter.template.springboottemplate.rest.models.TemplateInfo;
import com.boot.web.starter.template.springboottemplate.rest.resources.TemplateResource;
import org.springframework.stereotype.Component;

@Component
public class TransformUtil {

	/**
	 * Transforms {@link TemplateResource} to {@link TemplateInfo}
	 *
	 * @param {@link TemplateResource} Object
	 * @return {@link TemplateInfo} Object
	 */
	public TemplateInfo getTemplateWithID(TemplateResource templateResource) {
		TemplateInfo templateInfo = null;
		if (templateResource == null || templateResource.getId() == null) {
			throw new NullPointerException("Null custom template resource.");
		}
		templateInfo = new TemplateInfo();
		templateInfo.setTempId(templateResource.getId().toString());
		return templateInfo;
	}
}
