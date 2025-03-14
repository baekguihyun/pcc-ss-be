package org.promisepeople.ss.security.handler;

import com.google.gson.Gson;
import org.promisepeople.ss.fthchck.dto.resp.RespResult;
import org.promisepeople.ss.fthchck.security.MemberAuthDetail;
import org.promisepeople.ss.util.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    Authentication authentication) throws IOException, ServletException {
		log.info("------------------------------------------------");
		log.info(authentication);
		log.info("------------------------------------------------");

		MemberAuthDetail memberAuthDTO = (MemberAuthDetail) authentication.getPrincipal();

		Map<String, Object> claims = memberAuthDTO.generateClaims();

		String accessToken = JWTUtil.generateToken(claims, 10);
		String refreshToken = JWTUtil.generateToken(claims, 60*12);

		claims.put("accessToken", accessToken);
		claims.put("refreshToken", refreshToken);

		claims.remove("password");

		Gson gson = new Gson();

		String jsonStr = gson.toJson(
			RespResult.<Map<String, Object>>builder()
				.result(claims)
				.build());

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		PrintWriter printWriter = response.getWriter();
		printWriter.println(jsonStr);
		printWriter.close();
	}
}
