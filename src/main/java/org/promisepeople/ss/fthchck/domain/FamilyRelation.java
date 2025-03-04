package org.promisepeople.ss.fthchck.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 가족_관계
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_FAM_REL",
	indexes = {
		@Index(name = "UNIQ_MBR_ID", columnList = "MBR_ID"),
	}
)
public class FamilyRelation {

	@Id
	@Column(name = "FAM_REL_SN")
	private Long famRelSn;

	@ManyToOne
	@JoinColumn(name = "STDNT_ID")
	private MemberStudent student;

	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	private MemberParent parent;

	@Column(name = "FAM_REL_CD")
	private String famRelCd;
}
