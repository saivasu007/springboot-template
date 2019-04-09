package com.boot.web.starter.template.springboottemplate.rest.assembler;

import jersey.repackaged.com.google.common.base.Preconditions;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Abstract assembler to generate hypermedia links to the resources
 *
 * @author sthungathurti
 */
public abstract class JaxRsResourceAssemblerSupport<T, D extends ResourceSupport>
		extends ResourceAssemblerSupport<T, D> {
	private final Class<?> controllerClass;

	public JaxRsResourceAssemblerSupport(Class<?> controllerClass, Class<D> resourceType) {

		super(controllerClass, resourceType);
		this.controllerClass = controllerClass;
	}

	@Override
	protected D createResourceWithId(Object id, T entity, Object... parameters) {
		Preconditions.checkNotNull(entity);
		Preconditions.checkNotNull(id);
		D instance = getResourceInstance(entity);
		JaxRsLinkBuilder jaxRsLinkBuilder = getJaxRsLinkBuilder();
		instance.add(jaxRsLinkBuilder.slash(id).withSelfRel());
		return instance;
	}

	protected JaxRsLinkBuilder getJaxRsLinkBuilder() {
		return JaxRsLinkBuilder.linkTo(controllerClass);
	}

	protected D getResourceInstance(T entity) {
		return super.instantiateResource(entity);
	}
}
