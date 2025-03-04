package org.promisepeople.ss.exception;

import lombok.Builder;


public class ApiException extends RuntimeException {

	private ApiStatusEnum error;
	
	public ApiException(ApiStatusEnum error) {
		super(error.getMessageEng());
		this.error = error;
	}

	public ApiException(ApiStatusEnum error, Exception ex) {
		super(error.getMessageEng(), ex);

		this.error = error;
	}

	public ApiStatusEnum getError() {
		return error;
	}
}
