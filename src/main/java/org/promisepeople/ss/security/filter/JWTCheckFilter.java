package org.promisepeople.ss.security.filter;

import com.google.gson.Gson;
import org.promisepeople.ss.exception.ApiException;
import org.promisepeople.ss.exception.ApiStatusEnum;
import org.promisepeople.ss.fthchck.dto.resp.ApiExceptionEntity;
import org.promisepeople.ss.fthchck.security.MemberAuthDetail;
import org.promisepeople.ss.fthchck.security.MemberRole;
import org.promisepeople.ss.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.promisepeople.ss.util.MemberUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		// Preflight요청은 체크하지 않음
		if (request.getMethod().equals("OPTIONS")) {
			return true;
		}

		String path = request.getRequestURI();

		log.info("check url.........." + path);

		/* /api/auth/ 경로의 호출은 체크하지 않음 */
		if (path.startsWith("/api/auth/")) {
			return true;
		}

		/* 이미지 조회 경로는 체크하지 않는다면 */
		if (path.startsWith("/api/products/view/")) {
			return true;
		}

		return false;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException, IOException {
		log.info("-------------------------------- JWTCheckFilter..........");

		String authHeaderStr = request.getHeader("Authorization");

		try {
			//Bearer accesstoken...
			String accessToken = authHeaderStr.substring(7);
			Map<String, Object> claims = JWTUtil.validateToken(accessToken);

			log.info("JWT claims: " + claims);

			String username = (String) claims.get("username");
			String password = (String) claims.get("password");

			MemberAuthDetail memberAuthDTO = new MemberAuthDetail(
				username, password, MemberRole.valueOfType((String) claims.get("mbrType")));

			memberAuthDTO.setMbrId((Long) claims.get("mbrId"));

			log.info("--------------------------------");
			log.info(memberAuthDTO);
			log.info(memberAuthDTO.getAuthorities());

			UsernamePasswordAuthenticationToken authenticationToken
				= new UsernamePasswordAuthenticationToken(memberAuthDTO, password, memberAuthDTO.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			filterChain.doFilter(request, response);
		}
		catch (Exception e) {
			log.error("JWT Check Error..........");
			log.error(e.getMessage(), e);

			Gson gson = new Gson();
			String msg = gson.toJson(ApiExceptionEntity
				.builder()
					.code(ApiStatusEnum.FAILED_CHECK_JWT.getCode())
					.message(ApiStatusEnum.FAILED_CHECK_JWT.getMessage())
					.messageEng(ApiStatusEnum.FAILED_CHECK_JWT.getMessageEng())
					.detailMsg(e.getMessage())
				.build());

			response.setContentType("application/json");

			PrintWriter printWriter = response.getWriter();
			printWriter.println(msg);
			printWriter.close();
		}
	}
}
