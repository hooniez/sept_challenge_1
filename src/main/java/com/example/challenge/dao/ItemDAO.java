package com.example.challenge.dao;

import com.example.challenge.model.Item;
import com.example.challenge.model.Items;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAO {
    private static Items list = new Items();

    static {
        list.getItemList().add(new Item(1L, "item 1", "this is item 1", 12.0));
        list.getItemList().add(new Item(2L, "item 2", "this is item 2", 12.0));
        list.getItemList().add(new Item(3L, "item 3", "this is item 3", 12.0));
    }

    public Items getAllItems() {
        return list;
    }

    public void addItem(Item item) {
        list.getItemList().add(item);
    }
}
