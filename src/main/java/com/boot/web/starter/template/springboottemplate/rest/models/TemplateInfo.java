package com.boot.web.starter.template.springboottemplate.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id","info"})
@ApiModel
public class TemplateInfo extends ResourceSupport implements Serializable {

	private static final long serialVersionID = 1L;

	@JsonProperty("id")
	@ApiModelProperty(notes = "template id", value = "tempId", required = true)
	private String tempId;
	@ApiModelProperty(notes = "template Info", value = "tempInfo")
	@JsonProperty("info")
	private String info;
	@ApiModelProperty(notes = "template name", value = "tempName")
	@JsonProperty("name")
	private String name;
	@ApiModelProperty(notes = "template version", value = "tempVer")
	@JsonProperty("version")
	private String version;

	public TemplateInfo() {
	}

	public TemplateInfo(String id, String info, String name, String version) {
		this.tempId = id;
		this.info = info;
		this.name = name;
		this.version = version;
	}

	@JsonProperty("id")
	public String getTempId() {
		return tempId;
	}

	@JsonProperty("info")
	public String getInfo() {
		return info;
	}

	@JsonProperty("id")
	public void setTempId(String id) {
		this.tempId = id;
	}

	@JsonProperty("info")
	public void setInfo(String info) {
		this.info = info;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		TemplateInfo that = (TemplateInfo) o;
		return Objects.equals(tempId, that.tempId) && Objects.equals(info, that.info) && Objects.equals(name,
																										that.name)
			   && Objects.equals(version, that.version);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), tempId, info, name, version);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("TemplateInfo{");
		sb.append("tempId='").append(tempId).append('\'');
		sb.append(", info='").append(info).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append(", version='").append(version).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
