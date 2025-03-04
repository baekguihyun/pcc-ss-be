package org.promisepeople.ss.exception;

import org.springframework.http.HttpStatus;

/**
 * 공공데이터포털 Open API 응답 코드 및 메시지 정의
 * @author KITC컨소시엄 김기형
 * @since 2023.10.10
 * @version 0.1
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2023.10.10  김기형          최초 생성
 *   2023.10.12  김기형			 포털 제공을 위해 HttpStatus 200으로 전체 변경
 *
 * </pre>
 */


public enum ApiStatusEnum {

	/**
	 * 정상(CODE 0)
	 */
	NORMAL_CODE(HttpStatus.OK, "0", "정상", "NORMAL_CODE"),

	/**
	 * 애플리케이션 에러(CODE 1)
	 */
	APPLICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "1", "애플리케이션 에러", "APPLICATION_ERROR"),

	/**
	 * 데이터베이스 에러(CODE 2)
	 */
	DB_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "2", "데이터베이스 에러", "DB_ERROR"),

	/**
	 * 데이터없음 에러(CODE 3)
	 */
	NODATA_ERROR(HttpStatus.NOT_FOUND, "3", "데이터없음 에러", "NODATA_ERROR"),

	/**
	 * 서비스 연결실패 에러(CODE 5)
	 */
	SERVICETIMEOUT_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "5", "서비스 연결실패 에러", "SERVICETIMEOUT_ERROR"),

	/**
	 * 서버 에러(CODE 6)
	 */
	SERVER_UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "6","서버에러", "SERVER_UNKNOWN_ERROR"),

	/**
	 * 로그인 실패 (CODE 7)
	 */
	LOGIN_FAIL(HttpStatus.OK, "7","로그인 실패", "LOGIN_FAIL_ERROR"),

	/**
	 * 권한 없음 (CODE 8)
	 */
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "8","권한 없음", "UNAUTHORIZED"),

	/**
	 * 권한 없음 (CODE 9)
	 */
	FAILED_CHECK_JWT(HttpStatus.OK, "9","JWT 검사 실패", "FAILED_CHECK_JWT"),

	/**
	 * 잘못된 요청 파라미터 에러(CODE 10)
	 */
	INVALID_REQUEST_PARAMETER_ERROR(HttpStatus.BAD_REQUEST, "10", "잘못된 요청 파라미터 에러", "INVALID_REQUEST_PARAMETEER_ERROR"),
	/**
	 * 필수요청 파라미터가 없음(CODE 11)
	 */
	NO_MANDATORY_REQUEST_PARAMETER_ERROR(HttpStatus.BAD_REQUEST, "11", "필수요청 파라미터가 없음","NO_MANDATORY_REQUEST_PARAMETER_ERROR");
	
	private final HttpStatus status;
	private final String code;
	private String message;
	private String messageEng;
	
	ApiStatusEnum(HttpStatus status, String code) {
		this.status = status;
		this.code = code;
	}
	
	ApiStatusEnum(HttpStatus status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
	
	ApiStatusEnum(HttpStatus status, String code, String message, String messageEng) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.messageEng = messageEng;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public String getMessageEng() {
		return messageEng;
	}

	public String getMessage() {
		return message;
	}
}
