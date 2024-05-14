package com.Revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Revature.models.Item;
import com.Revature.models.OrderItem;
import com.Revature.utils.ConnectionFactory;

public class OrderItemDAO {

    public OrderItem save(OrderItem obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO order_items (item_id, order_id, quantity, cost) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, obj.getItem_id());
            ps.setString(2, obj.getOrder_id());
            ps.setInt(3, obj.getQuantity());
            ps.setInt(4, obj.getCost());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return obj;
    }

    public Item delete(String store_id, String item_id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM order_items WHERE store_id = ? AND item_id = ?")) {
            ps.setString(1, store_id);
            ps.setString(2, item_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                Item deletedItem = new Item();
                deletedItem.setItem_id(item_id);
                return deletedItem;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return null;
    }

    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM order_items");
        ResultSet rs = ps.executeQuery();) {
            while(rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setItem_id(rs.getString("item_id"));
                orderItem.setOrder_id(rs.getString("order_id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setCost(rs.getInt("cost"));
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return orderItems;
    }

    public OrderItem update(OrderItem obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE order_items SET quantity = ?, WHERE order_id = ? AND item_id = ?")) {
            ps.setInt(1, obj.getQuantity());
            ps.setString(2, obj.getOrder_id());
            ps.setString(3, obj.getItem_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
       return obj;
    }
    public List<OrderItem> findAllByOrderID(String ID) {
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM order_items WHERE order_id = ?")) {
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setItem_id(rs.getString("item_id"));
                orderItem.setOrder_id(rs.getString("order_id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setCost(rs.getInt("cost"));
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file" + e);
        }
        return orderItems;
    }
}
