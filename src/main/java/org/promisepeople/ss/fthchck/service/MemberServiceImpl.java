package org.promisepeople.ss.fthchck.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.promisepeople.ss.fthchck.domain.Member;
import org.promisepeople.ss.fthchck.domain.MemberStudent;
import org.promisepeople.ss.fthchck.dto.MbrSignUpDTO;
import org.promisepeople.ss.fthchck.dto.MbrSearchDTO;
import org.promisepeople.ss.fthchck.dto.MemberDTO;
import org.promisepeople.ss.fthchck.dto.SmallGroupDTO;
import org.promisepeople.ss.fthchck.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	private final SmallGroupService smallGroupService;

	private final PasswordEncoder passwordEncoder;

//	@Override
//	public void modifyMember(MemberModifyDTO memberModifyDTO) {
//		Optional<Member> result = memberRepository.findById(memberModifyDTO.getEmail());
//
//		Member member = result.orElseThrow();
//
//		member.setPw(passwordEncoder.encode(memberModifyDTO.getPw()));
//		member.setSocial(false);
//		member.setNickname(memberModifyDTO.getNickname());
//
//		memberRepository.save(member);
//	}

	@Override
	public MemberDTO getByUsername(String username) {
		Member member = memberRepository.findFirstByUsername(username);

		if (member == null) {
			return null;
		}

		MemberDTO memberDTO = new MemberDTO();

		memberDTO.set(member);

		if (member instanceof MemberStudent) {
			SmallGroupDTO smallGroup = smallGroupService.getByMbrStdntId(member.getMbrId());

			memberDTO.set(smallGroup);
		}

		return memberDTO;
	}

	@Override
	public List<MbrSearchDTO> searchMemberWithoutSignUp(String searchWord) {
		searchWord = StringUtils.trim(searchWord);

		if (StringUtils.isBlank(searchWord) || StringUtils.length(searchWord) < 2) {
			return Collections.emptyList();
		}

		searchWord = "%" + searchWord + "%";

		return memberRepository.searchMemberWithoutSignUp(searchWord);
	}

	@Override
	public boolean validateMemberWithBrdt(Long mbrId, String brdt) {
		Member member = memberRepository.findFirstByMbrIdAndBrdt(mbrId, brdt);

		return member != null;
	}

	@Override
	public boolean checkDpcnUsername(String username) {
		Member member = memberRepository.findFirstByUsername(username);

		return member != null;
	}

	@Override
	public boolean signUp(MbrSignUpDTO memberAuth) {
		String strMbrIdr = memberAuth.getMbrId();

		if (StringUtils.isBlank(strMbrIdr)) {
			return false;
		}

		Long mbrId = Long.parseLong(strMbrIdr);

		Member member = memberRepository.getReferenceById(mbrId);

		if (member == null) {
			return false;
		}

		member.setUsername(memberAuth.getUsername());
		member.setPswd(passwordEncoder.encode(memberAuth.getPswd()));

		memberRepository.save(member);

		return true;
	}

}
