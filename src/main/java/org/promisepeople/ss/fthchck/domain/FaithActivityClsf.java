package org.promisepeople.ss.fthchck.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 신앙_활동
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_FTH_ACTV_CLSF")
public class FaithActivityClsf {

	/**
	 * 신앙_활동_분류_코드
	 */
	@Id
	@Column(name = "FTH_ACTV_CLSF_CD")
	private Integer fthActvClsfCd;

	/**
	 * 신앙_활동_분류_명
	 */
	@Column(name = "FTH_ACTV_CLSF_NM")
	private String fthActvClsfNm;

	/**
	 * 순서
	 */
	@Column(name = "SEQ")
	private Integer seq;

	/**
	 * 신앙 활동 목록
	 */
	@OneToMany(mappedBy = "classification")
	private List<FaithActivity> activityList = new ArrayList<>(0);

}
