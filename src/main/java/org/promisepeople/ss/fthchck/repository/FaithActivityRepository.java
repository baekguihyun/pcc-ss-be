package org.promisepeople.ss.fthchck.repository;

import org.promisepeople.ss.fthchck.domain.FaithActivity;
import org.promisepeople.ss.fthchck.domain.FaithCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaithActivityRepository extends JpaRepository<FaithActivity, Integer> {

	@Query("SELECT fa FROM FaithActivity fa " +
		"WHERE CONCAT(',', fa.authorizedMemberRoles, ',') LIKE CONCAT('%,', :mbrType, ',%') " +
		"ORDER BY fa.classification.seq, fa.fthActvCd")
	List<FaithActivity> findByAuthority(String mbrType);
}
