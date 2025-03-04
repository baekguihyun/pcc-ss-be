package org.promisepeople.ss.fthchck.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.promisepeople.ss.fthchck.domain.FaithActivity;
import org.promisepeople.ss.fthchck.domain.FaithActivityClsf;

import java.util.Objects;

@Data
@NoArgsConstructor
public class FaithActivityDTO {

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

	public FaithActivityDTO(FaithActivity fa) {
		this.fthActvCd = Objects.toString(fa.getFthActvCd(), null);

		FaithActivityClsf classification = fa.getClassification();

		if (classification != null) {
			this.fthActvClsfCd = Objects.toString(classification.getFthActvClsfCd(), null);
			this.fthActvClsfNm = classification.getFthActvClsfNm();
		}

		this.fthActvNm = fa.getFthActvNm();
		this.fthActvUnit = fa.getFthActvUnit();
	}
}
