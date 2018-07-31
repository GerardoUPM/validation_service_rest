package edu.upm.midas.enums;

import org.apache.commons.lang.StringUtils;

public enum ApiErrorEnum {

	INVALID_PARAMETERS("400", "A required parameter for this API operation is invalid or has not been provided"),
	UNAUTHORIZED("401", "The username and password credentials are missing or invalid for the given request."),
	RESOURCE_NOT_FOUND("404", "Could not find resource."),
	INTERNAL_SERVER_ERROR("500", "An unknown Api exception was thrown");

	private String clave;
	private String descripcion;

	private ApiErrorEnum(String clave, String descripcion) {
		this.clave = clave;
		this.descripcion = descripcion;
	}
	
	public static ApiErrorEnum getEnum(String clave) {
		if (StringUtils.isNotBlank(clave)) {
	        for (ApiErrorEnum estatus : ApiErrorEnum.values()) {
	            if (clave.equals(estatus.getClave())) 
	                return estatus;
	        }
        }
		return null;
	}
	
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
