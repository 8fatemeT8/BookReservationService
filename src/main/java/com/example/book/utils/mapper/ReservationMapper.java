package com.example.book.utils.mapper;

import com.example.book.model.mongo.collection.Reservation;
import com.example.book.model.mongo.domain.ReservationDomain;
import com.example.book.model.mongo.dto.ReservationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {BookMapper.class, UserMapper.class})
public interface ReservationMapper extends MapperBase<Reservation, ReservationDto, ReservationDomain> {
	ReservationDomain toDtoFromDomain(ReservationDomain domain);
	ReservationDomain toDomainFromDto(ReservationDto dto);

	@Override
	default Reservation createNew() {
		return new Reservation();
	}
}
