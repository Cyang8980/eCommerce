package com.Revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.Revature.daos.CartProductDAO;
import com.Revature.models.CartProduct;
import com.Revature.models.Item;
import com.Revature.services.CartItemService;

public class CartItemServiceTest {

    private CartProductDAO cartProductDaoMock;
    private CartItemService cartItemService;

    @Before
    public void setUp() {
        cartProductDaoMock = mock(CartProductDAO.class);
        cartItemService = new CartItemService(cartProductDaoMock);
    }

    @Test
    public void testSaveCartItem() {
        // Arrange
        CartProduct cartProduct = new CartProduct();
        when(cartProductDaoMock.save(cartProduct)).thenReturn(cartProduct);

        // Act
        CartProduct savedCartItem = cartItemService.save(cartProduct);

        // Assert
        assertEquals(cartProduct, savedCartItem);
    }

    @Test
    public void testUpdateCartItem() {
        // Arrange
        CartProduct cartProduct = new CartProduct();
        when(cartProductDaoMock.update(cartProduct)).thenReturn(cartProduct);

        // Act
        CartProduct updatedCartItem = cartItemService.update(cartProduct);

        // Assert
        assertEquals(cartProduct, updatedCartItem);
    }

    @Test
    public void testDeleteCartItem() {
        // Arrange
        String cartId = "cartId";
        String itemId = "itemId";
        Item deletedItem = new Item();
        when(cartProductDaoMock.delete(cartId, itemId)).thenReturn(deletedItem);

        // Act
        Item result = cartItemService.delete(cartId, itemId);

        // Assert
        assertEquals(deletedItem, result);
    }

    @Test
    public void testFindAllByCartID() {
        // Arrange
        String cartID = "cartID";
        List<CartProduct> cartProducts = Arrays.asList(new CartProduct(), new CartProduct());
        when(cartProductDaoMock.findAllByCartID(cartID)).thenReturn(cartProducts);

        // Act
        List<CartProduct> retrievedCartProducts = cartItemService.findAllByCartID(cartID);

        // Assert
        assertEquals(cartProducts, retrievedCartProducts);
    }
}
