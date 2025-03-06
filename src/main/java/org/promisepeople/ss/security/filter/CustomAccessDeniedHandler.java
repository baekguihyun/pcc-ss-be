package org.promisepeople.ss.security.filter;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.promisepeople.ss.exception.ApiException;
import org.promisepeople.ss.exception.ApiStatusEnum;
import org.promisepeople.ss.fthchck.dto.resp.ApiExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request,
	                   HttpServletResponse response,
	                   AccessDeniedException accessDeniedException) throws IOException, ServletException {
		Gson gson = new Gson();

		String msg = gson.toJson(ApiExceptionEntity
			.builder()
			.code(ApiStatusEnum.UNAUTHORIZED.getCode())
			.message(ApiStatusEnum.UNAUTHORIZED.getMessage())
			.messageEng(ApiStatusEnum.UNAUTHORIZED.getMessageEng())
			.detailMsg(accessDeniedException.getMessage())
			.build());

		response.setStatus(ApiStatusEnum.UNAUTHORIZED.getStatus().value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter printWriter = response.getWriter();
		printWriter.println(msg);
		printWriter.close();
	}
}
