package org.promisepeople.ss.fthchck.service;

import org.promisepeople.ss.fthchck.dto.FaithActivityDTO;
import org.promisepeople.ss.fthchck.security.MemberAuthDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FaithActivityService {
	List<FaithActivityDTO> list(MemberAuthDetail mbrAuth);
}
