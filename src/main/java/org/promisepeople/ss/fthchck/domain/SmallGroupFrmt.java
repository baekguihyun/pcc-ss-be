package org.promisepeople.ss.fthchck.domain;


import jakarta.persistence.*;
import lombok.*;

/**
    소그룹_편성
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@Table(name = "TBL_SGRP_FRMT")
public class SmallGroupFrmt {

	/**
	 * 소그룹_편성_연번
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SGRP_FRMT_SN")
	private Long sgrpFrmtSn;

	/**
	 * 소그룹
	 */
	@ManyToOne
	@JoinColumn(name = "SGRP_CD")
	private SmallGroup smallGroup;

	/**
	 * 학생
	 */
	@ManyToOne
	@JoinColumn(name = "STDNT_ID")
	private MemberStudent student;

	/**
	 * 순서
	 */
	@Column(name = "SEQ")
	private Integer seq;
}
