package org.promisepeople.ss.fthchck.domain;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.promisepeople.ss.fthchck.dto.FaithCheckDTO;

/**
 * 신앙_점검
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_FTH_CHCK")
public class FaithCheck {

	/**
	 * 신앙_점검_연번
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FTH_CHCK_SN")
	private Long fthChckSn;

	/**
	 * 신앙_활동
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FTH_ACTV_CD")
	private FaithActivity faithActivity;

	/**
	 * 회원
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MBR_ID")
	private Member member;

	/**
	 * 신앙_점검_일자
	 */
	@Column(name = "FTH_CHCK_YMD")
	private String fthChckYmd;

	/**
	 * 신앙_점검_결과
	 */
	@Column(name = "FTH_CHCK_RSLT")
	private Integer fthChckRslt;

	public FaithCheck(Long mbrId, FaithCheckDTO dto) {
		this.faithActivity = new FaithActivity();

		String fthActvCd = dto.getFthActvCd();

		if (StringUtils.isNumeric(fthActvCd)) {
			this.faithActivity.setFthActvCd(Integer.parseInt(fthActvCd));
		}

		this.member = new Member();
		this.member.setMbrId(mbrId);
		this.fthChckYmd = dto.getFthChckYmd();

		Integer fthChckRslt = dto.getFthChckRslt();
		this.fthChckRslt = fthChckRslt == null ? 0 : fthChckRslt;
	}
}

