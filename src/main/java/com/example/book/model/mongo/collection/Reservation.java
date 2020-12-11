package com.example.book.model.mongo.collection;

import com.example.book.model.postgresql.domain.BookDomain;
import com.example.book.model.postgresql.entity.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EntityListeners;
import java.time.Instant;

@Document(collection = "reservation")
@EntityListeners(AuditingEntityListener.class)
public class Reservation extends CollectionBase {
	@CreatedBy
	private User user;
	private BookDomain book;
	private Instant returnDate;

	public Instant getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Instant returnDate) {
		this.returnDate = returnDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BookDomain getBook() {
		return book;
	}

	public void setBook(BookDomain book) {
		this.book = book;
	}
}
