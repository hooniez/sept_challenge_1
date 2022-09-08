package com.example.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private List<Item> itemList;

    public List<Item> getItemList() {
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
