package org.promisepeople.ss.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.promisepeople.ss.exception.ApiException;
import org.promisepeople.ss.fthchck.dto.resp.ApiExceptionEntity;
import org.promisepeople.ss.exception.ApiStatusEnum;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * 글로벌 API Exception 처리
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
 *
 * </pre>
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = Controller.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	
	/**
	 * 정의하지 않은 Runtime 예외 처리
	 * **/
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(
			HttpServletRequest request,
			HttpServletResponse response,
			final RuntimeException e)
	{
		String resultType = request.getParameter("resultType");
		boolean jsonType = StringUtils.equalsIgnoreCase(resultType, "xml") ? false : true;
		
		return ResponseEntity
				.status(ApiStatusEnum.SERVER_UNKNOWN_ERROR.getStatus())
				.header(HttpHeaders.CONTENT_TYPE, jsonType ? MediaType.APPLICATION_JSON_VALUE : MediaType.APPLICATION_XML_VALUE)
				.body(ApiExceptionEntity
						.builder()
						.code(ApiStatusEnum.SERVER_UNKNOWN_ERROR.getCode())
						.message(ApiStatusEnum.SERVER_UNKNOWN_ERROR.getMessage())
						.messageEng(ApiStatusEnum.SERVER_UNKNOWN_ERROR.getMessageEng())
						.build());
	}
	
	
	/**
	 * 정의된 Runtime 예외 처리
	 * **/
	@ExceptionHandler({ApiException.class})
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(
			HttpServletRequest request,
			HttpServletResponse response,
			final ApiException e) 
	{
		String resultType = request.getParameter("resultType");
		boolean jsonType = StringUtils.equalsIgnoreCase(resultType, "xml") ? false : true;
		
		return ResponseEntity
				.status(e.getError().getStatus())
				.header(HttpHeaders.CONTENT_TYPE, jsonType ? MediaType.APPLICATION_JSON_VALUE : MediaType.APPLICATION_XML_VALUE)
				.body(ApiExceptionEntity
						.builder()
						.code(e.getError().getCode())
						.message(e.getError().getMessage())
						.messageEng(e.getError().getMessageEng())
						.build());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatusCode status,
		WebRequest request) {
		String resultType = request.getParameter("resultType");
		boolean jsonType = StringUtils.equalsIgnoreCase(resultType, "xml") ? false : true;

		return ResponseEntity
			.status(ApiStatusEnum.NO_MANDATORY_REQUEST_PARAMETER_ERROR.getStatus())
			.header(HttpHeaders.CONTENT_TYPE, jsonType ? MediaType.APPLICATION_JSON_VALUE : MediaType.APPLICATION_XML_VALUE)
			.body(ApiExceptionEntity
				.builder()
				.code(ApiStatusEnum.NO_MANDATORY_REQUEST_PARAMETER_ERROR.getCode())
				.message(ApiStatusEnum.NO_MANDATORY_REQUEST_PARAMETER_ERROR.getMessage())
				.messageEng(ApiStatusEnum.NO_MANDATORY_REQUEST_PARAMETER_ERROR.getMessageEng())
				.build());
	}
	
	/**
	 * 기타 예외처리. 로그 확인 후 조치 필요
	 * **/
	@ExceptionHandler({Exception.class})
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(
			HttpServletRequest request,
			HttpServletResponse response,
			final Exception e)
	{
		String resultType = request.getParameter("resultType");
		boolean jsonType = StringUtils.equalsIgnoreCase(resultType, "xml") ? false : true;
	
		return ResponseEntity
				.status(ApiStatusEnum.SERVER_UNKNOWN_ERROR.getStatus())
				.header(HttpHeaders.CONTENT_TYPE, jsonType ? MediaType.APPLICATION_JSON_VALUE : MediaType.APPLICATION_XML_VALUE)
				.body(ApiExceptionEntity
						.builder()
						.code(ApiStatusEnum.SERVER_UNKNOWN_ERROR.getCode())
						.message(ApiStatusEnum.SERVER_UNKNOWN_ERROR.getMessage())
						.messageEng(ApiStatusEnum.SERVER_UNKNOWN_ERROR.getMessageEng())
						.build());
	}
}
