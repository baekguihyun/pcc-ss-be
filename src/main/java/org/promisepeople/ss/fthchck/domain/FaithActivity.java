package org.promisepeople.ss.fthchck.domain;


import jakarta.persistence.*;
import lombok.*;
import org.promisepeople.ss.fthchck.domain.convert.BooleanToYNConverter;
import org.promisepeople.ss.fthchck.domain.convert.MemberRoleListConverter;
import org.promisepeople.ss.fthchck.security.MemberRole;

import java.util.List;

/**
 * 신앙_활동
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TBL_FTH_ACTV")
public class FaithActivity {

	/**
	 * 신앙_활동_코드
	 */
	@Id
	@Column(name = "FTH_ACTV_CD")
	private Integer fthActvCd;

	/**
	 * 신앙_활동_분류
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FTH_ACTV_CLSF_CD")
	private FaithActivityClsf classification;

	/**
	 * 신앙_활동_명
	 */
	@Column(name = "FTH_ACTV_NM")
	private String fthActvNm;

	/**
	 * 신앙_활동_단위
	 */
	@Column(name = "FTH_ACTV_UNIT")
	private String fthActvUnit;

	/**
	 * 점검_권한_코드_목록
	 */
	@Column(name = "CHCK_AUTHRT_CD_LST")
	@Convert(converter = MemberRoleListConverter.class)
	private List<MemberRole> authorizedMemberRoles;


	/**
	 * 사용_여부
	 */
	@Column(name = "USE_YN")
	@Convert(converter = BooleanToYNConverter.class)
	private Boolean useYn = true;
}
