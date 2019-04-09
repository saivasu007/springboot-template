package com.boot.web.starter.template.springboottemplate.rest.models;

import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Model object for Template service
 *
 * @author sthungathurti
 */
public class Templates extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 962497687514167703L;

	private List<TemplateInfo> templates;

	public List<TemplateInfo> getTemplates() {
		if (templates == null) {
			return new ArrayList<>();
		}
		return templates;
	}

	public void setTemplates(List<TemplateInfo> templatesList) {
		this.templates = templatesList;
	}

	@Override
	public String toString() {
		return "Templates [templatesList=" + templates + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Templates that = (Templates) o;
		return Objects.equals(templates, that.templates);
	}

	@Override
	public int hashCode() {

		return Objects.hash(templates);
	}


}
