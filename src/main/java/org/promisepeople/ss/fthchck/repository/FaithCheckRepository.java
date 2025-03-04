package org.promisepeople.ss.fthchck.repository;

import org.promisepeople.ss.fthchck.domain.FaithCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaithCheckRepository extends JpaRepository<FaithCheck, Integer> {

	@Query("SELECT fc FROM Member mbr " +
		"JOIN mbr.faithCheckList fc " +
		"WHERE mbr.mbrId = :mbrId AND fc.fthChckYmd = :fthChckYmd")
	List<FaithCheck> findByMbrIdAndFthChckYmd(Long mbrId, String fthChckYmd);

	List<FaithCheck> findByFthChckSnIn(List<Long> fthChckSns);

}
