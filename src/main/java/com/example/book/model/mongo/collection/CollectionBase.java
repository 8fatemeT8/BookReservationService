package com.example.book.model.mongo.collection;

import javax.persistence.Id;
import java.time.Instant;

public class CollectionBase {
	@Id
	private String id;
	private Instant createDate;
	private Instant updateDate;

	public Instant getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Instant createDate) {
		this.createDate = createDate;
	}

	public Instant getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Instant updateDate) {
		this.updateDate = updateDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
