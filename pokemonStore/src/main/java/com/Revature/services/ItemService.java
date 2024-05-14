package com.Revature.services;

import java.util.List;

import com.Revature.daos.ItemDAO;
import com.Revature.models.Item;

public class ItemService {
    private ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public boolean isUnique(String name) {
        List<Item> items = itemDAO.findAll();
        return items.stream().noneMatch(s -> s.getName().equals(name));
    }

    public List<Item> getAllItems() {
        return itemDAO.findAll();
    }
    public Item createItem(String name, int value, int grade, int quantity, String id) {
        Item newItem = new Item(name,value,grade,quantity,id);
        return itemDAO.save(newItem);

    }
    public boolean isValid(String name) {
        return itemDAO.findAll()
        .stream()
        .noneMatch(s -> s.getName().equalsIgnoreCase(name));
    }

    public Item delete(String ID) {
        return itemDAO.delete(ID);
    }

    public Item getItemById(String id) {
        return itemDAO.findByID(id);
    }

    public Item update(Item item) {
        return itemDAO.update(item);
    }
}
