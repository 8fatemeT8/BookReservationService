package com.example.book.utils.mapper;

import com.example.book.model.postgresql.domain.UserDomain;
import com.example.book.model.postgresql.dto.UserDto;
import com.example.book.model.postgresql.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.WARN,uses = {})
public interface UserMapper extends MapperBase<User, UserDto, UserDomain>{
	@Override
	default User createNew() {
		return new User();
	}
}
