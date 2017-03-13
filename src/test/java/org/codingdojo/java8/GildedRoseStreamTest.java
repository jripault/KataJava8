package org.codingdojo.java8;

import org.junit.Test;

import java.util.*;

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
        //List<Item> items = shop.getItems().stream().filter(item -> item.getSellIn() == 10).collect(Collectors.toList());
        List<Item> items = new ArrayList<>();
        for (Item item : shop.getItems()) {
            if (item.getSellIn() == 10) {
                items.add(item);
            }
        }

        //Then
        assertThat(items).hasSize(2).extracting("name").containsOnly("+5 Dexterity Vest", "Conjured Mana Cake");
    }

    @Test
    public void shouldGetItemsQuality() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        //List<Integer> itemsQuality = shop.getItems().stream().map(Item::getQuality).collect(Collectors.toList());
        List<Integer> itemsQuality = new ArrayList<>();
        for (Item item : shop.getItems()) {
            itemsQuality.add(item.getQuality());
        }

        //Then
        assertThat(itemsQuality).containsExactlyInAnyOrder(10, 0, 10, 25, 20, 30, 20);
    }

    @Test
    public void shouldSortByName() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        //List<String> itemsName = shop.getItems().stream().map(Item::getName).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        List<String> itemsName = new ArrayList<>();
        for (Item item : shop.getItems()) {
            itemsName.add(item.getName());
        }
        Collections.sort(itemsName);

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
//        List<Integer> itemsSellIn = shop.getItems().stream()
//                .map(Item::getSellIn)
//                .sorted(Comparator.reverseOrder())
//                .collect(Collectors.toList());
        List<Integer> itemsSellIn = new ArrayList<>();
        for (Item item : shop.getItems()) {
            itemsSellIn.add(item.getSellIn());
        }
        Collections.sort(itemsSellIn, Collections.<Integer>reverseOrder());

        //Then
        assertThat(itemsSellIn).containsExactly(50, 20, 15, 10, 10, 5, 0);
    }

    @Test
    public void shouldGetTop3Expensive() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
//        List<Item> items = shop.getItems().stream()
//                .sorted(Comparator.comparing(Item::getSellIn).reversed())
//                // Or: .sorted((o1, o2) -> o2.getSellIn() - o1.getSellIn())
//                .limit(3)
//                .collect(Collectors.toList());

        List<Item> items = new ArrayList<>();
        items.addAll(shop.getItems());
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return Integer.valueOf(item2.getSellIn()).compareTo(item1.getSellIn());
            }
        });
        items = items.subList(0, 3);

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

//        List<Integer> itemsQuality = shop.getItems().stream()
//                .map(Item::getQuality)
//                .distinct()
//                .collect(Collectors.toList());
        Set<Integer> tmp = new HashSet<>();
        for (Item item : shop.getItems()) {
            tmp.add(item.getQuality());
        }
        List<Integer> itemsQuality = new ArrayList<>();
        itemsQuality.addAll(tmp);

        //Then
        assertThat(itemsQuality).containsExactlyInAnyOrder(0, 10, 20, 25, 30);
    }

    @Test
    public void shouldCountTotalSellIn() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        // Integer total = shop.getItems().stream().mapToInt(Item::getSellIn).sum();
        Integer total = 0;
        for (Item item : shop.getItems()) {
            total += item.getSellIn();
        }

        //Then
        assertThat(total).isEqualTo(110);
    }

    @Test
    public void shouldGetMaxQuality() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        // Integer maxQuality = shop.getItems().stream().mapToInt(Item::getQuality).max().getAsInt();
        Integer maxQuality = 0;
        for (Item item : shop.getItems()) {
            maxQuality = item.getQuality() > maxQuality ? item.getQuality() : maxQuality;
        }

        //Then
        assertThat(maxQuality).isEqualTo(30);
    }

    @Test
    public void shouldGetMinValue() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        // Integer minSellIn = shop.getItems().stream().mapToInt(Item::getSellIn).min().getAsInt();
        Integer minSellIn = Integer.MAX_VALUE;
        for (Item item : shop.getItems()) {
            minSellIn = item.getSellIn() < minSellIn ? item.getSellIn() : minSellIn;
        }

        //Then
        assertThat(minSellIn).isEqualTo(0);
    }

    @Test
    public void shouldGetFirst() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        // Item firstItem = shop.getItems().stream().findFirst().get();
        Iterator<Item> iterator = shop.getItems().iterator();
        Item firstItem = iterator.hasNext() ? iterator.next() : null;

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
        // boolean hasItemWithNoQuality = shop.getItems().stream()
        //      .map(Item::getQuality).anyMatch(quality -> quality > 0);
        // Or: .anyMatch(item -> item.getQuality() > 0);

        boolean hasItemWithNoQuality = false;
        for (Item item : shop.getItems()) {
            if (item.getQuality() <= 0) {
                hasItemWithNoQuality = true;
                break;
            }
        }

        //Then
        assertThat(hasItemWithNoQuality).isEqualTo(true);
    }

    @Test
    public void areEveryItemNamed() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        // boolean allItemsHaveName = shop.getItems().stream()
        //   .map(Item::getName).noneMatch(Strings::isNullOrEmpty);
        // Or: .allMatch(item -> !Strings.isNullOrEmpty(item.getName()));

        boolean allItemsHaveName = true;
        for (Item item : shop.getItems()) {
            if (item.getName().isEmpty()) {
                allItemsHaveName = false;
                break;
            }
        }

        //Then
        assertThat(allItemsHaveName).isEqualTo(true);
    }

    @Test
    public void shouldCSVItems() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        //String namesBuilder = shop.getItems().stream().map(Item::getName).collect(Collectors.joining(",", "[", "]"));
        List<Item> items = shop.getItems();
        StringBuilder namesBuilder = new StringBuilder("[");
        for (int i = 0, itemsSize = items.size(); i < itemsSize; i++) {
            namesBuilder.append(items.get(i).getName());
            if (i < itemsSize - 1) {
                namesBuilder.append(",");
            }
        }
        namesBuilder.append("]");
        String names = namesBuilder.toString();

        //Then
        assertThat(names).isEqualTo("[+5 Dexterity Vest,+5 Dexterity Vest,Aged Brie,Sulfuras, Hand of Ragnaros,Backstage passes to a TAFKAL80ETC concert,Backstage passes to a TAFKAL80ETC concert,Conjured Mana Cake]");
    }

    @Test
    public void shouldGroupBySellIn() throws Exception {
        //Given
        GildedRose shop = company.shop();

        //When
        // Map<Integer, Long> qualitiesItems = shop.getItems().stream()
        //        .collect(Collectors.groupingBy(Item::getQuality, Collectors.counting()));
        Map<Integer, Long> qualitiesItems = new HashMap<>();
        for (Item item : shop.getItems()) {
            Long previous = qualitiesItems.get(item.getQuality());
            if (previous == null) {
                previous = 0L;
            }
            qualitiesItems.put(item.getQuality(), ++previous);
        }

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
        //double averageSellIn = shop.getItems().stream().mapToInt(Item::getSellIn).average().getAsDouble();
        double total = 0;
        for (Item item : shop.getItems()) {
            total += item.getSellIn();
        }
        double averageSellIn = shop.getItems().size() >= 1 ? total / shop.getItems().size() : 0;

        //Then
        assertThat(averageSellIn).isEqualTo(15.71, offset(0.01));
    }

    @Test
    public void shouldGetQualityFromAllShops() throws Exception {
        //Given
        List<GildedRose> shops = company.getShops();

        //When
        //List<Integer> qualities = shops.stream()
        //        .map(GildedRose::getItems)
        //        .flatMap(List::stream)
        //        //Or: .flatMap(gildedRose -> gildedRose.getItems().stream())
        //        .map(Item::getQuality).collect(Collectors.toList());

        List<Integer> qualities = new ArrayList<>();
        for (GildedRose shop : shops) {
            for (Item item : shop.getItems()) {
                qualities.add(item.getQuality());
            }
        }

        //Then
        assertThat(qualities).containsOnly(10, 0, 10, 25, 20, 30, 20, 0, 0, 50, 49, 20, 1);
    }

    @Test
    public void howMuchToBuyAllItems() throws Exception {
        //Given
        List<GildedRose> shops = company.getShops();

        //When
        //Integer priceOfAllItems = shops.stream()
        //        .map(GildedRose::getItems)
        //        .flatMap(List::stream)
        //        .mapToInt(Item::getSellIn).sum();

        Integer priceOfAllItems = 0;
        for (GildedRose shop : shops) {
            for (Item item : shop.getItems()) {
                priceOfAllItems += item.getSellIn();
            }
        }

        //Then
        assertThat(priceOfAllItems).isEqualTo(155);
    }

    @Test
    public void getItemsNotOnSaleInAnyShops() throws Exception {
        //Given
        List<GildedRose> shops = company.getShops();

        //When
        //List<String> notSellingItems = shops.stream()
        //        .map(GildedRose::getItems)
        //        .flatMap(List::stream)
        //        .filter(item -> item.getSellIn() == 0)
        //        .map(Item::getName).collect(Collectors.toList());

        Map<String, List<Integer>> sellingByItem = new HashMap<>();
        for (GildedRose shop : shops) {
            for (Item item : shop.getItems()) {
                List<Integer> value = sellingByItem.get(item.getName());
                if (value == null) {
                    value = new ArrayList<>();
                }
                value.add(item.getSellIn());
                sellingByItem.put(item.getName(), value);
            }
        }

        List<String> notSellingItems = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : sellingByItem.entrySet()) {
            List<Integer> value = entry.getValue();
            if (value.contains(0)) {
                notSellingItems.add(entry.getKey());
            }
        }

        //Then
        assertThat(notSellingItems).containsOnly("+5 Dexterity Vest", "+5 Dexterity Vest", "Backstage passes to a TAFKAL80ETC concert");
    }
}
