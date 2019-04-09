package com.boot.web.starter.template.springboottemplate.rest.validators;

import com.boot.web.starter.template.springboottemplate.rest.models.TemplateInfo;
import org.apache.commons.lang3.Validate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.boot.web.starter.template.springboottemplate.rest.services.exceptions.TemplateServiceRuntimeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Aspect for all service request methods to verify if request has valid fields
 *
 * @author sthungathurti
 */
@Aspect
@Component
public class TemplateRequestVerifier {
	private static final Logger LOGGER = LoggerFactory.getLogger(TemplateRequestVerifier.class);
	private static final String SERVICE_METHOD_CREATE_TEMPLATE = "createInfo";
	private static final String SERVICE_METHOD_UPDATE_TEMPLATE = "updateInfo";
	private static final Pattern VALID_TEMPLATE_NAME_PATTERN = Pattern.compile("[a-z0-9-]+");

	/**
	 * Verify all service requests. Aspect runs before all service request methods.
	 *
	 * @param joinPoint joinPoint
	 * @throws TemplateServiceRuntimeException templateServiceRuntimeException
	 */
	@Before("execution(* com.boot.web.starter.template.springboottemplate.rest.services.TemplateServiceImpl.createInfo" +
			"(..)) ")
	public void verifyTemplateRequest(JoinPoint joinPoint) throws TemplateServiceRuntimeException {
		String serviceMethodName = joinPoint.getSignature().getName();
		switch (serviceMethodName) {
			case SERVICE_METHOD_CREATE_TEMPLATE:
			case SERVICE_METHOD_UPDATE_TEMPLATE:
				verifyUpsertTemplate(joinPoint);
				break;
			default:
				throw new IllegalArgumentException("No validation for service method: " + serviceMethodName);
		}
	}

	/**
	 * verify create and update request.
	 *
	 * @param joinPoint joinPoint
	 * @throws TemplateServiceRuntimeException templateServiceRuntimeException
	 */
	private void verifyUpsertTemplate(JoinPoint joinPoint) throws TemplateServiceRuntimeException {
		TemplateInfo templateInfo = (TemplateInfo) joinPoint.getArgs()[0];
		try {
			Validate.notNull(templateInfo, "Template object is null!");
			String templateName = templateInfo.getName();
			Validate.notBlank(templateName,
							  "Templates 'name' parameter must have non-empty value; templateInfo="
							  + templateInfo);
			Validate.notBlank(templateInfo.getTempId(),
							  "templateInfo 'id' parameter must have non-empty value; templateInfo="
							  + templateInfo);

			// only lowercase and hyphen in templates name
			if (!isValidByPattern(templateName, VALID_TEMPLATE_NAME_PATTERN)) {
				LOGGER.error(
						"Templates 'name' parameter must have lowercase and hyphen; templateName = "
						+ templateName);
				throw new IllegalArgumentException(
						"templateInfo 'name' parameter must have lowercase and hyphen, "
						+ "templateName = " + templateName);
			}
		} catch (IllegalArgumentException | NullPointerException ex) {
			throw new TemplateServiceRuntimeException(ex.getMessage(), ex);
		}
	}

	/**
	 * check if target string has valid pattern
	 *
	 * @param targetStr
	 * @param validPattern
	 * @return
	 */
	private boolean isValidByPattern(String targetStr, Pattern validPattern) {
		Matcher matcher = validPattern.matcher(targetStr);
		return matcher.matches();
	}
}
