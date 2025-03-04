package org.promisepeople.ss.fthchck.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.promisepeople.ss.fthchck.domain.FaithActivity;
import org.promisepeople.ss.fthchck.domain.FaithActivityClsf;
import org.promisepeople.ss.fthchck.domain.FaithCheck;

import java.util.Objects;

@Data
@NoArgsConstructor
public class FaithCheckDTO {

	/**
	 * 신앙_점검_연번
	 */
	private String fthChckSn;

	/**
	 * 신앙_활동_코드
	 */
	private String fthActvCd;

	/**
	 * 신앙_활동_분류_코드
	 */
	private String fthActvClsfCd;

	/**
	 * 신앙_활동_분류_명
	 */
	private String fthActvClsfNm;

	/**
	 * 신앙_활동_명
	 */
	private String fthActvNm;

	/**
	 * 신앙_활동_단위
	 */
	private String fthActvUnit;

	/**
	 * 신앙_활동_단위_명
	 */
	private String fthActvUnitNm;

	/**
	 * 신앙_점검_일자
	 */
	private String fthChckYmd;

	/**
	 * 신앙_점검_결과
	 */
	private Integer fthChckRslt;

	public FaithCheckDTO(FaithActivityDTO dto) {
		this.fthActvCd = dto.getFthActvCd();
		this.fthActvNm = dto.getFthActvNm();
		this.fthActvClsfCd = dto.getFthActvClsfCd();
		this.fthActvClsfNm = dto.getFthActvClsfNm();
		this.fthActvUnit = dto.getFthActvUnit();
		this.fthActvUnitNm = dto.getFthActvUnitNm();
	}

	public FaithCheckDTO(FaithCheck fc) {
		this.fthChckSn = Objects.toString(fc.getFthChckSn());
		this.fthChckYmd = fc.getFthChckYmd();

		FaithActivity fa = fc.getFaithActivity();

		if (fa != null) {
			this.fthActvCd = Objects.toString(fa.getFthActvCd(), null);

			FaithActivityClsf classification = fa.getClassification();

			if (classification != null) {
				this.fthActvClsfCd = Objects.toString(classification.getFthActvClsfCd(), null);
				this.fthActvClsfNm = classification.getFthActvClsfNm();
			}

			this.fthActvNm = fa.getFthActvNm();
			this.fthActvUnit = fa.getFthActvUnit();
		}

		this.fthChckRslt = fc.getFthChckRslt();
	}
}
