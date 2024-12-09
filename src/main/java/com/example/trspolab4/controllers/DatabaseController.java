package com.example.trspolab4.controllers;

import com.example.trspolab4.classes.Database;
import com.example.trspolab4.classes.Estate;
import com.example.trspolab4.classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.*;
import java.time.LocalDateTime;

public class DatabaseController {
    private final Database db = new Database();
    private final Connection connection = db.getConnection();

    public void user_read(TableView userTable, ObservableList<User> userList) {
        userList.clear();
        String sql = "SELECT id, name, mail FROM ro_users";
        try (PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("mail");
                User user = new User(id, name, email);
                userList.add(user);
            }
            userTable.setItems(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void user_create(int id, String name, String mail) {
        String sql = "INSERT INTO ro_users (id, name, mail) VALUES (?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, mail);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void user_update(int id, String name, String mail) {
        String sql = "UPDATE ro_users SET name = ?, mail = ? WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, mail);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void user_delete(int id) {
        String sql = "DELETE FROM ro_users WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void estate_read(TableView estateTable, ObservableList<Estate> estateList) {
        String sql = "SELECT e.id, u.id AS seller_id, u.name AS seller_name, e.title, e.cost, e.time FROM ro_estates AS e JOIN ro_users AS u ON e.seller_id = u.id";
        try (PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                User seller = new User(resultSet.getInt("seller_id"), resultSet.getString("seller_name"));
                String title = resultSet.getString("title");
                double cost = resultSet.getDouble("cost");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                Estate estate = new Estate(id, seller, title, cost, time);
                estateList.add(estate);
            }
            estateTable.setItems(estateList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<User> getIdAndNameUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        String sql = "SELECT id, name FROM ro_users";
        try (PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                users.add(new User(id, name));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void estate_create(int id, User seller, String title, double cost, LocalDateTime time) {
        String sql = """
        INSERT INTO ro_estates (
            id, seller_id, title, cost, time, realtor_id, accepted, archived, description, city_id, locality_id, type_id, area, bedrooms, floors
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setInt(2, seller.getId());
            statement.setString(3, title);
            statement.setDouble(4, cost);
            statement.setTimestamp(5, Timestamp.valueOf(time));

            statement.setNull(6, java.sql.Types.INTEGER);
            statement.setNull(7, java.sql.Types.TIMESTAMP);
            statement.setInt(8, 1);
            statement.setString(9, "");
            statement.setInt(10, 1);
            statement.setInt(11, 1);
            statement.setInt(12, 1);
            statement.setDouble(13, 1.0);
            statement.setInt(14, 1);
            statement.setInt(15, 1);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void estate_update(int id, User seller, String title, double cost, LocalDateTime time) {
        String sql = "UPDATE ro_estates SET seller_id = ?, title = ?, cost = ?, time = ? WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, seller.getId());
            statement.setString(2, title);
            statement.setDouble(3, cost);
            statement.setTimestamp(4, Timestamp.valueOf(time));
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void estate_delete(int id) {
        String sql = "DELETE FROM ro_estates WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
