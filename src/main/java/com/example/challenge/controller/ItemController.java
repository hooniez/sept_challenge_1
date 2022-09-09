package com.example.challenge.controller;

import com.example.challenge.dao.ItemDAO;
import com.example.challenge.exception.ItemNotFoundException;
import com.example.challenge.model.Item;
import com.example.challenge.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/item")
public class ItemController {
    @Autowired
    private ItemDAO itemDao;

    @GetMapping(path="/items", produces="application/json")
    public Items getItems() {
        return itemDao.getAllItems();
    }

    @GetMapping(path="/item{id}")
    public Item getItem(@PathVariable("id") Long id) {
        // If out-of-index bound
        if (itemDao.getItemById(id).isEmpty()) {
            throw new ItemNotFoundException();
        }
        return itemDao.getItemById(id).get();
    }

    @DeleteMapping(path="/item{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!itemDao.deleteItemById(id)) {
            System.out.println("yes");
            throw new ItemNotFoundException();
        }
        return ResponseEntity.ok("Successfully removed");
    }

    @PutMapping(path="/item{id}")
    public Item update(@PathVariable("id") Long id,
                                       @RequestParam("name") String name,
                                       @RequestParam("desc") String desc,
                                       @RequestParam("price") double price) {
        return itemDao.update(id, name, desc, price);
    }
}
