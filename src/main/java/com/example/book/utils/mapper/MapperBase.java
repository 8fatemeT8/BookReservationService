package com.example.book.utils.mapper;

import java.util.List;

public interface MapperBase<TEntity, TDto, TDomain> {
	TEntity createNew();

	TEntity toEntityFromDomain(TDomain domain);

	TEntity toEntityFromDto(TDto dto);

	List<TEntity> toEntityListFromDomain(List<TDomain> domains);

	List<TEntity> toEntityListFromDto(List<TDto> dtos);

	TDto toDto(TEntity entity);

	TDomain toDomain(TEntity entity);

	List<TDomain> toDomainList(List<TEntity> entities);

	List<TDto> toDtoList(List<TEntity> entities);

}
