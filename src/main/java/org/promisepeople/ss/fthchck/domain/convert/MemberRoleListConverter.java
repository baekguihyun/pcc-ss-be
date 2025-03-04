package org.promisepeople.ss.fthchck.domain.convert;

import jakarta.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;
import org.promisepeople.ss.fthchck.security.MemberRole;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MemberRoleListConverter implements AttributeConverter<List<MemberRole>, String> {


	private static final String DELIMITER = ",";

	@Override
	public String convertToDatabaseColumn(List<MemberRole> attribute) {
		if (CollectionUtils.isEmpty(attribute)) {
			return null;
		}

		return attribute.stream()
			.map(MemberRole::getMbrType)
			.map(s -> StringUtils.trim(s))
			.collect(Collectors.joining(DELIMITER));
	}

	@Override
	public List<MemberRole> convertToEntityAttribute(String dbData) {
		if (StringUtils.isBlank(dbData)) {
			return List.of();
		}

		return Arrays.stream(StringUtils.split(dbData, DELIMITER))
			.map(MemberRole::valueOfType)
			.collect(Collectors.toList());
	}
}
