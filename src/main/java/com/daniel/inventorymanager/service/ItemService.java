package com.daniel.inventorymanager.service;

import org.springframework.stereotype.Service;
import com.daniel.inventorymanager.model.Item;
import com.daniel.inventorymanager.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {
    //this class contains all business logic for
    //managing items, such as adding, updating,
    // deleting, and retrieving items from the database
    private final ItemRepository itemRepository;    //repository for database operations

    //constructor injection of the repository
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //add item to database
    public Item addItem(Item item) {
        //name cannot be empty
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new RuntimeException("Item name cannot be empty");
        }
        //quantity cannot be negative
        if (item.getQuantity() < 0) {
            throw new RuntimeException("Item quantity cannot be negative");
        }
        return itemRepository.save(item);
    }

    //get all items from database
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    //get item by name from database
    public Item getItemByName(String name) {
        return itemRepository.findByName(name).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    //get items by category from database
    public List<Item> getItemsByCategory(String category) {
        return itemRepository.findByCategory(category);
    }

    //get items with low stock from database
    public List<Item> getLowStockItems(int threshold) {
        return itemRepository.findByQuantityLessThan(threshold);
    }

    //get item by id from database
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    //update item in database
    public Item updateItem(Long id, Item updatedItem) {
        //validate that item exists
        Item existingItem = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));

        //validate that updated item name is not empty
        if (updatedItem.getName() == null || updatedItem.getName().isEmpty()) {
            throw new RuntimeException("Item name cannot be empty");
        }
        //ensure updated items quantity is negative
        if(updatedItem.getQuantity() < 0) {
            throw new RuntimeException("Item quantity cannot be negative");
        }
        existingItem.setName(updatedItem.getName());
        existingItem.setQuantity(updatedItem.getQuantity());
        existingItem.setCategory(updatedItem.getCategory());
        return itemRepository.save(existingItem);
    }

    //delete item from database
    public void deleteItem(Long id) {
        //checks if item exists
        if (!itemRepository.existsById(id)) {
            throw new RuntimeException("Item not found");
        }
        itemRepository.deleteById(id);
    }
}
