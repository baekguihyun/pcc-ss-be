package org.promisepeople.ss.fthchck.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 부서 (주일학교)
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@Table(name = "TBL_DEPT")
public class Department {

	/**
	 * 부서_코드
	 */
	@Id
	@Column(name = "DEPT_CD")
	private Long deptCd;

	/**
	 * 부서_명
	 */
	@Column(name = "DEPT_NM")
	private String deptNm;

}
