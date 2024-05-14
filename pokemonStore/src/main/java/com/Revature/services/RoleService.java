package com.Revature.services;

import com.Revature.daos.RoleDAO;

public class RoleService {
    private final RoleDAO roleDAO;
    
    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public String getRoleIDByName(String name) {
        return roleDAO.findAll().stream()
            .filter(r -> r.getName().equals(name))
            .findFirst()
            .get()
            .getId();
    }
}
