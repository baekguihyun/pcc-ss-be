package org.promisepeople.ss.fthchck.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.promisepeople.ss.fthchck.domain.SmallGroup;
import org.promisepeople.ss.fthchck.dto.SmallGroupDTO;
import org.promisepeople.ss.fthchck.repository.SmallGroupRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2

public class SmallGroupServiceImpl implements SmallGroupService{

	private final SmallGroupRepository repository;

	@Override
	public SmallGroupDTO getByMbrStdntId(Long mbrId) {
		SmallGroup smallGroup = repository.findFirstByMbrStdntId(mbrId);

		if (smallGroup == null) {
			return null;
		}

		SmallGroupDTO dto = new SmallGroupDTO();

		dto.set(smallGroup);

		return dto;
	}
}
