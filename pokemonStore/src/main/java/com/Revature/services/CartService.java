package com.Revature.services;

import com.Revature.daos.CartDAO;
import com.Revature.models.Cart;
import com.Revature.models.User;

public class CartService {
    private CartDAO cartDAO;
    
    public CartService(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public Cart save(Cart cart, User user) {
        cart.setBelongsTo(user.getId());
        return cartDAO.save(cart);
    }

    public Cart save(Cart cart) {
        return cartDAO.save(cart);
    }
    public Cart delete(String id) {
        return cartDAO.delete(id);
    }

    public Cart getCart(String userID) {
        return cartDAO.findByID(userID);
    }
    public Cart findByID(String id) {
        return cartDAO.findByID(id);
    }
    public Cart findByCartID(String id) {
        return cartDAO.findByCartID(id);
    }
}
