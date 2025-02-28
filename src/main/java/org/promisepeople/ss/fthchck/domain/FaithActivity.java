package org.promisepeople.ss.fthchck.domain;


import jakarta.persistence.*;
import lombok.*;

/**
 * 신앙_활동
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@Table(name = "TBL_FTH_ACTV")
public class FaithActivity {

	/**
	 * 신앙_활동_코드
	 */
	@Id
	@Column(name = "FTH_ACTV_CD")
	private Long fthActvCd;


	/**
	 * 신앙_활동_분류
	 */
	@ManyToOne
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

}
