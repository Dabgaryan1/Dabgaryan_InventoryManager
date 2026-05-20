package com.daniel.inventorymanager.repository;

import com.daniel.inventorymanager.model.Item;
import java.util.List;
import java.util.Optional;

//this interface extends JpaRepository, which provides basic CRUD operations for the Item entity
public interface ItemRepository extends org.springframework.data.jpa.repository.JpaRepository<com.daniel.inventorymanager.model.Item, Long> {
    List<Item> findByQuantityLessThan(int quantity);
    Optional<Item> findByName(String name);
    List<Item> findByCategory(String category);
    //jpa repository provides basic CRUD operations, so we don't need to define them here
}
