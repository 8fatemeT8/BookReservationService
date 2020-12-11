package com.example.book.model.mongo.domain;

import com.example.book.model.postgresql.domain.BookDomain;
import com.example.book.model.postgresql.domain.UserDomain;

import java.time.Instant;

public class ReservationDomain extends CoDomainBase {
	private UserDomain user;
	private BookDomain book;
	private Instant returnDate;

	public Instant getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Instant returnDate) {
		this.returnDate = returnDate;
	}

	public UserDomain getUser() {
		return user;
	}

	public void setUser(UserDomain user) {
		this.user = user;
	}

	public BookDomain getBook() {
		return book;
	}

	public void setBook(BookDomain book) {
		this.book = book;
	}
}
