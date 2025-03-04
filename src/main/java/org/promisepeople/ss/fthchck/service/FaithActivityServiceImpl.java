package org.promisepeople.ss.fthchck.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.promisepeople.ss.fthchck.domain.FaithActivity;
import org.promisepeople.ss.fthchck.dto.FaithActivityDTO;
import org.promisepeople.ss.fthchck.repository.FaithActivityRepository;
import org.promisepeople.ss.fthchck.security.MemberAuthDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class FaithActivityServiceImpl implements FaithActivityService {
	private final FaithActivityRepository activityRepository;

	@Override
	public List<FaithActivityDTO> list(MemberAuthDetail mbrAuth) {
		String mbrType = mbrAuth.getMbrType();

		List<FaithActivity> list = activityRepository.findByAuthority(mbrType);

		if (CollectionUtils.isEmpty(list)) {
			return List.of();
		}

		return list.stream().map(fa -> new FaithActivityDTO(fa)).toList();
	}

}
