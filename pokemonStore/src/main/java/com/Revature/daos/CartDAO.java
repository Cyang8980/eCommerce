package com.Revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Revature.models.Cart;
import com.Revature.utils.ConnectionFactory;

import java.io.IOException;


public class CartDAO implements CrudDAO<Cart>{

    @Override
    public Cart save(Cart obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO carts (id, user_id) VALUES (?, ?)")) {
            ps.setString(1, obj.getCart_id());
            ps.setString(2, obj.getBelongsTo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return obj;
    }

    @Override
    public Cart update(Cart obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE carts SET user_id=? WHERE id=?")) {
            ps.setString(2, obj.getCart_id());
            ps.setString(1, obj.getBelongsTo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return obj;
    }

    @Override
    public Cart delete(String ID) {
        Cart deletedItem = null;
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM carts WHERE user_id = ?")) {
            ps.setString(1, ID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                deletedItem = new Cart();
                deletedItem.setCart_id(ID);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting item from the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return deletedItem;
    }

    @Override
    public List<Cart> findAll(){
        List<Cart> carts = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM carts");
        ResultSet rs = ps.executeQuery();) {
            while(rs.next()) {
                Cart cart = new Cart();
                cart.setCart_id(rs.getString("id"));
                cart.setBelongsTo(rs.getString("user_id"));
                carts.add(cart);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return carts;
    }

    @Override
    public Cart findByID(String ID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM carts WHERE user_id = ?")) {
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();
            Cart cart = new Cart();
            while(rs.next()) {
                cart.setCart_id(rs.getString("id"));
                cart.setBelongsTo(rs.getString("user_id"));
            }
            return cart;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database"+ e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file"+ e);
        }
    }

    public Cart findByCartID(String ID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM carts WHERE id = ?")) {
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Cart cart = new Cart();
                cart.setCart_id(rs.getString("id"));
                cart.setBelongsTo(rs.getString("user_id"));
                return cart;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return null;
    }
}
