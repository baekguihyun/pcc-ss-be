package org.promisepeople.ss.fthchck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MbrSearchDTO {

	public MbrSearchDTO(Long mbrId, String deptNm, String sgrpNm, String flnm, String gndr) {
		this.mbrId = Long.toString(mbrId);
		this.deptNm = deptNm;
		this.sgrpNm = sgrpNm;
		this.flnm = flnm;
		this.gndr = gndr;
	}

	/**
	 * 회원_아이디
	 */
	private String mbrId;
	/**
	 * 부서_명
	 */
	private String deptNm;

	/**
	 * 소그룹_명
	 */
	private String sgrpNm;

	/**
	 * 성명
	 */
	private String flnm;

	/**
	 * 성별
	 */
	private String gndr;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String brdt;
}
