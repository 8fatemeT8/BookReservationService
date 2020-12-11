package com.example.book.utils.enums;

public interface EnumProviderKey<TKey> {
	TKey toKey();

	String getName();
}
