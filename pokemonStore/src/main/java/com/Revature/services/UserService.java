package com.Revature.services;

import java.util.List;
import java.util.Optional;

import com.Revature.daos.UserDAO;
import com.Revature.models.User;
import com.Revature.utils.custom_exceptions.ResourceNotFoundException;

import org.springframework.security.crypto.bcrypt.BCrypt;
public class UserService {
    private final UserDAO userDAO;
    private final RoleService roleService;
    
    public UserService(UserDAO userDAO, RoleService roleService) {
        this.userDAO = userDAO;
        this.roleService = roleService;
    }

    public boolean uniqueUsername(String username) {
        List<User> users = userDAO.findAll();
        return users.stream().noneMatch(u -> u.getUsername().equals(username));
    }

    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean validPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }

    public User save(User user) {
        String defaultID = roleService.getRoleIDByName("DEFAULT");
        System.out.println(user);
        if (defaultID == null || defaultID.isEmpty()) {
            throw new ResourceNotFoundException("Default role not found");
        }
        user.setRole_id(defaultID);
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hashedPassword);
        
        return userDAO.save(user);
    }

    public Optional<User> login(String username, String password) {
        return userDAO.findAllWithRoles()
        .stream()
        .filter(u -> u.getUsername().equals(username) && BCrypt.checkpw(password, u.getPassword()))
        .findFirst();
    }

    public User delete(String id) {
        return userDAO.delete(id);
    }
}
