package com.Revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.Revature.daos.OrderDAO;
import com.Revature.models.Order;
import com.Revature.services.OrderService;

public class OrderServiceTest {

    private OrderDAO orderDaoMock;
    private OrderService orderService;

    @Before
    public void setUp() {
        orderDaoMock = mock(OrderDAO.class);
        orderService = new OrderService(orderDaoMock);
    }

    @Test
    public void testSaveOrder() {
        // Arrange
        Order order = new Order();
        when(orderDaoMock.addOrder(order)).thenReturn(order);

        // Act
        Order savedOrder = orderService.save(order);

        // Assert
        assertEquals(order, savedOrder);
    }

    @Test
    public void testGetAllOrders() {
        // Arrange
        List<Order> orders = Arrays.asList(new Order(), new Order());
        when(orderDaoMock.getAllOrders()).thenReturn(orders);

        // Act
        List<Order> retrievedOrders = orderService.getAllOrders();

        // Assert
        assertEquals(orders, retrievedOrders);
    }

    @Test
    public void testGetAllOrdersByUserID() {
        // Arrange
        String userId = "123";
        List<Order> orders = Arrays.asList(new Order(), new Order());
        when(orderDaoMock.getAllOrdersByUserID(userId)).thenReturn(orders);

        // Act
        List<Order> retrievedOrders = orderService.getAllByUserID(userId);

        // Assert
        assertEquals(orders, retrievedOrders);
    }
}
