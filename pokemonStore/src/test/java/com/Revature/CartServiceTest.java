package com.Revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.Revature.daos.CartDAO;
import com.Revature.models.Cart;
import com.Revature.models.User;
import com.Revature.services.CartService;

public class CartServiceTest {
    private CartDAO cartDaoMock;
    private CartService cartService;

    @Before
    public void setUp() {
        cartDaoMock = mock(CartDAO.class);
        cartService = new CartService(cartDaoMock);
    }

    @Test
    public void testSaveCartWithUser() {
        // Arrange
        Cart cart = new Cart();
        User user = new User();
        user.setId("userId");
        cart.setBelongsTo(user.getId());
        when(cartDaoMock.save(cart)).thenReturn(cart);

        // Act
        Cart savedCart = cartService.save(cart, user);

        // Assert
        assertEquals(cart, savedCart);
        assertEquals(user.getId(), savedCart.getBelongsTo());
    }

    @Test
    public void testSaveCartWithoutUser() {
        // Arrange
        Cart cart = new Cart();
        when(cartDaoMock.save(cart)).thenReturn(cart);

        // Act
        Cart savedCart = cartService.save(cart);

        // Assert
        assertEquals(cart, savedCart);
    }

    @Test
    public void testDeleteCart() {
        // Arrange
        String cartId = "cartId";
        Cart deletedCart = new Cart();
        when(cartDaoMock.delete(cartId)).thenReturn(deletedCart);

        // Act
        Cart result = cartService.delete(cartId);

        // Assert
        assertEquals(deletedCart, result);
    }

    @Test
    public void testGetCart() {
        // Arrange
        String userId = "userId";
        Cart cart = new Cart();
        when(cartDaoMock.findByID(userId)).thenReturn(cart);

        // Act
        Cart result = cartService.getCart(userId);

        // Assert
        assertEquals(cart, result);
    }

    @Test
    public void testFindByID() {
        // Arrange
        String cartId = "cartId";
        Cart cart = new Cart();
        when(cartDaoMock.findByID(cartId)).thenReturn(cart);

        // Act
        Cart result = cartService.findByID(cartId);

        // Assert
        assertEquals(cart, result);
    }

    @Test
    public void testFindByCartID() {
        // Arrange
        String cartId = "cartId";
        Cart cart = new Cart();
        when(cartDaoMock.findByCartID(cartId)).thenReturn(cart);

        // Act
        Cart result = cartService.findByCartID(cartId);

        // Assert
        assertEquals(cart, result);
    }
}
