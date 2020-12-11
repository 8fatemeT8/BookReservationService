package com.example.book.utils.enums;

import com.example.book.utils.EnumUtils;

public enum  ReserveStatus implements EnumProviderKey<Integer> {
	RESERVED(0),NOT_RESERVED(1);

	private int key;

	ReserveStatus(int key) {
		this.key = key;
	}

	public static ReserveStatus fromKey(int key) {
		return EnumUtils.fromKey(ReserveStatus.class, key);
	}

	@Override
	public Integer toKey() {
		return key;
	}

	@Override
	public String getName() {
		return this.name();
	}
}
