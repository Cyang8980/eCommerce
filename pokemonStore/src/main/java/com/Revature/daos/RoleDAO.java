package com.Revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Revature.models.Role;
import com.Revature.utils.ConnectionFactory;

public class RoleDAO implements CrudDAO<Role> {

    @Override
    public Role save(Role obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO roles (id, name) VALUES (?, ?)")) {
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return obj;
    }

    @Override
    public Role update(Role obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE roles SET name = ? WHERE id = ?")) {
            ps.setString(2, obj.getId());
            ps.setString(1, obj.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return obj;
    }

    @Override
    public Role delete(String ID) {
        Role deletedRole = null;
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM roles WHERE id = ?")) {
            ps.setString(1, ID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                deletedRole = new Role();
                deletedRole.setId(ID);
                deletedRole.setName(findByID(ID).getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting item from the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return deletedRole;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM roles");
        ResultSet rs = ps.executeQuery();) {
            while(rs.next()) {
                Role role = new Role();
                role.setId(rs.getString("id"));
                role.setName(rs.getString("name"));
                roles.add(role);
                System.out.println("added: " + role.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return roles;
    }

    @Override
    public Role findByID(String ID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM roles WHERE id = ?");
        ResultSet rs = ps.executeQuery();) {
            while(rs.next()) {
                Role role = new Role();
                role.setId(rs.getString("id"));
                role.setName(rs.getString("name"));
                return role;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return null;
    }
}
