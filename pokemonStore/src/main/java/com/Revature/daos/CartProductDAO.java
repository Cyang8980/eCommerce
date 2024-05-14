package com.Revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Revature.utils.ConnectionFactory;
import com.Revature.models.CartProduct;
import com.Revature.models.Item;
public class CartProductDAO {


    /*/*
    INSERT INTO JunctionAB (TableA_ID, TableB_ID)
    VALUES (value_for_TableA_ID, value_for_TableB_ID);
    */
    public CartProduct save(CartProduct obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO cart_items (item_id, cart_id, quantity) VALUES (?, ?, ?)")) {
            ps.setString(1, obj.getItem_id());
            ps.setString(2, obj.getCart_id());
            ps.setInt(3, obj.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file"+ e);
        }
        return obj;
    }

    public CartProduct update(CartProduct obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE cart_items SET quantity = ? WHERE cart_id = ? AND item_id = ?")) {
            ps.setInt(1, obj.getQuantity());
            ps.setString(2, obj.getCart_id());
            ps.setString(3, obj.getItem_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database" + e) ;
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file"+ e);
        }
        return obj;
    }

    public Item delete(String cart_id, String item_id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM cart_items WHERE cart_id = ? AND item_id = ?")) {
            ps.setString(1, cart_id);
            ps.setString(2, item_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                Item deletedItem = new Item();
                deletedItem.setItem_id(item_id);
                return deletedItem;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database"+ e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file"+ e);
        }
        return null;
    }
    public Item delete(String cart_id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM cart_items WHERE cart_id = ?")) {
            ps.setString(1, cart_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                Item deletedItem = new Item();
                System.out.println(rowsAffected);
                return deletedItem;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file" + e);
        }
        return null;
    }

    public List<CartProduct> findAll() {
        List<CartProduct> cartProducts = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM cart_items");
        ResultSet rs = ps.executeQuery();) {
            while(rs.next()) {
                CartProduct cartProduct = new CartProduct();
                cartProduct.setItem_id(rs.getString("item_id"));
                cartProduct.setCart_id(rs.getString("cart_id"));
                cartProduct.setQuantity(rs.getInt("quantity"));
                cartProducts.add(cartProduct);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file" + e);
        }
        return cartProducts;
    }
    public List<CartProduct> findAllByCartID(String ID) {
        List<CartProduct> cartProducts = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM cart_items WHERE cart_id = ?")) {
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                CartProduct cartProduct = new CartProduct();
                cartProduct.setItem_id(rs.getString("item_id"));
                cartProduct.setCart_id(rs.getString("cart_id"));
                cartProduct.setQuantity(rs.getInt("quantity"));
                cartProducts.add(cartProduct);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file" + e);
        }
        return cartProducts;
    }
}
