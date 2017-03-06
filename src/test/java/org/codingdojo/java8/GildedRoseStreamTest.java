package org.codingdojo.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.assertj.core.data.MapEntry.entry;

/**
 * Created by Julien on 22/02/2017.
 */
public class GildedRoseStreamTest {
    private GildedRoseCompany company = new GildedRoseCompany();

    @Test
    public void shouldFilterBySellEquals10() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        List<Item> items = new ArrayList<>();

        //Then
        assertThat(items).hasSize(2).extracting("name").containsOnly("+5 Dexterity Vest", "Conjured Mana Cake");
    }

    @Test
    public void shouldGetItemsQuality() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        List<Integer> itemsQuality = new ArrayList<>();

        //Then
        assertThat(itemsQuality).containsExactlyInAnyOrder(10,0,10,25,20,30,20);
    }

    @Test
    public void shouldSortByName() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        List<String> itemsName = new ArrayList<>();

        //Then
        assertThat(itemsName).containsExactly("+5 Dexterity Vest",
                "+5 Dexterity Vest",
                "Aged Brie",
                "Backstage passes to a TAFKAL80ETC concert",
                "Backstage passes to a TAFKAL80ETC concert",
                "Conjured Mana Cake",
                "Sulfuras, Hand of Ragnaros");
    }

    @Test
    public void shouldSortBySellInDescending() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        List<Integer> itemsSellIn = new ArrayList<>();

        //Then
        assertThat(itemsSellIn).containsExactly(50,20,15,10,10,5,0);
    }

    @Test
    public void shouldGetTop3Expensive() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        List<Item> items = new ArrayList<>();

        //Then
        assertThat(items).extracting("name")
                .containsOnly("Sulfuras, Hand of Ragnaros",
                        "Aged Brie",
                        "Backstage passes to a TAFKAL80ETC concert");
    }

    @Test
    public void shouldGetQualityDistinct() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        List<Integer> itemsQuality = new ArrayList<>();

        //Then
        assertThat(itemsQuality).containsExactlyInAnyOrder(0,10,20,25,30);
    }

    @Test
    public void shouldCountTotalSellIn() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        Integer total = 0;

        //Then
        assertThat(total).isEqualTo(110);
    }

    @Test
    public void shouldGetMaxQuality() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        Integer maxQuality = 0;

        //Then
        assertThat(maxQuality).isEqualTo(30);
    }

    @Test
    public void shouldGetMinValue() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        Integer minSellIn = 0;

        //Then
        assertThat(minSellIn).isZero();
    }

    @Test
    public void shouldGetFirst() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        Item firstItem = null;

        //Then
        assertThat(firstItem.getName()).isEqualTo("+5 Dexterity Vest");
        assertThat(firstItem.getSellIn()).isEqualTo(10);
        assertThat(firstItem.getQuality()).isEqualTo(10);
    }

    @Test
    public void isThereOneWithNoQuality() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        boolean hasItemWithNoQuality = false;

        //Then
        assertThat(hasItemWithNoQuality).isTrue();
    }

    @Test
    public void areEveryItemNamed() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        boolean allItemsHaveName = false;

        //Then
        assertThat(allItemsHaveName).isTrue();
    }

    @Test
    public void shouldCSVItems() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        String names = "";

        //Then
        assertThat(names).isEqualTo("[+5 Dexterity Vest,+5 Dexterity Vest,Aged Brie,Sulfuras, Hand of Ragnaros,Backstage passes to a TAFKAL80ETC concert,Backstage passes to a TAFKAL80ETC concert,Conjured Mana Cake]");
    }

    @Test
    public void shouldGroupByQuality() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        Map<Integer, Long> qualitiesItems = new HashMap<>();

        //Then
        assertThat(qualitiesItems).hasSize(5).containsOnly(
                entry(0, 1L),
                entry(10, 2L),
                entry(20, 2L),
                entry(25, 1L),
                entry(30, 1L));
    }

    @Test
    public void shouldGetAverageSellIn() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        double averageSellIn = 0;

        //Then
        assertThat(averageSellIn).isEqualTo(15.71, offset(0.01));
    }

    @Test
    public void shouldGetQualityFromAllShops() throws Exception {
        //Given
        List<GildedRose> shops = company.getShops();

        //When
        List<Integer> qualities = new ArrayList<>();

        //Then
        assertThat(qualities).containsOnly(10,0,10,25,20,30,20,0,0,50,49,20,1);
    }

    @Test
    public void howMuchToBuyAllItems() throws Exception {
        //Given
        List<GildedRose> shops = company.getShops();

        //When
        Integer priceOfAllItems = 0;

        //Then
        assertThat(priceOfAllItems).isEqualTo(155);
    }

    @Test
    public void getItemsNotOnSaleInAnyShops() throws Exception {
        //Given
        List<GildedRose> shops = company.getShops();

        //When
        List<String> notSellingItems = new ArrayList<>();

        //Then
        assertThat(notSellingItems).containsOnly("+5 Dexterity Vest", "+5 Dexterity Vest", "Backstage passes to a TAFKAL80ETC concert");
    }
}
