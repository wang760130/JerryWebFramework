package com.jerry.web.framework.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class DualCaches {
	protected static final int MAX_ENTRIES = 10;
	static HashMap secondLeveCache;
	static LinkedHashMap firstLevelCache;

	public static void main(String[] args) {
		secondLeveCache = new HashMap();
		firstLevelCache = new LinkedHashMap(MAX_ENTRIES, 0.75f, true) {
			private static final long serialVersionUID = 1854207461617477904L;

			@Override
			protected boolean removeEldestEntry(java.util.Map.Entry eldest) {
				if (size() > MAX_ENTRIES) {
					firstLevelCache.remove(eldest.getKey());
					secondLeveCache.put(eldest.getKey(), new SoftReference(
							eldest.getValue()));
					return true;
				}
				return super.removeEldestEntry(eldest);
			}
		};

		for (int i = 0; i < 12; i++) {
			firstLevelCache.put("" + i, "first" + i);
		}
		System.out.println("-------level 1-----------");
		System.out.println(firstLevelCache.toString());

		System.out.println("-------level 2-----------");
		for (int i = 0; i < secondLeveCache.size(); i++) {
			System.out.println(((SoftReference) secondLeveCache.get(i + "")).get());

		}
	}
}