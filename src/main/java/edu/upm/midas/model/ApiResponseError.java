package edu.upm.midas.model;


import edu.upm.midas.enums.ApiErrorEnum;

public class ApiResponseError {
	
	private String errorCode;
	private String errorStatus;
	private String message;
	private String exception;
	private String errorDetail;
	
	public ApiResponseError(String returnCode, String returnStatus, String message, String exception, String errorSource){
		this.errorCode=returnCode;
		this.errorStatus=returnStatus;
		this.message=message;
		this.exception=exception;
		this.errorDetail=errorSource;
	}
	
	public ApiResponseError(ApiErrorEnum apiEnumError, String excepcion, String errorDetail){
		this.errorCode=apiEnumError.getClave();
		this.errorStatus=apiEnumError.name();
		this.message=apiEnumError.getDescripcion();
		this.exception=excepcion;
		this.errorDetail=errorDetail;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
		
}
