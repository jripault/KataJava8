package org.codingdojo.javaslang;

import static javaslang.Tuple.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import org.codingdojo.java8.GildedRose;
import org.codingdojo.java8.GildedRoseCompany;
import org.codingdojo.java8.Item;
import org.junit.Test;

import javaslang.collection.List;
import javaslang.collection.Map;

public class GildedRoseStreamTest {
	private GildedRoseCompany company = new GildedRoseCompany();

	@Test
	public void shouldFilterBySellEquals10() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		List<Item> items = List.ofAll(shop.getItems()).filter(i -> i.getSellIn() == 10);

		// Then
		assertThat(items).hasSize(2).extracting("name").containsOnly("+5 Dexterity Vest", "Conjured Mana Cake");
	}

	@Test
	public void shouldGetItemsQuality() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		List<Integer> itemsQuality = List.ofAll(shop.getItems()).map(Item::getQuality);

		// Then
		assertThat(itemsQuality).containsExactlyInAnyOrder(10, 0, 10, 25, 20, 30, 20);
	}

	@Test
	public void shouldSortByName() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		List<String> itemsName = List.ofAll(shop.getItems()).map(Item::getName).sorted();

		// Then
		assertThat(itemsName).containsExactly("+5 Dexterity Vest", "+5 Dexterity Vest", "Aged Brie",
				"Backstage passes to a TAFKAL80ETC concert", "Backstage passes to a TAFKAL80ETC concert",
				"Conjured Mana Cake", "Sulfuras, Hand of Ragnaros");
	}

	@Test
	public void shouldSortBySellInDescending() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		List<Integer> itemsSellIn = List.ofAll(shop.getItems()).map(Item::getSellIn).sorted().reverse();

		// Then
		assertThat(itemsSellIn).containsExactly(50, 20, 15, 10, 10, 5, 0);
	}

	@Test
	public void shouldGetTop3Expensive() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		List<Item> items = List.ofAll(shop.getItems()).sortBy(Item::getSellIn).takeRight(3);

		// Then
		assertThat(items).extracting("name").containsOnly("Sulfuras, Hand of Ragnaros", "Aged Brie",
				"Backstage passes to a TAFKAL80ETC concert");
	}

	@Test
	public void shouldGetQualityDistinct() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		List<Integer> itemsQuality = List.ofAll(shop.getItems()).map(Item::getQuality).distinct();

		// Then
		assertThat(itemsQuality).containsExactlyInAnyOrder(0, 10, 20, 25, 30);
	}

	@Test
	public void shouldCountTotalSellIn() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		Integer total = List.ofAll(shop.getItems()).map(Item::getSellIn).sum().intValue();

		// Then
		assertThat(total).isEqualTo(110);
	}

	@Test
	public void shouldGetMaxQuality() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		Integer maxQuality = List.ofAll(shop.getItems()).map(Item::getQuality).max().get();

		// Then
		assertThat(maxQuality).isEqualTo(30);
	}

	@Test
	public void shouldGetMinValue() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		Integer minSellIn = List.ofAll(shop.getItems()).map(Item::getSellIn).min().get();

		// Then
		assertThat(minSellIn).isZero();
	}

	@Test
	public void shouldGetFirst() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		Item firstItem = shop.getItems().get(0);

		// Then
		assertThat(firstItem.getName()).isEqualTo("+5 Dexterity Vest");
		assertThat(firstItem.getSellIn()).isEqualTo(10);
		assertThat(firstItem.getQuality()).isEqualTo(10);
	}

	@Test
	public void isThereOneWithNoQuality() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		boolean hasItemWithNoQuality = List.ofAll(shop.getItems()).exists(i -> i.getQuality() == 0);

		// Then
		assertThat(hasItemWithNoQuality).isTrue();
	}

	@Test
	public void areEveryItemNamed() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		boolean allItemsHaveName = List.ofAll(shop.getItems()).forAll(i -> !"".equals(i.getName()));

		// Then
		assertThat(allItemsHaveName).isTrue();
	}

	@Test
	public void shouldCSVItems() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		String names = List.ofAll(shop.getItems()).map(Item::getName).mkString("[", ",", "]");

		// Then
		assertThat(names).isEqualTo(
				"[+5 Dexterity Vest,+5 Dexterity Vest,Aged Brie,Sulfuras, Hand of Ragnaros,Backstage passes to a TAFKAL80ETC concert,Backstage passes to a TAFKAL80ETC concert,Conjured Mana Cake]");
	}

	@Test
	public void shouldGroupByQuality() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		Map<Integer, Integer> qualitiesItems = List.ofAll(shop.getItems()).groupBy(Item::getQuality)
				.mapValues(items -> items.size());

		// Then
		assertThat(qualitiesItems).hasSize(5).containsOnly(of(0, 1), of(10, 2), of(20, 2), of(25, 1), of(30, 1));
	}

	@Test
	public void shouldGetAverageSellIn() throws Exception {
		// Given
		GildedRose shop = company.shop();

		// When
		double averageSellIn = List.ofAll(shop.getItems()).map(Item::getSellIn).average().get();

		// Then
		assertThat(averageSellIn).isEqualTo(15.71, offset(0.01));
	}

	@Test
	public void shouldGetQualityFromAllShops() throws Exception {
		// Given
		List<GildedRose> shops = List.ofAll(company.getShops());

		// When
		List<Integer> qualities = shops.flatMap(s -> List.ofAll(s.getItems()).map(Item::getQuality));

		// Then
		assertThat(qualities).containsOnly(10, 0, 10, 25, 20, 30, 20, 0, 0, 50, 49, 20, 1);
	}

	@Test
	public void howMuchToBuyAllItems() throws Exception {
		// Given
		List<GildedRose> shops = List.ofAll(company.getShops());

		// When
		Integer priceOfAllItems = shops.flatMap(s -> List.ofAll(s.getItems()).map(Item::getSellIn)).sum().intValue();

		// Then
		assertThat(priceOfAllItems).isEqualTo(155);
	}

	@Test
	public void getItemsNotOnSaleInAnyShops() throws Exception {
		// Given
		List<GildedRose> shops = List.ofAll(company.getShops());

		// When
		List<String> notSellingItems = shops.flatMap(s -> List.ofAll(s.getItems()).filter(i -> i.getSellIn() == 0))
				.map(Item::getName);

		// Then
		assertThat(notSellingItems).containsOnly("+5 Dexterity Vest", "+5 Dexterity Vest",
				"Backstage passes to a TAFKAL80ETC concert");
	}
}
