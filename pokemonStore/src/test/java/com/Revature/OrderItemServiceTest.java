package com.Revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.Revature.daos.OrderItemDAO;
import com.Revature.models.Item;
import com.Revature.models.OrderItem;
import com.Revature.services.OrderItemService;

public class OrderItemServiceTest {
    private OrderItemDAO orderItemDaoMock;
    private OrderItemService orderItemService;

    @Before
    public void setUp() {
        orderItemDaoMock = mock(OrderItemDAO.class);
        orderItemService = new OrderItemService(orderItemDaoMock);
    }

    @Test
    public void testSaveOrderItem() {
        // Arrange
        OrderItem orderItem = new OrderItem();
        when(orderItemDaoMock.save(orderItem)).thenReturn(orderItem);

        // Act
        OrderItem savedOrderItem = orderItemService.save(orderItem);

        // Assert
        assertEquals(orderItem, savedOrderItem);
    }

    @Test
    public void testUpdateOrderItem() {
        // Arrange
        OrderItem orderItem = new OrderItem();
        when(orderItemDaoMock.update(orderItem)).thenReturn(orderItem);

        // Act
        OrderItem updatedOrderItem = orderItemService.update(orderItem);

        // Assert
        assertEquals(orderItem, updatedOrderItem);
    }

    @Test
    public void testDeleteOrderItem() {
        // Arrange
        String storeId = "storeId";
        String itemId = "itemId";
        Item deletedItem = new Item(); // Assuming Item is returned by the delete method
        when(orderItemDaoMock.delete(storeId, itemId)).thenReturn(deletedItem);

        // Act
        Item result = orderItemService.delete(storeId, itemId);

        // Assert
        assertEquals(deletedItem, result);
    }

    @Test
    public void testGetAllOrderItems() {
        // Arrange
        List<OrderItem> orderItems = Arrays.asList(new OrderItem(), new OrderItem());
        when(orderItemDaoMock.findAll()).thenReturn(orderItems);

        // Act
        List<OrderItem> retrievedOrderItems = orderItemService.getAll();

        // Assert
        assertEquals(orderItems, retrievedOrderItems);
    }
}
