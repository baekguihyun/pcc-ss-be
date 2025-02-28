package org.promisepeople.ss.security.handler;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.promisepeople.ss.exception.ApiStatusEnum;
import org.promisepeople.ss.fthchck.dto.resp.ApiExceptionEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
public class APILoginFailHandler implements AuthenticationFailureHandler  {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    AuthenticationException exception) throws IOException, ServletException {
		log.info("Login fail......" + exception);

		Gson gson = new Gson();

		String jsonStr = gson.toJson(ApiExceptionEntity.builder()
			.code(ApiStatusEnum.LOGIN_FAIL.getCode())
			.message(exception.getMessage())
			.messageEng(exception.getMessage())
			.build());

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter printWriter = response.getWriter();
		printWriter.println(jsonStr);

		printWriter.close();
	}
}
