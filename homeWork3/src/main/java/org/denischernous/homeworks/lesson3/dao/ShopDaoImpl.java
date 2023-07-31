package org.denischernous.homeworks.lesson3.dao;

import org.denischernous.homeworks.lesson3.config.DbConnector;
import org.denischernous.homeworks.lesson3.model.Book;
import org.denischernous.homeworks.lesson3.model.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopDaoImpl implements ShopDao{
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Shop save(Shop shop) {
        String sql = "INSERT INTO shops (shop_city, shop_phone) VALUES(?, ?) ";
        int shopId = 0;

        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, shop.getCity());
            preparedStatement.setInt(2, shop.getPhone());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                shopId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        shop.setId(shopId);
        return shop;
    }

    @Override
    public List<Shop> getAll() {
        List<Shop> shopList = new ArrayList<>();

        String sql = "SELECT * FROM shops";
        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("shop_id");
                String city = rs.getString("shop_city");
                int phone = rs.getInt("shop_phone");
                Shop shop = new Shop(id, city, phone);
                shopList.add(shop);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shopList;
    }

    @Override
    public Shop update(Shop shop) {
        String sql = "UPDATE shops SET shop_city = ?, shop_phone = ? " +
                "WHERE shop_id = ? ";
        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, shop.getCity());
            preparedStatement.setInt(2, shop.getPhone());
            preparedStatement.setInt(3, shop.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shop;
    }

    @Override
    public int deleteById(int id) {
        if (id <= 0) {
            throw new RuntimeException("Other type of exception");
        }
        String sql = "DELETE FROM shops " +
                "WHERE shop_id = ? ";

        try (Connection connection = DbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
