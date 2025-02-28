package org.promisepeople.ss.fthchck.service;


import org.promisepeople.ss.fthchck.dto.SmallGroupDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SmallGroupService {

	SmallGroupDTO getByMbrStdntId(Long mbrId);
}
