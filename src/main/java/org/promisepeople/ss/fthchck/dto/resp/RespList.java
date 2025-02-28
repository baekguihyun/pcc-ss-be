package org.promisepeople.ss.fthchck.dto.resp;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RespList<T> extends RespDTO {
	private long count;

	@Builder.Default
	private List<T> result = new ArrayList<T>(0);
}
