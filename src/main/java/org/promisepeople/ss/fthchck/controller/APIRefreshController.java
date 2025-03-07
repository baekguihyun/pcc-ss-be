package org.promisepeople.ss.fthchck.controller;

import org.apache.commons.lang3.StringUtils;
import org.promisepeople.ss.exception.ApiException;
import org.promisepeople.ss.exception.ApiStatusEnum;
import org.promisepeople.ss.util.CustomJWTException;
import org.promisepeople.ss.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
public class APIRefreshController {
	@RequestMapping("/api/auth/refresh")
	public Map<String, Object> refresh(
		@RequestHeader("Authorization") String authHeader, @RequestParam String refreshToken) {

		if (StringUtils.isBlank(refreshToken) ||
			(StringUtils.isBlank(authHeader) || authHeader.length() < 7)) {
			throw new ApiException(ApiStatusEnum.INVALID_REQUEST_PARAMETER_ERROR);
		}

		String accessToken = authHeader.substring(7);

		// Access 토큰이 만료되었다면
		if (checkExpiredToken(accessToken)) {
			// Refresh 토큰 검증
			Map<String, Object> claims;

			try {
				claims = JWTUtil.validateToken(refreshToken);
			}
			catch (CustomJWTException e) {
				throw new ApiException(ApiStatusEnum.UNAUTHORIZED);
			}

			log.info("refresh ... claims: " + claims);

			String newAccessToken = JWTUtil.generateToken(claims, 10);
			String newRefreshToken = checkTime((Long) claims.get("exp")) == true ?
				JWTUtil.generateToken(claims, 60 * 24) : refreshToken;

			return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);
		}
		else {
			return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
		}
	}

	private boolean checkTime(Long exp) {
		//JWT exp를 날짜료 변환
		java.util.Date expDate = new java.util.Date((long) exp * (1000));

		//현재 시간과 차이 계산 - 밀리세컨즈
		long gap = expDate.getTime() - System.currentTimeMillis();

		//분단위 계산
		long leftMin = gap / (1000 * 60);

		//1시간도 안남았는지..
		return leftMin < 60;
	}

	private boolean checkExpiredToken(String token) {
		try {
			JWTUtil.validateToken(token);
		}
		catch (CustomJWTException ex) {
			if (ex.getMessage().equals("Expired")) {
				return true;
			}
		}

		return false;
	}
}
