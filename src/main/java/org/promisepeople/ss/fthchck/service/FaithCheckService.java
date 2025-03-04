package org.promisepeople.ss.fthchck.service;

import org.promisepeople.ss.fthchck.dto.FaithCheckDTO;
import org.promisepeople.ss.fthchck.security.MemberAuthDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FaithCheckService{
	List<FaithCheckDTO> getCheckList(MemberAuthDetail mbrAuth, String fthChckYmd);

	List<FaithCheckDTO> updateFaithCheck(MemberAuthDetail mbrAuth, String fthChckYmd, List<FaithCheckDTO> checkList);
}
