package org.codingdojo.javaslang;

import javaslang.collection.List;

public class GildedRose {
	final List<Item> items;

	public GildedRose(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}
}
