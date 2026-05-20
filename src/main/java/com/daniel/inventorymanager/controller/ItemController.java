package com.daniel.inventorymanager.controller;

import com.daniel.inventorymanager.model.Item;
import com.daniel.inventorymanager.service.ItemService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//this class is responsible for handling HTTP requests related to items,
// such as creating, updating, deleting, and retrieving items from the database
@RestController
public class ItemController {
    private final ItemService itemService;

    //constructor injection of the service
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //endpoint to get all items from the database
    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    //endpoint to add a new item to the database
    @PostMapping("/items")
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    //endpoint to get an item by name from the database
    @GetMapping("/items/name/{name}")
    public Item getItemByName(@PathVariable String name) {
        return itemService.getItemByName(name);
    }

    //endpoint to get items by category from the database
    @GetMapping("/items/category/{category}")
    public List<Item> getItemsByCategory(@PathVariable String category) {
        return itemService.getItemsByCategory(category);
    }

    //endpoint to get items with low stock from the database
    @GetMapping("/items/low-stock/{threshold}")
    public List<Item> getLowStockItems(@PathVariable int threshold) {
        return itemService.getLowStockItems(threshold);
    }

    //endpoint to get an item by id from the database
    @GetMapping("/items/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    //endpoint to update an item in the database
    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        return itemService.updateItem(id, updatedItem);
    }

    //endpoint to delete an item from the database
    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

}
