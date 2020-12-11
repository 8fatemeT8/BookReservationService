package com.example.book.utils.filter.criteria;

import com.example.book.utils.IdFilter;
import com.example.book.utils.StringFilter;

public class BookCriteria {
	private StringFilter title;
	private IdFilter reserveStatus;

	public IdFilter getReserveStatus() {
		return reserveStatus;
	}

	public void setReserveStatus(IdFilter reserveStatus) {
		this.reserveStatus = reserveStatus;
	}

	public StringFilter getTitle() {
		return title;
	}

	public void setTitle(StringFilter title) {
		this.title = title;
	}
}
