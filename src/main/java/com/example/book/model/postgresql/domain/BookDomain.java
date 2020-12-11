package com.example.book.model.postgresql.domain;

import java.time.Instant;

public class BookDomain extends DomainBase {
	private String title;
	private Instant publishDate;
	private CategoryDomain category;
	private Integer reserveStatus;

	public Integer getReserveStatus() {
		return reserveStatus;
	}

	public void setReserveStatus(Integer reserveStatus) {
		this.reserveStatus = reserveStatus;
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

	public CategoryDomain getCategory() {
		return category;
	}

	public void setCategory(CategoryDomain category) {
		this.category = category;
	}
}
