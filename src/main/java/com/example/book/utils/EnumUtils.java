package com.example.book.utils;

import com.example.book.utils.enums.EnumProviderKey;

public class EnumUtils {
	public static <E extends Enum<E> & EnumProviderKey<K>, K> E fromKey(Class<E> enumClass, K key) {
		for (E en : enumClass.getEnumConstants()) {
			if (en.toKey().equals(key))
				return en;
		}
		throw new IllegalArgumentException(String.format("{%s}({%s}) not supported.", enumClass.getSimpleName(), key.toString()));
	}
}
