package com.example.book.utils.mapper;

import com.example.book.model.postgresql.domain.CategoryDomain;
import com.example.book.model.postgresql.dto.CategoryDto;
import com.example.book.model.postgresql.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.WARN,uses = {})
public interface CategoryMapper extends MapperBase<Category, CategoryDto, CategoryDomain>{
	@Override
	default Category createNew() {
		return new Category();
	}
}
