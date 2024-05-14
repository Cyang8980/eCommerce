package com.Revature.services;

import java.util.List;

import com.Revature.daos.OrderItemDAO;
import com.Revature.models.Item;
import com.Revature.models.OrderItem;

public class OrderItemService {
    OrderItemDAO orderItemDAO;

    public OrderItemService(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    public OrderItem save(OrderItem obj) {
        return orderItemDAO.save(obj);
    }

    public OrderItem update(OrderItem obj) {
        return orderItemDAO.update(obj);
    }

    public Item delete(String storeId, String itemId) {
        return orderItemDAO.delete(storeId, itemId);
    }
    public List<OrderItem> getAll() {
        return orderItemDAO.findAll();
    }
    public List<OrderItem> getAllByOrderID(String ID) {
        return orderItemDAO.findAllByOrderID(ID);
    }
}
