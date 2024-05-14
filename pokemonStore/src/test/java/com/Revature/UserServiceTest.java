package com.Revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.Revature.daos.UserDAO;
import com.Revature.models.User;
import com.Revature.services.RoleService;
import com.Revature.services.UserService;
import com.Revature.utils.custom_exceptions.ResourceNotFoundException;

public class UserServiceTest {
    private UserDAO userDaoMock;
    private RoleService roleServiceMock;
    private UserService userService;

    @Before
    public void setup() {
        userDaoMock = mock(UserDAO.class);
        roleServiceMock = mock(RoleService.class);
        userService = new UserService(userDaoMock, roleServiceMock);
    }

    @Test
    public void testUniqueUsername_ReturnsTrueWhenNoMatchingUsernames() {
        // Arrange
        when(userDaoMock.findAll()).thenReturn(new ArrayList<>());

        // Act
        boolean result = userService.uniqueUsername("newUsername");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testUniqueUsername_ReturnsFalseWhenMatchingUsernameExists() {
        // Arrange
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUsername("existingUsername");
        users.add(user);
        when(userDaoMock.findAll()).thenReturn(users);

        // Act
        boolean result = userService.uniqueUsername("existingUsername");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testUniqueUsername_ReturnsTrueWhenUsernameIsUnique() {
        // Arrange
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUsername("existingUsername");
        users.add(user);
        when(userDaoMock.findAll()).thenReturn(users);

        // Act
        boolean result = userService.uniqueUsername("newUsername");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsValidUsername_ReturnsTrueForValidUsername() {
        // Arrange
        UserService userService = new UserService(userDaoMock, roleServiceMock);

        // Act
        boolean result = userService.isValidUsername("validUsername123");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsValidUsername_ReturnsFalseForInvalidUsername() {
        // Arrange
        UserService userService = new UserService(userDaoMock, roleServiceMock);

        // Act
        boolean result = userService.isValidUsername("invalid Username");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testValidPassword_ReturnsTrueForValidPassword() {
        // Arrange
        UserService userService = new UserService(userDaoMock, roleServiceMock);

        // Act
        boolean result = userService.validPassword("Strong@123");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testValidPassword_ReturnsFalseForInvalidPassword() {
        // Arrange
        UserService userService = new UserService(userDaoMock, roleServiceMock);

        // Act
        boolean result = userService.validPassword("weakpassword");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSave_SuccessfulSave() {
        // Arrange
        User user = new User();
        user.setUsername("example_user");
        user.setPassword("example_password");
        when(roleServiceMock.getRoleIDByName("DEFAULT")).thenReturn("default_role_id");
        when(userDaoMock.save(any(User.class))).thenReturn(user);

        // Act
        User savedUser = userService.save(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals("default_role_id", savedUser.getRole_id());
        assertEquals("example_user", savedUser.getUsername());
        // Add more assertions as needed
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testSave_DefaultRoleNotFound() {
        // Arrange
        User user = new User();
        user.setUsername("example_user");
        user.setPassword("example_password");
        when(roleServiceMock.getRoleIDByName("DEFAULT")).thenReturn(null);

        // Act
        userService.save(user);
    }

    @Test
    public void testLogin_SuccessfulLogin() {
        // Arrange
        User user = new User("testUser", BCrypt.hashpw("testPassword", BCrypt.gensalt()), null);
        when(userDaoMock.findAllWithRoles()).thenReturn(Arrays.asList(user));

        // Act
        Optional<User> loggedInUser = userService.login("testUser", "testPassword");

        // Assert
        assertTrue(loggedInUser.isPresent());
        assertEquals("testUser", loggedInUser.get().getUsername());
    }

    @Test
    public void testLogin_IncorrectUsernameOrPassword() {
        // Arrange
        User user = new User("testUser", BCrypt.hashpw("testPassword", BCrypt.gensalt()), null);
        when(userDaoMock.findAllWithRoles()).thenReturn(Arrays.asList(user));

        // Act
        Optional<User> loggedInUser = userService.login("testUser", "incorrectPassword");

        // Assert
        assertFalse(loggedInUser.isPresent());
    }

    @Test
    public void testDelete_SuccessfulDeletion() {
        // Arrange
        User deletedUser = new User();
        deletedUser.setId("123");
        deletedUser.setUsername("testUser");
        deletedUser.setPassword("password");
        when(userDaoMock.delete("123")).thenReturn(deletedUser);

        // Act
        User result = userService.delete("123");

        // Assert
        assertNotNull(result);
        assertEquals("123", result.getId());
        assertEquals("testUser", result.getUsername());
        assertEquals("password", result.getPassword());
    }

    @Test
    public void testDelete_UserNotFound() {
        // Arrange
        when(userDaoMock.delete("456")).thenReturn(null); // Simulate user not found

        // Act
        User result = userService.delete("456");

        // Assert
        assertNull(result);
    }
}

