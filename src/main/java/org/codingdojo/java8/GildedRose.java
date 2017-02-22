package org.codingdojo.java8;

import java.util.List;

/**
 * Created by Julien on 22/02/2017.
 */
public class GildedRose {
    final List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}
