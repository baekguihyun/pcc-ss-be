package org.promisepeople.ss.fthchck.domain;

import jakarta.persistence.*;
import lombok.*;
import org.promisepeople.ss.fthchck.domain.convert.BooleanToYNConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 소그룹
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@Table(name = "TBL_SGRP", indexes = {
	@Index(name = "FK_tbl_sgrp_tbl_dept", columnList = "DEPT_CD"),
	@Index(name = "FK_tbl_sgrp_tbl_mbr_tchr", columnList = "TCHR_ID")
})
public class SmallGroup {
	/**
	 * 소그룹_코드
	 */
	@Id
	@Column(name = "SGRP_CD")
	private Long sgrpCd;

	/**
	 * 부서
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPT_CD")
	private Department department;

//	/**
//	 * 부서_코드
//	 */
//	@Column(name = "DEPT_CD")
//	private Long deptCd;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TCHR_ID")
	private MemberTeacher teacher;

	@Column(name = "SGRP_NM")
	private String sgrpNm;

	/**
	 * 교사_아이디
	 */
//	@Column(name = "TCHR_ID", insertable = false, updatable = false)
//	private Long tchrId;

	/**
	 * 학년_번호
	 */
	@Column(name = "GRD_NO")
	private Integer grdNo;

	/**
	 * 반_번호
	 */
	@Column(name = "CLS_NO")
	private Integer clsNo;

	/**
	 * 학기
	 */
	@Column(name = "SEM")
	private String sem;

	/**
	 * 사용_여부
	 */
	@Column(name = "USE_YN")
	@Convert(converter = BooleanToYNConverter.class)
	private Boolean useYn = true;

	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "smallGroup")
	private List<SmallGroupFrmt> sgrpFrmtList = new ArrayList<>(0);
}
