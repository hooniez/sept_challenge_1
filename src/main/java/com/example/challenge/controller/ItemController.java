package com.example.challenge.controller;

import com.example.challenge.dao.ItemDAO;
import com.example.challenge.model.Item;
import com.example.challenge.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
        Item item;
        if (itemDao.getItemById(id).isPresent()) {
            item = itemDao.getItemById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please" +
                    " enter a valid id");
        }
        return item;
    }

    @DeleteMapping(path="/item{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        ResponseEntity<?> re;
        if (itemDao.deleteItemById(id)) {
            re = ResponseEntity.ok("Successfully removed");
        } else {
            re = ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please " +
                    "delete an existing item");
        }
        return re;
    }

    @PutMapping(path="/item{id}")
    public Item update(@PathVariable("id") Long id,
                                       @RequestParam("name") String name,
                                       @RequestParam("desc") String desc,
                                       @RequestParam("price") double price) {
        return itemDao.update(id, name, desc, price);
    }
}
