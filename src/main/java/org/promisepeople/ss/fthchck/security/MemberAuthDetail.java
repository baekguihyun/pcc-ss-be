package org.promisepeople.ss.fthchck.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.promisepeople.ss.fthchck.domain.Member;
import org.promisepeople.ss.fthchck.dto.MemberDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@ToString
public class MemberAuthDetail extends User {

	/**
	 * 회원_아이디
	 */
	private Long mbrId;

	/**
	 * 비밀번호
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

	private List<String> roleNames;

	public MemberAuthDetail(String username, String password, MemberRole memberRole) {
		super(username, password,
			Arrays.asList(new SimpleGrantedAuthority("ROLE_" + memberRole.name())));

		this.pswd = password;
		this.mbrType = memberRole.getMbrType();
		this.roleNames = Arrays.asList(memberRole.name());
	}

	public final Map<String, Object> generateClaims() {
		Map<String, Object> dataMap = new HashMap<>();

		dataMap.put("mbrId", this.mbrId);
		dataMap.put("username", getUsername());
		dataMap.put("password", this.pswd);
		dataMap.put("flnm", this.flnm);
		dataMap.put("gndr", this.gndr);
		dataMap.put("telno", this.telno);
		dataMap.put("brdt", this.brdt);
		dataMap.put("dobMmDay", this.dobMmDay);
		dataMap.put("lnclYn", this.lnclYn);
		dataMap.put("addr", this.addr);
		dataMap.put("daddr", this.daddr);

		dataMap.put("deptNm", this.deptNm);
		dataMap.put("sgrpNm", this.sgrpNm);
		dataMap.put("schlNm", this.schlNm);

		dataMap.put("mbrType", this.mbrType);
		dataMap.put("roleNames", this.roleNames);


		return dataMap;
	}

	public void set(Member member) {
		if (!StringUtils.equals(member.getUsername(), this.getUsername())) {
			return;
		}

		this.mbrId = member.getMbrId();
		this.flnm = member.getFlnm();
		this.gndr = member.getGndr();
		this.telno = member.getTelno();
		this.brdt = member.getBrdt();
		this.dobMmDay = member.getDobMmDay();
		this.lnclYn = member.getLnclYn();
		this.gglEml = member.getGglEml();
		this.addr = member.getAddr();
		this.daddr = member.getDaddr();
	}


	public void set(MemberDTO dto) {
		if (dto == null || !StringUtils.equals(dto.getUsername(), this.getUsername())) {
			return;
		}

		this.mbrId = Long.parseLong(dto.getMbrId());
		this.flnm = dto.getFlnm();
		this.gndr = dto.getGndr();
		this.telno = dto.getTelno();
		this.brdt = dto.getBrdt();
		this.dobMmDay = dto.getDobMmDay();
		this.lnclYn = dto.getLnclYn();
		this.gglEml = dto.getGglEml();
		this.addr = dto.getAddr();
		this.daddr = dto.getDaddr();

		this.deptNm = dto.getDeptNm();
		this.sgrpNm = dto.getSgrpNm();
		this.schlNm = dto.getSchlNm();
	}
}
