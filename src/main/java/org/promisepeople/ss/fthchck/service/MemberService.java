package org.promisepeople.ss.fthchck.service;

import org.promisepeople.ss.fthchck.dto.MbrSignUpDTO;
import org.promisepeople.ss.fthchck.dto.MbrSearchDTO;
import org.promisepeople.ss.fthchck.dto.MemberDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MemberService {
	MemberDTO getByUsername(String username);

	//	void modifyMember(MemberModifyDTO memberModifyDTO);
	List<MbrSearchDTO> searchMemberWithoutSignUp(String searchWord);

	boolean validateMemberWithBrdt(Long mbrId, String brdt);

	boolean checkDpcnUsername(String username);

	boolean signUp(MbrSignUpDTO member);
}
