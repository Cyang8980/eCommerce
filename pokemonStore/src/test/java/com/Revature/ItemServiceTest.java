package com.Revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.Revature.daos.ItemDAO;
import com.Revature.models.Item;
import com.Revature.models.OrderItem;
import com.Revature.services.ItemService;

public class ItemServiceTest {
    private ItemDAO itemDaoMock;
    private ItemService itemService;

    @Before
    public void setUp() {
        itemDaoMock = mock(ItemDAO.class);
        itemService = new ItemService(itemDaoMock);
    }

    @Test
    public void testIsUnique_ItemIsUnique() {
        // Arrange
        String itemName = "UniqueItem";
        List<Item> items = Arrays.asList(new Item("Item1", 0), new Item("Item2", 0));
        when(itemDaoMock.findAll()).thenReturn(items);

        // Act
        boolean isUnique = itemService.isUnique(itemName);

        // Assert
        assertTrue(isUnique);
    }

    @Test
    public void testIsUnique_ItemAlreadyExists() {
        // Arrange
        String itemName = "ExistingItem";
        List<Item> items = Arrays.asList(new Item("Item1", 0), new Item("ExistingItem", 0));
        when(itemDaoMock.findAll()).thenReturn(items);

        // Act
        boolean isUnique = itemService.isUnique(itemName);

        // Assert
        assertFalse(isUnique);
    }

    @Test
    public void testGetAllItems() {
        // Arrange
        List<Item> items = Arrays.asList(new Item(), new Item());
        when(itemDaoMock.findAll()).thenReturn(items);

        // Act
        List<Item> retrievedItems = itemService.getAllItems();

        // Assert
        assertEquals(items, retrievedItems);
    }

    // @Test
    // public void testCreateItem() {
    //     // Arrange
    //     Item newItem = new Item("NewItem", 10, 1, 5, "123");
    //     when(itemDaoMock.save(newItem)).thenReturn(newItem);

    //     // Act
    //     Item createdItem = itemService.createItem("NewItem1", 10, 1, 5, "1234");

    //     // Assert
    //     assertEquals(newItem, createdItem);
    // }

    @Test
    public void testIsValid_ItemIsUnique() {
        // Arrange
        String itemName = "UniqueItem";
        List<Item> items = Arrays.asList(new Item("Item1", 0), new Item("Item2", 0));
        when(itemDaoMock.findAll()).thenReturn(items);

        // Act
        boolean isValid = itemService.isValid(itemName);

        // Assert
        assertTrue(isValid);
    }

    @Test
    public void testIsValid_ItemAlreadyExists() {
        // Arrange
        String itemName = "ExistingItem";
        List<Item> items = Arrays.asList(new Item("Item1", 0), new Item("ExistingItem", 0));
        when(itemDaoMock.findAll()).thenReturn(items);

        // Act
        boolean isValid = itemService.isValid(itemName);

        // Assert
        assertFalse(isValid);
    }

    @Test
    public void testDeleteItem() {
        // Arrange
        String itemId = "123";
        Item deletedItem = new Item();
        when(itemDaoMock.delete(itemId)).thenReturn(deletedItem);

        // Act
        Item result = itemService.delete(itemId);

        // Assert
        assertEquals(deletedItem, result);
    }

    @Test
    public void testGetItemById() {
        // Arrange
        String itemId = "123";
        Item item = new Item();
        when(itemDaoMock.findByID(itemId)).thenReturn(item);

        // Act
        Item result = itemService.getItemById(itemId);

        // Assert
        assertEquals(item, result);
    }
    @Test
    public void testUpdateItem() {
        // Arrange
        Item item = new Item();
        item.setItem_id("itemId");
        item.setName("itemName");
        item.setGrade(10);
        item.setQuantity(1);
        item.setValue(1);
        when(itemDaoMock.update(item)).thenReturn(item);

        // Act
        Item updatedItem = itemService.update(item);

        // Assert
        assertEquals(item, updatedItem);
    }
}
