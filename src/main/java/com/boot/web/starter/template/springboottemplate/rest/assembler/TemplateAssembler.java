package com.boot.web.starter.template.springboottemplate.rest.assembler;

import com.boot.web.starter.template.springboottemplate.common.util.TransformUtil;
import com.boot.web.starter.template.springboottemplate.rest.models.TemplateInfo;
import com.boot.web.starter.template.springboottemplate.rest.resources.TemplateResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Assembler class to generate Response object including hypermedia link to Template resource
 *
 * @author sthungathurti
 */
@Component
public class TemplateAssembler extends JaxRsResourceAssemblerSupport<TemplateResource, TemplateInfo> {

	@Autowired
	TransformUtil transformUtil;

	public TemplateAssembler() {
		super(TemplateResource.class, TemplateInfo.class);
	}

	@Override
	public TemplateInfo toResource(TemplateResource templateResource) {
		TemplateInfo templateInfo = createResource(templateResource);
		TemplateInfo templateInfoResponse = transformUtil.getTemplateWithID(templateResource);
		templateInfoResponse.add(templateInfo.getLinks());
		return templateInfoResponse;
	}

	protected TemplateInfo createResource(TemplateResource templateResource ){
		return super.createResourceWithId(templateResource.getId(), templateResource);
	}

}
