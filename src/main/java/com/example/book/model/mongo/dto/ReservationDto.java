package com.example.book.model.mongo.dto;

import com.example.book.model.postgresql.dto.BookDto;
import com.example.book.model.postgresql.dto.UserDto;

public class ReservationDto extends CoDtoBase {
	private UserDto user;
	private BookDto book;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}
}
