package com.Revature.services;

import java.util.List;

import com.Revature.daos.OrderDAO;
import com.Revature.models.Order;

public class OrderService {
    private OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    public Order save(Order order) {
        return orderDAO.addOrder(order);
    }
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }
    public List<Order> getAllByUserID(String id) {
        return orderDAO.getAllOrdersByUserID(id);
    }
}
