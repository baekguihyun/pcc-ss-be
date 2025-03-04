package org.promisepeople.ss.fthchck.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.promisepeople.ss.fthchck.dto.FaithCheckDTO;
import org.promisepeople.ss.fthchck.dto.resp.RespList;
import org.promisepeople.ss.fthchck.security.MemberAuthDetail;
import org.promisepeople.ss.fthchck.service.FaithCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fth-chck")
@RequiredArgsConstructor
public class FaithCheckController {
	private final FaithCheckService service;

	@GetMapping("/{fthChckYmd}")
	public ResponseEntity<RespList<FaithCheckDTO>> getCheckList(@AuthenticationPrincipal MemberAuthDetail mbrAuth,
	                                                            @PathVariable("fthChckYmd") String fthChckYmd) {
		List<FaithCheckDTO> result = service.getCheckList(mbrAuth, fthChckYmd);

		RespList<FaithCheckDTO> response = RespList.<FaithCheckDTO>builder()
			.result(result)
			.count(CollectionUtils.size(result)).build();

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

	@PutMapping("/{fthChckYmd}")
	public ResponseEntity<RespList<FaithCheckDTO>> putCheckList(@AuthenticationPrincipal MemberAuthDetail mbrAuth,
	                                                            @PathVariable("fthChckYmd") String fthChckYmd,
	                                                            @RequestBody List<FaithCheckDTO> checkList) {
		List<FaithCheckDTO> result = service.updateFaithCheck(mbrAuth, fthChckYmd, checkList);

		RespList<FaithCheckDTO> response = RespList.<FaithCheckDTO>builder()
			.result(result)
			.count(CollectionUtils.size(result)).build();

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

}
