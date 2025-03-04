package org.promisepeople.ss.fthchck.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.promisepeople.ss.fthchck.domain.FaithCheck;
import org.promisepeople.ss.fthchck.dto.FaithActivityDTO;
import org.promisepeople.ss.fthchck.dto.FaithCheckDTO;
import org.promisepeople.ss.fthchck.repository.FaithCheckRepository;
import org.promisepeople.ss.fthchck.security.MemberAuthDetail;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class FaithCheckServiceImpl implements FaithCheckService {
	private final FaithCheckRepository repository;
	private final FaithActivityService faService;
	private final FaithCheckRepository faithCheckRepository;

	@Override
	public List<FaithCheckDTO> getCheckList(MemberAuthDetail mbrAuth, String fthChckYmd) {
		List<FaithActivityDTO> listActivity = faService.list(mbrAuth);

		Map<String, FaithCheckDTO> map = new LinkedHashMap<>();

		for (FaithActivityDTO faDTO : listActivity) {
			map.put(faDTO.getFthActvCd(), new FaithCheckDTO(faDTO));
		}

		if (StringUtils.isBlank(fthChckYmd)) {
			LocalDate today = LocalDate.now();

			fthChckYmd = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		}

		Long mbrId = mbrAuth.getMbrId();

		List<FaithCheck> listFaithCheck = repository.findByMbrIdAndFthChckYmd(
			mbrId, fthChckYmd);

		for (FaithCheck fc : listFaithCheck) {
			FaithCheckDTO alreadyCheckDto = new FaithCheckDTO(fc);

			map.put(alreadyCheckDto.getFthActvCd(), alreadyCheckDto);
		}

		return List.copyOf(map.values());
	}

	@Override
	public List<FaithCheckDTO> updateFaithCheck(MemberAuthDetail mbrAuth, String fthChckYmd, List<FaithCheckDTO> checkList) {

		Map<Long, FaithCheckDTO> mapExistingCheck = checkList.stream()
			.filter((check) -> check.getFthChckSn() != null)
			.collect(Collectors.toMap(
				check -> Long.parseLong(check.getFthChckSn()),
				check -> check,
				(oldValue, newValue) -> newValue     // 중복 처리
			));

		List<FaithCheckDTO> listNewCheck = checkList.stream()
			.filter((check) -> check.getFthChckSn() == null)
			.toList();

		List<FaithCheck> existingFthChckList = repository.findByFthChckSnIn(mapExistingCheck.keySet().stream().toList());

		for (FaithCheck fthCheck : existingFthChckList) {
			FaithCheckDTO dto = mapExistingCheck.get(fthCheck.getFthChckSn());

			if (dto == null) {
				listNewCheck.add(new FaithCheckDTO(fthCheck));

				continue;
			}

			Integer fthChckRslt = dto.getFthChckRslt();

			fthCheck.setFthChckRslt(fthChckRslt == null ? 0 : fthChckRslt);

			repository.save(fthCheck);
		}


		for (FaithCheckDTO dto : listNewCheck) {
			FaithCheck newCheck = new FaithCheck(mbrAuth.getMbrId(), dto);

			if (StringUtils.isBlank(newCheck.getFthChckYmd())) {
				newCheck.setFthChckYmd(fthChckYmd);
			}

			repository.save(newCheck);
		}

		return getCheckList(mbrAuth, fthChckYmd);
	}
}
