package com.example.book.model.postgresql.dto;

import java.time.Instant;

public class BookDto extends DtoBase {
	private String title;
	private Instant publishDate;
	private CategoryDto category;
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

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}
}
