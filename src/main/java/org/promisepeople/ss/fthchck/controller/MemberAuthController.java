package org.promisepeople.ss.fthchck.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.promisepeople.ss.fthchck.dto.MbrSignUpDTO;
import org.promisepeople.ss.fthchck.dto.MbrSearchDTO;
import org.promisepeople.ss.fthchck.dto.resp.RespList;
import org.promisepeople.ss.fthchck.dto.resp.RespResult;
import org.promisepeople.ss.fthchck.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberAuthController {

	private final MemberService memberService;

	@PostMapping("/search-member")
	public ResponseEntity<RespList<MbrSearchDTO>> searchMember(@RequestParam("searchWord") String searchWord) {
		List<MbrSearchDTO> result = memberService.searchMemberWithoutSignUp(searchWord);

		RespList<MbrSearchDTO> response = RespList.<MbrSearchDTO>builder()
			.result(result)
			.count(CollectionUtils.size(result)).build();

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

	@PostMapping("/validate-member")
	public ResponseEntity<RespResult<Boolean>> validateMember(@RequestBody MbrSearchDTO dto) {
		boolean validate = memberService.validateMemberWithBrdt(NumberUtils.toLong(dto.getMbrId()), dto.getBrdt());

		RespResult<Boolean> response = RespResult.<Boolean>builder()
			.result(validate)
			.build();

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

	@PostMapping("/check-dpcn-username")
	public ResponseEntity<RespResult<Boolean>> checkDpcnUsername(@RequestParam("username") String username) {
		boolean check = memberService.checkDpcnUsername(username);

		RespResult<Boolean> response = RespResult.<Boolean>builder()
			.result(check)
			.build();

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

	@PutMapping("/sign-up")
	public ResponseEntity<RespResult<Boolean>> signUp(@RequestBody MbrSignUpDTO memberAuth) {
		boolean result = memberService.signUp(memberAuth);

		RespResult<Boolean> response = RespResult.<Boolean>builder()
			.result(result)
			.build();

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

}
