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

import com.Revature.daos.StoreDAO;
import com.Revature.models.Store;
import com.Revature.services.StoreService;

public class StoreServiceTest {
    private StoreDAO storeDaoMock;
    private StoreService storeService;

    @Before
    public void setUp() {
        storeDaoMock = mock(StoreDAO.class);
        storeService = new StoreService(storeDaoMock);
    }

    @Test
    public void testIsUnique_ReturnsTrueWhenNameIsUnique() {
        // Arrange
        when(storeDaoMock.findAll()).thenReturn(Arrays.asList(new Store("Store1"), new Store("Store2")));

        // Act
        boolean result = storeService.isUnique("NewStore");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsUnique_ReturnsFalseWhenNameExists() {
        // Arrange
        when(storeDaoMock.findAll()).thenReturn(Arrays.asList(new Store("Store1"), new Store("Store2")));

        // Act
        boolean result = storeService.isUnique("Store1");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetAllStores_ReturnsAllStores() {
        // Arrange
        List<Store> expectedStores = Arrays.asList(new Store("Store1"), new Store("Store2"));
        when(storeDaoMock.findAll()).thenReturn(expectedStores);

        // Act
        List<Store> result = storeService.getAllStores();

        // Assert
        assertEquals(expectedStores, result);
    }

    // @Test
    // public void testCreateStore_SuccessfulCreation() {
    //     // Arrange
    //     String storeName = "NewStore";
    //     Store newStore = new Store(storeName);
    //     when(storeDaoMock.save(newStore)).thenReturn(newStore);

    //     // Act
    //     Store createdStore = storeService.createStore(storeName);

    //     // Assert
    //     assertEquals(newStore.getName(), createdStore.getName());
    // }

    // @Test
    // public void testFindStoreByName_StoreExists() {
    //     // Arrange
    //     String storeName = "ExistingStore";
    //     Store existingStore = new Store();
    //     existingStore.setName(storeName);
    //     when(storeDaoMock.findByName(storeName)).thenReturn(Optional.of(existingStore));

    //     // Act
    //     Store foundStore = storeService.findStoreByName(storeName);

    //     // Assert
    //     assertEquals(existingStore, foundStore);
    // }

    // @Test
    // public void testFindStoreByName_StoreDoesNotExist() {
    //     // Arrange
    //     String storeName = "NonExistingStore";
    //     when(storeDaoMock.findByName(storeName)).thenReturn(Optional.empty());

    //     // Act
    //     Store foundStore = storeService.findStoreByName(storeName);

    //     // Assert
    //     assertNull(foundStore);
    // }

    @Test
    public void testIsValid_NameIsUnique() {
        // Arrange
        String name = "UniqueStore";
        List<Store> stores = Arrays.asList(new Store("Store1"), new Store("Store2"));
        when(storeDaoMock.findAll()).thenReturn(stores);

        // Act
        boolean isValid = storeService.isValid(name);

        // Assert
        assertTrue(isValid);
    }

    @Test
    public void testIsValid_NameExists() {
        // Arrange
        String name = "ExistingStore";
        List<Store> stores = Arrays.asList(new Store("Store1"), new Store("ExistingStore"));
        when(storeDaoMock.findAll()).thenReturn(stores);

        // Act
        boolean isValid = storeService.isValid(name);

        // Assert
        assertFalse(isValid);
    }
}
