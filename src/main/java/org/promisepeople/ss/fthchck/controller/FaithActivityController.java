package org.promisepeople.ss.fthchck.controller;


import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.promisepeople.ss.fthchck.dto.FaithActivityDTO;
import org.promisepeople.ss.fthchck.dto.resp.RespList;
import org.promisepeople.ss.fthchck.security.MemberAuthDetail;
import org.promisepeople.ss.fthchck.service.FaithActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fth-actv")
@RequiredArgsConstructor
public class FaithActivityController {

	private final FaithActivityService service;

	@GetMapping
	public ResponseEntity<RespList<FaithActivityDTO>> list(@AuthenticationPrincipal MemberAuthDetail mbrAuth) {
		List<FaithActivityDTO> result = service.list(mbrAuth);

		RespList<FaithActivityDTO> response = RespList.<FaithActivityDTO>builder()
			.result(result)
			.count(CollectionUtils.size(result)).build();

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}
}
