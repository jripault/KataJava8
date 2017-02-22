package org.codingdojo.java8;

import org.codingdojo.java8.GildedRose;
import org.codingdojo.java8.GildedRoseCompany;
import org.codingdojo.java8.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;
import static org.assertj.core.data.MapEntry.entry;

/**
 * Created by Julien on 22/02/2017.
 */
public class GildedRoseStreamTest {
    private GildedRoseCompany company = new GildedRoseCompany();

    @Test
    public void shouldFilterBySellIn() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        //filter collect

        //Then
        List<Item> items = new ArrayList<>();
        assertThat(items).hasSize(5).extracting("name").containsOnlyOnce("+5 Dexterity Vest");
    }

    @Test
    public void shouldGetItemsQuality() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        //map

        //Then
        List<Integer> itemsQuality = new ArrayList<>();
        assertThat(itemsQuality).containsExactlyInAnyOrder(10,0,10,25,20,30,20);
    }

    @Test
    public void shouldSort() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        //sorted

        //Then
        List<String> itemsName = new ArrayList<>();
        assertThat(itemsName).containsExactly("+5 Dexterity Vest",
                "+5 Dexterity Vest",
                "Aged Brie",
                "Sulfuras, Hand of Ragnaros ",
                "Backstage passes to a TAFKAL80ETC concert",
                "Backstage passes to a TAFKAL80ETC concert",
                "Conjured Mana Cake");
    }

    @Test
    public void shouldSortDescending() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        //custom sort

        //Then
        List<Integer> itemsSellIn = new ArrayList<>();
        assertThat(itemsSellIn).containsExactly(50,20,15,10,10,5,-1);
    }

    @Test
    public void shouldGetTop3Expensive() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        // sort limit

        //Then
        List<Item> items = new ArrayList<>();
        assertThat(items).extracting("name")
                .containsExactly("Sulfuras, Hand of Ragnaros",
                        "Aged Brie",
                        "Backstage passes to a TAFKAL80ETC concert");
    }

    @Test
    public void shouldGetQualityDistinct() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When

        //Then
        List<Integer> itemsQuality = new ArrayList<>();
        assertThat(itemsQuality).containsExactlyInAnyOrder(0,10,20,25,30);
    }

    @Test
    public void shouldCountTotalSellIn() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When

        //Then
        Integer total = 0;
        assertThat(total).isEqualTo(110);
    }

    @Test
    public void shouldGetMaxQuality() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When

        //Then
        Integer maxQuality = 0;
        assertThat(maxQuality).isEqualTo(30);
    }

    @Test
    public void shouldGetMinValue() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        // count

        //Then
        Integer minSellIn = 0;
        assertThat(minSellIn).isEqualTo(0);
    }

    @Test
    public void shouldGetFirst() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When

        //Then
        Item firstItem = null;
        assertThat(firstItem.getName()).isEqualTo("+5 Dexterity Vest");
        assertThat(firstItem.getSellIn()).isEqualTo(10);
        assertThat(firstItem.getQuality()).isEqualTo(10);
    }

    @Test
    public void isThereOneWithNoQuality() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        // anyMatch

        //Then
        boolean hasItemWithNoQuality = false;
        assertThat(hasItemWithNoQuality).isEqualTo(true);
    }

    @Test
    public void areEveryItemNamed() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        // allMatch

        //Then
        boolean allItemsHaveName = false;
        assertThat(allItemsHaveName).isEqualTo(true);
    }

    @Test
    public void shouldCSVItems() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When

        //Then
        String names = "";
        assertThat(names).isEqualTo("[+5 Dexterity Vest,+5 Dexterity Vest,Aged Brie,Sulfuras, Hand of Ragnaros,Backstage passes to a TAFKAL80ETC concert,Backstage passes to a TAFKAL80ETC concert,Conjured Mana Cake]");
    }

    @Test
    public void shouldGroupBySellIn() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When

        //Then
        Map<Integer, List<String>> qualitiesItems = new HashMap<>();
        assertThat(qualitiesItems).hasSize(5).containsOnly(entry(0, null),
                entry(10, null),
                entry(20, null),
                entry(25, null),
                entry(30, null));
    }

    @Test
    public void shouldGetAverageSellIn() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When

        //Then
        double averageSellIn = 0;
        assertThat(averageSellIn).isEqualTo(15.71);
    }


    @Test
    public void shouldGetQualityFromAllShops() throws Exception {
        //Given
        List<GildedRose> shops = company.getShops();

        //When
        // flatMap

        //Then
        List<Integer> qualities = new ArrayList<>();
        assertThat(qualities).containsOnly(10,0,10,25,20,30,20,0,0,50,49,20,1);
    }

    @Test
    public void howMuchToBuyAllItems() throws Exception {
        //Given
        List<GildedRose> shops = company.getShops();

        //When

        //Then
        Integer priceOfAllItems = 0;
        assertThat(priceOfAllItems).isEqualTo(155);
    }

    @Test
    public void getItemsNotOnSaleInAnyShops() throws Exception {
        //Given
        List<GildedRose> shops = company.getShops();

        //When

        //Then
        List<String> notSellingItems = new ArrayList<>();
        assertThat(notSellingItems).containsOnly("+5 Dexterity Vest", "+5 Dexterity Vest", "Backstage passes to a TAFKAL80ETC concert");
    }
}
