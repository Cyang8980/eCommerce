package com.Revature.services;

import java.util.List;

import com.Revature.daos.StoreDAO;
import com.Revature.models.Store;

public class StoreService {

    private StoreDAO storeDAO;

    public StoreService(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    public boolean isUnique(String name) {
        List<Store> stores = storeDAO.findAll();
        return stores.stream().noneMatch(s -> s.getName().equals(name));
    }

    public List<Store> getAllStores() {
        return storeDAO.findAll();
    }
    public Store createStore(String name) {
        Store newStore = new Store(name);
        return storeDAO.save(newStore);
    }

    public Store findStoreByName(String name) {
        return storeDAO.findByName(name);
    }

    public boolean isValid(String name) {
        return storeDAO.findAll()
        .stream()
        .noneMatch(s -> s.getName().equalsIgnoreCase(name));
    }
}
