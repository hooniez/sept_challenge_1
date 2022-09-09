package com.example.challenge.dao;

import com.example.challenge.model.Item;
import com.example.challenge.model.Items;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemDAO {
    private static final Items list = new Items();

    static {
        list.getItemList().add(new Item(1L, "item 1", "this is item 1", 12.0));
        list.getItemList().add(new Item(2L, "item 2", "this is item 2", 12.0));
        list.getItemList().add(new Item(3L, "item 3", "this is item 3", 12.0));
    }

    public Items getAllItems() {
        return list;
    }

    public Optional<Item> getItemById(Long id) {
        return list.getItemList().stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public boolean deleteItemById(Long id) {
        return list.getItemList().removeIf(item -> item.getId().equals(id));
    }

    public void addItem(Item item) {
        list.getItemList().add(item);
    }

    public Item update(Long id, String name, String desc, double price) {
        Item item;
        if (getItemById(id).isPresent()) {
            item = getItemById(id).get();
            item.setName(name);
            item.setDesc(desc);
            item.setPrice(price);
        } else {
            id =
                    list.getItemList().get(list.getItemList().size() - 1).getId() + 1;
            item =
                    new Item(id, name, desc, price);
            addItem(item);
        }
        return item;
    }
}
