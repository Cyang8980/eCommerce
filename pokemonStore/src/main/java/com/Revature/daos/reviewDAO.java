package com.Revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Revature.models.Review;
import com.Revature.utils.ConnectionFactory;

import java.io.IOException;

public class reviewDAO implements CrudDAO<Review> {

    @Override
    public Review save(Review review){
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO items (id, name, value, grade) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, review.getId());
            ps.setInt(2, review.getRating());
            ps.setString(3, review.getUser_id());
            ps.setString(4, review.getStore_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving review to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return review;
    }

    @Override
    public Review update(Review obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE reviews SET message = ?, rating = ?, user_id = ?, store_id = ?, WHERE id = ?")) {
            ps.setString(5, obj.getId());
            ps.setString(1, obj.getMessage());
            ps.setInt(2, obj.getRating());
            ps.setString(3, obj.getUser_id());
            ps.setString(4, obj.getStore_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return obj;
    }

    @Override
    public Review delete(String ID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM reviews WHERE id = ?");
        ResultSet rs = ps.executeQuery();) {
            while(rs.next()) {
                Review review = new Review();
                review.setId(rs.getString("id"));
                review.setMessage(rs.getString("message"));
                review.setRating(rs.getInt("rating"));
                review.setUser_id(rs.getString("user_id"));
                review.setStore_id(rs.getString("store_id"));
                return review;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return null;
    }

    @Override
    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM reviews");
        ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                Review review = new Review();
                review.setId(rs.getString("id"));
                review.setMessage(rs.getString("message"));
                review.setRating(rs.getInt("rating"));
                review.setUser_id(rs.getString("user_id"));
                review.setStore_id(rs.getString("store_id"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return reviews;
    }

    @Override
    public Review findByID(String ID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM reviews WHERE id = ?");
        ResultSet rs = ps.executeQuery();) {
            while(rs.next()) {
                Review review = new Review();
                review.setId(rs.getString("id"));
                review.setMessage(rs.getString("message"));
                review.setRating(rs.getInt("rating"));
                review.setUser_id(rs.getString("user_id"));
                review.setStore_id(rs.getString("store_id"));
                return review;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return null;
    }
    
    public List<Review> findAllReviewByUserID(String ID) {
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM reviews WHERE user_id = ?");
        ResultSet rs = ps.executeQuery();) {
            while(rs.next()) {
                Review review = new Review();
                review.setId(rs.getString("id"));
                review.setMessage(rs.getString("message"));
                review.setRating(rs.getInt("rating"));
                review.setUser_id(rs.getString("user_id"));
                review.setStore_id(rs.getString("store_id"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to the database");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties file");
        }
        return reviews;
    }
}
