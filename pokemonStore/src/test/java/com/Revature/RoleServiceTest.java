package com.Revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.Revature.daos.RoleDAO;
import com.Revature.models.Role;
import com.Revature.services.RoleService;

public class RoleServiceTest {
    private RoleDAO roleDaoMock;
    private RoleService roleService;

    @Before
    public void setUp() {
        roleDaoMock = mock(RoleDAO.class);
        roleService = new RoleService(roleDaoMock);
    }

    @Test
    public void testGetRoleIDByName_RoleExists() {
        // Arrange
        String roleName = "Admin";
        String roleId = "123";
        Role role = new Role(roleId, roleName);
        when(roleDaoMock.findAll()).thenReturn(Arrays.asList(role));

        // Act
        String foundRoleId = roleService.getRoleIDByName(roleName);

        // Assert
        assertEquals(roleId, foundRoleId);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetRoleIDByName_RoleDoesNotExist() {
        // Arrange
        String roleName = "NonExistingRole";
        when(roleDaoMock.findAll()).thenReturn(Arrays.asList());

        // Act
        String foundRoleId = roleService.getRoleIDByName(roleName);

        // Assert
        // NoSuchElementException should be thrown
    }
}
