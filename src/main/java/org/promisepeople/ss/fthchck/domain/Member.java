package org.promisepeople.ss.fthchck.domain;

import jakarta.persistence.*;
import lombok.*;
import org.promisepeople.ss.fthchck.domain.convert.BooleanToYNConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 회원
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "TBL_MBR",
	indexes = {
		@Index(name = "UNIQ_USERNAME", columnList = "USERNAME"),
	}
)
@Inheritance(strategy = InheritanceType.JOINED)
public class Member {

	/**
	 * 회원_아이디
	 */
	@Id
	@Column(name = "MBR_ID")
	private Long mbrId;

	/**
	 * 사용자계정명
	 */
	@Column(name = "USERNAME", unique = true)
	private String username;

	/**
	 * 패스워드
	 */
	@Column(name = "PSWD")
	private String pswd;

	/**
	 * 성명
	 */
	@Column(name = "FLNM")
	private String flnm;

	/**
	 * 성별 ('M': 남, 'F': 여)
	 */
	@Column(name = "GNDR")
	private String gndr = "M";

	/**
	 * 전화번호
	 */
	@Column(name = "TELNO")
	private String telno;

	/**
	 * 생년월일
	 */
	@Column(name = "BRDT")
	private String brdt;

	/**
	 * 생일_월_일
	 */
	@Column(name = "DOB_MM_DAY")
	private String dobMmDay;

	/**
	 * 음력_여부
	 */
	@Column(name = "LNCL_YN")
	@Convert(converter = BooleanToYNConverter.class)
	private Boolean lnclYn;

	/**
	 * 구글_이메일
	 */
	@Column(name = "GGL_EML")
	private String gglEml;

	/**
	 * 주소
	 */
	@Column(name = "ADDR")
	private String addr;

	/**
	 * 상세주소
	 */
	@Column(name = "DADDR")
	private String daddr;

	/**
	 * 사용_여부
	 */
	@Column(name = "USE_YN")
	@Convert(converter = BooleanToYNConverter.class)
	private Boolean useYn = true;

	/**
	 * 최초_등록_일시
	 */
	@Column(name = "FRST_REG_DT")
	private LocalDateTime frstRegDt;

	/**
	 * 갱신_일시
	 */
	@Column(name = "UPDT_DT")
	private LocalDateTime updtDt;

}
