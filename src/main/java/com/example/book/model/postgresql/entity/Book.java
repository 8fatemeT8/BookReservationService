package com.example.book.model.postgresql.entity;

import com.example.book.utils.enums.ReserveStatus;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "books")
public class Book extends EntityBase {

	@Column(unique = true ,nullable = false)
	private String title;

	@Column(nullable = false)
	private Instant publishDate;

	@ManyToOne
	@JoinColumn(name = "categoory_id")
	private Category category;

	@Column(name = "reserve_status")
	private ReserveStatus reserveStatus;

	public ReserveStatus getReserveStatus() {
		return reserveStatus;
	}

	public void setReserveStatus(ReserveStatus reserveStatus) {
		this.reserveStatus = reserveStatus;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instant getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Instant publishDate) {
		this.publishDate = publishDate;
	}
}
