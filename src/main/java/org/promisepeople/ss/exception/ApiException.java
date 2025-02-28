package org.promisepeople.ss.exception;

public class ApiException extends RuntimeException {

	private ApiStatusEnum error;
	
	public ApiException(ApiStatusEnum error) {
		super(error.getMessageEng());
		this.error = error;
	}
	
	public ApiStatusEnum getError() {
		return error;
	}
}
