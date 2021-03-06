package com.boot.web.starter.template.springboottemplate.rest.services.exceptions;


/**
 * Base exception for the {@link TemplateService}.
 *
 * @author sthungathurti
 */
public class TemplateServiceRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs exception without message or cause.
	 */
	public TemplateServiceRuntimeException() {
		super();
	}

	/**
	 * Construct with a message {@code String} that is returned by the inherited
	 * {@code Throwable.getMessage}.
	 *
	 * @param message the message that is returned by the inherited
	 *                {@code Throwable.getMessage}
	 */
	public TemplateServiceRuntimeException(String message) {
		super(message);
	}

	/**
	 * Construct with both a {@code String} message and a {@code Throwable}
	 * cause. The {@code message} is returned by the inherited
	 * {@code Throwable.getMessage}. The cause that is returned by the inherited
	 * {@code Throwable.getCause}.
	 *
	 * @param message the message that is returned by the inherited
	 *                {@code Throwable.getMessage}
	 * @param cause   the cause that is returned by the inherited
	 *                {@code Throwable.getCause}
	 */
	public TemplateServiceRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
