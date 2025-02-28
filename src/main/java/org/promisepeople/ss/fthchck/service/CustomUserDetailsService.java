package org.promisepeople.ss.fthchck.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.promisepeople.ss.fthchck.domain.Member;
import org.promisepeople.ss.fthchck.dto.MemberDTO;
import org.promisepeople.ss.fthchck.repository.MemberRepository;
import org.promisepeople.ss.fthchck.security.MemberAuthDetail;
import org.promisepeople.ss.fthchck.security.MemberRole;
import org.promisepeople.ss.util.MemberUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberService memberService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("---------------loadUserByUsername---------------");

		MemberDTO member = memberService.getByUsername(username);

		if (member == null) {
			throw new UsernameNotFoundException("Not Found");
		}

		MemberRole mbrRole = MemberRole.valueOfType(member);

		MemberAuthDetail memberAuth = new MemberAuthDetail(
			member.getUsername(), member.getPswd(), mbrRole
		);

		memberAuth.set(member);


		log.info(memberAuth);

		return memberAuth;
	}
}
