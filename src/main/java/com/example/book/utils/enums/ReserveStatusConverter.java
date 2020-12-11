package com.example.book.utils.enums;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class ReserveStatusConverter implements AttributeConverter<ReserveStatus,Integer> {
	@Override
	public Integer convertToDatabaseColumn(ReserveStatus attribute) {
		if (attribute == null)
			return null;
		return attribute.toKey();
	}

	@Override
	public ReserveStatus convertToEntityAttribute(Integer dbData) {
		if (dbData==null)
			return null;
		return ReserveStatus.fromKey(dbData);
	}
}
