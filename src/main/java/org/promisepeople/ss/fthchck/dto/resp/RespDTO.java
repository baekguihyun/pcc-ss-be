package org.promisepeople.ss.fthchck.dto.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import egovframework.com.cmm.ResponseCode;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.promisepeople.ss.exception.ApiStatusEnum;

@Getter
@SuperBuilder
@JacksonXmlRootElement(localName = "response")
public abstract class RespDTO {
	@Builder.Default
	private String code = ApiStatusEnum.NORMAL_CODE.getCode();

	@Builder.Default
	private String message = ResponseCode.SUCCESS.getMessage();

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String messageEng;
}

