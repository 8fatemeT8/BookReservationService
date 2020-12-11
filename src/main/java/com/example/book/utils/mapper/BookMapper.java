package com.example.book.utils.mapper;

import com.example.book.model.postgresql.domain.BookDomain;
import com.example.book.model.postgresql.dto.BookDto;
import com.example.book.model.postgresql.entity.Book;
import com.example.book.utils.enums.ReserveStatusConverter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {CategoryMapper.class, ReserveStatusConverter.class})
public interface BookMapper extends MapperBase<Book, BookDto, BookDomain> {
	@Override
	default Book createNew() {
		return new Book();
	}
}
