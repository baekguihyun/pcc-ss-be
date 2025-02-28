package org.promisepeople.ss.fthchck.repository;

import org.promisepeople.ss.fthchck.domain.Member;
import org.promisepeople.ss.fthchck.dto.MbrSearchDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

//	@Query("select m from Member m where m.username = :username")
	Member findFirstByUsername(String username);

//	@Query("select m from Member m where m.mbrId = :mbrId AND m.brdt = :brdt")
	Member findFirstByMbrIdAndBrdt(Long mbrId, String brdt);

	@Query("SELECT " +
		"new org.promisepeople.ss.fthchck.dto.MbrSearchDTO(" +
		"   stdnt.mbrId, dept.deptNm, sgrp.sgrpNm, stdnt.flnm, stdnt.gndr) " +
		"FROM MemberStudent stdnt " +
		"JOIN stdnt.sgrpFrmtList frmt " +
		"JOIN frmt.smallGroup sgrp " +
		"JOIN sgrp.department dept " +
		"WHERE stdnt.flnm LIKE :searchWord AND " +
			"(stdnt.username IS NULL OR stdnt.username <> '') AND " +
			"(sgrp.useYn = true AND stdnt.useYn = true) " +
		"ORDER BY sgrp.sgrpCd DESC")
	List<MbrSearchDTO> searchMemberWithoutSignUp(String searchWord);

}