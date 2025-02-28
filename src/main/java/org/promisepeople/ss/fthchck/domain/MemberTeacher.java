package org.promisepeople.ss.fthchck.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 회원_교사
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "TBL_MBR_TCHR")
@PrimaryKeyJoinColumn(name = "TCHR_ID")
public class MemberTeacher extends Member {
	/**
	 * 직책_코드
	 */
	@Column(name = "JBTTL_CD")
	private String jbttlCd;

	/**
	 * 담당 소그룹 목록
	 */
	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
	private List<SmallGroup> tkcgSgrpList = new ArrayList<>(0);
}
