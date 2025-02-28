package org.promisepeople.ss.fthchck.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 회원_학생
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "TBL_MBR_STDNT")
@PrimaryKeyJoinColumn(name = "STDNT_ID")
public class MemberStudent extends Member {
	/**
	 * 학교_명
	 */
	@Column(name = "SCHL_NM")
	private String schlNm;

	/**
	 * 소그룹 편성 목록
	 */
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
	private List<SmallGroupFrmt> sgrpFrmtList = new ArrayList<>(0);
}
