package org.promisepeople.ss.fthchck.dto.resp;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RespResult<T> extends RespDTO {
	private T result;
}
