package com.Revature.services;

import java.util.List;

import com.Revature.daos.CartProductDAO;
import com.Revature.models.CartProduct;
import com.Revature.models.Item;

public class CartItemService {
    private CartProductDAO cartProductDao;

    public CartItemService(CartProductDAO cartProductDao) {
        this.cartProductDao = cartProductDao;
    }

    public CartProduct save(CartProduct obj) {
        return cartProductDao.save(obj);
    }

    public CartProduct update(CartProduct obj) {
        return cartProductDao.update(obj);
    }

    public Item delete(String cartId, String itemId) {
        return cartProductDao.delete(cartId, itemId);
    }
    public Item delete(String cartId) {
        return cartProductDao.delete(cartId);
    }
    public List<CartProduct> findAllByCartID(String cartID) {
        return cartProductDao.findAllByCartID(cartID);
    }
}
