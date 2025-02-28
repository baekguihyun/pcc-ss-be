package org.promisepeople.ss.fthchck.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 신앙_점검
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@Table(name = "TBL_FTH_CHCK")
public class FaithCheck {

	/**
	 * 신앙_점검_연번
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FTH_CHCK_SN")
	private Long fthCheckSn;

	/**
	 * 신앙_활동
	 */
	@ManyToOne
	@JoinColumn(name = "FTH_ACTV_CD")
	private FaithActivity faithActivity;

	/**
	 * 신앙_점검_일자
	 */
	@Column(name = "FTH_CHCK_YMD")
	private String fthChckYmd;

	/**
	 * 신앙_점검_결과
	 */
	@Column(name = "FTH_CHCK_RSLT")
	private Short fthChckRslt;
}

