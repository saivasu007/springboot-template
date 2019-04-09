package com.boot.web.starter.template.springboottemplate.rest.resources;

import com.boot.web.starter.template.springboottemplate.rest.models.TemplateInfo;
import com.boot.web.starter.template.springboottemplate.rest.models.Templates;
import com.boot.web.starter.template.springboottemplate.rest.services.TemplateService;
import com.boot.web.starter.template.springboottemplate.rest.services.exceptions.TemplateNotFoundException;
import com.boot.web.starter.template.springboottemplate.rest.services.exceptions.TemplateServiceRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static com.boot.web.starter.template.springboottemplate.rest.resources.util.ApiErrorCodes.API_RESP_MSG_BAD_REQUEST;
import static com.boot.web.starter.template.springboottemplate.rest.resources.util.ApiErrorCodes.API_RESP_MSG_INTERNAL_SERVER_ERROR;
import static com.boot.web.starter.template.springboottemplate.rest.resources.util.ApiErrorCodes.API_RESP_MSG_NOT_FOUND;
import static com.boot.web.starter.template.springboottemplate.rest.resources.util.ApiErrorCodes.API_RESP_MSG_UNAUTHORIZED;

/**
 * V1 API Resources.
 * <p>
 * Get Info	GET	 /info
 */
@RestController
@RequestMapping("/v1/template")
@Api(tags = { "Template Information" })
/*
@SwaggerDefinition(info = @Info(title = "Springboot REST API",
								description = "Springboot REST Service API.",
								termsOfService = "Terms of service",
								contact = @Contact(name = "Srinivas Thungathurti", url = "www.srinivast.com", email = "srinivas_thungathurti@intuit.com"),
								license = @License(name = "License of API",
												   url = "API license URL"),
								version = "1.0"))
*/
public class TemplateResource extends ResourceSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(TemplateResource.class);

	@Context
	private HttpServletRequest httpRequest;

	@Context
	private HttpServletResponse httpResponse;

	@Resource
	private TemplateService templateService;

	/**
	 * This API will get the information for given id.
	 *
	 * @param id The unique identifier for the Info resource.
	 * @return the Info
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/info")
	@ApiOperation(value = "Information",
				  response = Templates.class,
				  notes = "Get all the template information")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "API-0200"),
							@ApiResponse(code = 400, message = API_RESP_MSG_BAD_REQUEST),
							@ApiResponse(code = 401, message = API_RESP_MSG_UNAUTHORIZED),
							@ApiResponse(code = 404, message = API_RESP_MSG_NOT_FOUND),
							@ApiResponse(code = 500, message = API_RESP_MSG_INTERNAL_SERVER_ERROR)})
	public Templates getAllTemplates() {
		LOGGER.info("TemplateResource :: getAllTemplates");
		Templates templates = templateService.getAllTemplates();
		LOGGER.info("TemplateResource :: Template information is : "+templates);
		return templates;
	}

	/**
	 * This API will get the information for given id.
	 *
	 * @param id The unique identifier for the Info resource.
	 * @return the Info
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/info/{id}")
	@ApiOperation(value = "Information",
				  response = TemplateInfo.class,
				  notes = "Get the template information"
				  )
	@ApiResponses(value = { @ApiResponse(code = 200, message = "API-0200"),
							@ApiResponse(code = 400, message = API_RESP_MSG_BAD_REQUEST),
							@ApiResponse(code = 401, message = API_RESP_MSG_UNAUTHORIZED),
							@ApiResponse(code = 404, message = API_RESP_MSG_NOT_FOUND),
							@ApiResponse(code = 500, message = API_RESP_MSG_INTERNAL_SERVER_ERROR)})
	public TemplateInfo getInfoByID(
			@PathVariable("id") @ApiParam(value = "Template ID", required = true, example = "123") String id) {
		LOGGER.info("TemplateResource :: info endpoint information and id is : "+id);
		TemplateInfo info = templateService.getInfo(id);
		if(!Optional.ofNullable(info).isPresent()) throw new TemplateNotFoundException("Requested template id : "+id+" does not exists.");
		Link selfLink = ControllerLinkBuilder.linkTo(TemplateResource.class).slash("info").slash(info.getTempId()).withSelfRel();
		info.getLinks().clear();
		info.getLinks().add(selfLink);
		setHttpLocationHeader(id);
		LOGGER.info("TemplateResource :: info endpoint information is : "+info);
		return (TemplateInfo)Response.ok().entity(info).build().getEntity();
	}

	/**
	 * This API will create the information for template.
	 *
	 * @param message String template info for the new template.
	 * @return the template info
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/info/{id}/{message}")
	@ApiOperation(value = "Information",
				  response = TemplateInfo.class,
				  notes = "Set the template information"
	)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "API-0200"),
							@ApiResponse(code = 400, message = API_RESP_MSG_BAD_REQUEST),
							@ApiResponse(code = 401, message = API_RESP_MSG_UNAUTHORIZED),
							@ApiResponse(code = 404, message = API_RESP_MSG_NOT_FOUND),
							@ApiResponse(code = 500, message = API_RESP_MSG_INTERNAL_SERVER_ERROR)})
	public TemplateInfo createTempInfo(
			@PathVariable("id") @ApiParam(value = "Template ID", required = true, example = "123") String id,
			@PathVariable("message") @ApiParam(value = "Template Info", required = true, example = "Debug Template") String message) {
		LOGGER.info("TemplateResource :: info endpoint information and Info is : "+message);
		TemplateInfo info = new TemplateInfo();
		try {
			info.setTempId(id);
			info.setName(message);
			info.setInfo("Msg:"+message);
			info.setVersion("1.0");
			info = templateService.createInfo(info);
			Link selfLink = ControllerLinkBuilder.linkTo(TemplateResource.class).slash("info").slash(info.getTempId()).withSelfRel();
			info.getLinks().clear();
			info.getLinks().add(selfLink);
			setHttpLocationHeader(id);
			LOGGER.info("TemplateResource :: info endpoint information is : " + info);
		} catch(Exception ex) {
			LOGGER.error("ERROR While Creating Template Resource.");
			LOGGER.error(ExceptionUtils.getStackTrace(ex));
			throw new TemplateServiceRuntimeException(ex.getMessage());
		}
		return (TemplateInfo) Response.ok().entity(info).build().getEntity();
	}

	/**
	 * This API will create the information for template.
	 *
	 * @param message String template info for the new template.
	 * @return the template info
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/info/{id}/{message}")
	@ApiOperation(value = "Information",
				  response = TemplateInfo.class,
				  notes = "Update the template information"
	)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "API-0200"),
							@ApiResponse(code = 400, message = API_RESP_MSG_BAD_REQUEST),
							@ApiResponse(code = 401, message = API_RESP_MSG_UNAUTHORIZED),
							@ApiResponse(code = 404, message = API_RESP_MSG_NOT_FOUND),
							@ApiResponse(code = 500, message = API_RESP_MSG_INTERNAL_SERVER_ERROR)})
	public TemplateInfo updateTempInfo(
			@PathVariable("id") @ApiParam(value = "Template ID", required = true, example = "123") String id,
			@PathVariable("message") @ApiParam(value = "Template Info", required = true, example = "Debug Template") String message) {
		LOGGER.info("TemplateResource :: info endpoint information and id & Info is : "+id+" "+message);
		TemplateInfo info = new TemplateInfo();
		try {
			info.setTempId(id);
			info.setName(message);
			info.setInfo("Msg:"+message);
			info = templateService.updateInfo(info);
			Link selfLink = ControllerLinkBuilder.linkTo(TemplateResource.class).slash("info").slash(info.getTempId()).withSelfRel();
			info.getLinks().clear();
			info.getLinks().add(selfLink);
			setHttpLocationHeader(id);
			LOGGER.info("TemplateResource :: info endpoint information is : " + info);
		}catch(Exception ex) {
			LOGGER.error("ERROR While Updating Template Resource.");
			LOGGER.error(ExceptionUtils.getStackTrace(ex));
			throw new TemplateServiceRuntimeException(ex.getMessage());
		}
		return (TemplateInfo) Response.ok().entity(info).build().getEntity();
	}

	/**
	 * This API will delete the information for template.
	 *
	 * @param message String template info for the new template.
	 * @return the template info
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/info/{id}")
	@ApiOperation(value = "Information",
				  response = TemplateInfo.class,
				  notes = "Delete the template information"
	)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "API-0200"),
							@ApiResponse(code = 400, message = API_RESP_MSG_BAD_REQUEST),
							@ApiResponse(code = 401, message = API_RESP_MSG_UNAUTHORIZED),
							@ApiResponse(code = 404, message = API_RESP_MSG_NOT_FOUND),
							@ApiResponse(code = 500, message = API_RESP_MSG_INTERNAL_SERVER_ERROR)})
	public TemplateInfo deleteTempInfo(
			@PathVariable("id") @ApiParam(value = "Template ID", required = true, example = "123") String id) {
		LOGGER.info("TemplateResource :: info endpoint information and id is : "+id);
		TemplateInfo info;
		try {
			info = templateService.deleteInfo(id);
			LOGGER.info("TemplateResource :: info endpoint information is : "+info);
		}catch(Exception ex) {
			LOGGER.error("ERROR While Deleting Template Resource.");
			LOGGER.error(ExceptionUtils.getStackTrace(ex));
			throw new TemplateServiceRuntimeException(ex.getMessage());
		}
		return (TemplateInfo)Response.ok().entity(info).build().getEntity();
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	private void setHttpLocationHeader(String name) {
		/*
		String location = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString() +
									 "/" + name;
		*/
		/**
		String location = StringUtils.stripEnd(httpRequest.getRequestURI(), "/ ") +
						  "/" + name;
		httpResponse.addHeader(HttpHeaders.LOCATION, location);
		 */
	}

}