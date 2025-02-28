package org.promisepeople.ss.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.Map;

@Log4j2
public class JWTUtil {
	private static String key = "dG6pH*$%Nq68r8Y=Sjc7HJjgD$#N$^K46wPt6z?$*YBBMRy$Q2tAVCSRBm+GKPFP";

	public static String generateToken(Map<String, Object> valueMap, int min) {
		SecretKey key = null;

		try {
			key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		String jwtStr = Jwts.builder()
			.header().add(Map.of("typ", "JWT")).and()
			.claims().add(valueMap).and()
			.issuedAt(Date.from(ZonedDateTime.now().toInstant()))
			.expiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
			.signWith(key)
			.compact();

		return jwtStr;
	}

	public static Map<String, Object> validateToken(String token) {
		Map<String, Object> claim = null;

		try {
			SecretKey key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));

			claim = Jwts.parser()
						.verifyWith(key).build()
						.parseSignedClaims(token)
						.getBody();
		}
		catch (MalformedJwtException e) {
			throw new CustomJWTException("MalFormed");
		}
		catch (ExpiredJwtException e) {
			throw new CustomJWTException("Expired");
		}
		catch (InvalidClaimException e) {
			throw new CustomJWTException("Invalid");
		}
		catch (JwtException e) {
			throw new CustomJWTException("JWTError");
		}
		catch (Exception e) {
			throw new CustomJWTException("Error");
		}


		return claim;
	}

}
