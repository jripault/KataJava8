package org.codingdojo.javaslang;

import javaslang.collection.List;

public class GildedRoseCompany {

	private final List<GildedRose> shops;

	public GildedRoseCompany() {
		List<Item> items1 = List.of(new Item("+5 Dexterity Vest", 10, 10), new Item("+5 Dexterity Vest", 0, 0),
				new Item("Aged Brie", 20, 10), new Item("Sulfuras, Hand of Ragnaros", 50, 25),
				new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
				new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30), new Item("Conjured Mana Cake", 10, 20));

		List<Item> items2 = List.of(new Item("+5 Dexterity Vest", 10, 0), new Item("+5 Dexterity Vest", 0, 0),
				new Item("Aged Brie", 20, 50), new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
				new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20), new Item("Conjured Mana Cake", 10, 1));

		GildedRose shop1 = new GildedRose(items1);
		GildedRose shop2 = new GildedRose(items2);

		shops = List.of(shop1, shop2);
	}

	public GildedRose shop() {
		return shops.get(0);
	}

	public List<GildedRose> getShops() {
		return shops;
	}
}
