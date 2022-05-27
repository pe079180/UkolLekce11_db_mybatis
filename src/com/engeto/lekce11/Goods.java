package com.engeto.lekce11;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Goods implements GoodsMethods {
    private final String URL = "jdbc:mysql://localhost:3306/engeto";
    private final String USER = "test";
    private final String PASSWORD = "test";

    @Override
    public Item loadItemById(Integer id) {
        Item resultItem = new Item(null, null, null, null, null, null, null);
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = con.createStatement();
            statement.executeQuery("SELECT * FROM engeto.item WHERE id=" + id.toString());
            ResultSet result = statement.getResultSet();
            if (result.next()) {
                resultItem.setId(result.getInt("id"));
                resultItem.setPartNo(result.getString("part_no"));
                resultItem.setSerialNo(result.getString("serial_no"));
                resultItem.setName(result.getString("name"));
                resultItem.setDescription(result.getString("description"));
                resultItem.setNumberInStock(result.getInt("number_in_stock"));
                resultItem.setPrice(result.getBigDecimal("price"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultItem;
    }

    @Override
    public void deleteAllOutOfStockItems() {
        final String DELETE_QUERY = "DELETE FROM engeto.item WHERE number_in_stock=0";
        PreparedStatement preparedStatement;
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            preparedStatement = con.prepareStatement(DELETE_QUERY);
            int rowCount = preparedStatement.executeUpdate();
            System.out.println("" + rowCount + " out of stocks items deleted ...");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Item> loadAllAvailableItems() {
        List<Item> resultList = new ArrayList<>();
        Item item;
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = con.createStatement();
            statement.executeQuery("SELECT * FROM engeto.item WHERE number_in_stock>0");
            ResultSet result = statement.getResultSet();

            while (result.next()) {
                item = new Item(
                        result.getInt("id"),
                        result.getString("part_no"),
                        result.getString("serial_no"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getInt("number_in_stock"),
                        result.getBigDecimal("price"));

                resultList.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public void saveItem(Item item) {
        final String INSERT_QUERY = "INSERT INTO engeto.item (part_no,serial_no,name,description,number_in_stock,price) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            preparedStatement = con.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, item.getPartNo());
            preparedStatement.setString(2, item.getSerialNo());
            preparedStatement.setString(3, item.getName());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.setInt(5, item.getNumberInStock());
            preparedStatement.setBigDecimal(6, item.getPrice());

            int rowCount = preparedStatement.executeUpdate();
            System.out.println("" + rowCount + " row inserted ...");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updatePrice(Integer id, BigDecimal newPrice) {
        final String UPDATE_QUERY = "UPDATE engeto.item set price = ? WHERE id = ?";
        PreparedStatement preparedStatement;
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            preparedStatement = con.prepareStatement(UPDATE_QUERY);
            preparedStatement.setBigDecimal(1, newPrice);
            preparedStatement.setInt(2, id);

            int rowCount = preparedStatement.executeUpdate();
            System.out.println("" + rowCount + " row updated ...");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
