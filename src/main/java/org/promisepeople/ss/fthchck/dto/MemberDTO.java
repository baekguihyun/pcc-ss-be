package org.promisepeople.ss.fthchck.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.promisepeople.ss.fthchck.domain.*;
import org.promisepeople.ss.fthchck.security.MemberRole;

import java.util.List;

@Data
@NoArgsConstructor
public class MemberDTO {

	/**
	 * 회원_아이디
	 */
	private String mbrId;

	/**
	 * 사용자계정명
	 */
	private String username;

	/**
	 * 패스워드
	 */
	private String pswd;

	/**
	 * 성명
	 */
	private String flnm;

	/**
	 * 성별 ('M': 남, 'F': 여)
	 */
	private String gndr;

	/**
	 * 전화번호
	 */
	private String telno;

	/**
	 * 생년월일
	 */
	private String brdt;

	/**
	 * 생일_월_일
	 */
	private String dobMmDay;

	/**
	 * 음력_여부
	 */
	private Boolean lnclYn = false;

	/**
	 * 구글_이메일
	 */
	private String gglEml;

	/**
	 * 주소
	 */
	private String addr;

	/**
	 * 상세주소
	 */
	private String daddr;

	/**
	 * 부서_명
	 */
	private String deptNm;

	/**
	 * 소그룹_명
	 */
	private String sgrpNm;

	/**
	 * 학교_명
	 */
	private String schlNm;

	/**
	 * 회원_유형
	 */
	private String mbrType;

	public void set(Member member) {
		if (member == null) {
			return;
		}

		this.mbrId = Long.toString(member.getMbrId());
		this.username = member.getUsername();
		this.pswd = member.getPswd();
		this.flnm = member.getFlnm();
		this.gndr = member.getGndr();
		this.telno = member.getTelno();
		this.brdt = member.getBrdt();
		this.dobMmDay = member.getDobMmDay();
		this.lnclYn = member.getLnclYn();
		this.gglEml = member.getGglEml();
		this.addr = member.getAddr();
		this.daddr = member.getDaddr();

		if (member instanceof MemberStudent ms) {
			this.mbrType = MemberRole.STUDENT.getMbrType();

			this.schlNm = ms.getSchlNm();
		}
		else if (member instanceof MemberTeacher) {
			this.mbrType = MemberRole.TEACHER.getMbrType();
		}
		else if (member instanceof MemberParent) {
			this.mbrType = MemberRole.PARENT.getMbrType();
		}
		else {
			this.mbrType = MemberRole.MEMBER.getMbrType();
		}
	}

	public void set(SmallGroupDTO smallGroup) {
		if (smallGroup == null) {
			return;
		}

		this.deptNm = smallGroup.getDeptNm();
		this.sgrpNm = smallGroup.getSgrpNm();
	}
}
