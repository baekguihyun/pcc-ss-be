package org.promisepeople.ss.fthchck.dto.resp;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * 예외 처리 메시지 VO 클래스
 * @author KITC컨소시엄 김기형
 * **/
@Getter
@SuperBuilder
public class ApiExceptionEntity extends RespDTO {
	private String detailMsg;
}
