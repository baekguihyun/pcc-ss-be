package org.promisepeople.ss.fthchck.repository;

import org.promisepeople.ss.fthchck.domain.SmallGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SmallGroupRepository extends JpaRepository<SmallGroup, Long> {
	@Query("SELECT sgrp FROM MemberStudent stdnt " +
		"JOIN stdnt.sgrpFrmtList frmt " +
		"JOIN frmt.smallGroup sgrp " +
		"WHERE stdnt.mbrId = :mbrId AND sgrp.useYn = true " +
		"ORDER BY sgrp.sgrpCd DESC")
	SmallGroup findFirstByMbrStdntId(Long mbrId);
}
