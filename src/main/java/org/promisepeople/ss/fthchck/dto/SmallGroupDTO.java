package org.promisepeople.ss.fthchck.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.promisepeople.ss.fthchck.domain.*;
import org.promisepeople.ss.fthchck.domain.convert.BooleanToYNConverter;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SmallGroupDTO {
	/*
	 * 소그룹_코드
	 */
	private String sgrpCd;

	/**
	 * 소그룹_명
	 */
	private String sgrpNm;

	/**
	 * 부서_코드
	 */
	private String deptCd;

	/**
	 * 부서_명
	 */
	private String deptNm;

	/**
	 * 교사_아이디
	 */
	private String tchrId;

	/**
	 * 교사_명
	 */
	private String tchrNm;

	/**
	 * 학년_번호
	 */
	private Integer grdNo;

	/**
	 * 반_번호
	 */
	private Integer clsNo;

	/**
	 * 학기
	 */
	private String sem;

	public void set(SmallGroup smallGroup) {
		this.sgrpCd = Long.toString(smallGroup.getSgrpCd());
		this.sgrpNm = smallGroup.getSgrpNm();

		Department department = smallGroup.getDepartment();

		if (department != null) {
			this.deptCd =  Long.toString(department.getDeptCd());
			this.deptNm = department.getDeptNm();
		}

		MemberTeacher teacher = smallGroup.getTeacher();

		if (teacher != null) {
			this.tchrId = Long.toString(teacher.getMbrId());
			this.tchrNm = teacher.getFlnm();
		}

		this.grdNo = smallGroup.getGrdNo();
		this.clsNo = smallGroup.getClsNo();
		this.sem = smallGroup.getSem();
	}
}
