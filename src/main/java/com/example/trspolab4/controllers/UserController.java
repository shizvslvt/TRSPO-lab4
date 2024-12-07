package com.example.trspolab4.controllers;

import com.example.trspolab4.classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserController {
    private final DatabaseController dbCtrl;
    public UserController(DatabaseController dbCtrl) {
        this.dbCtrl = dbCtrl;
    }
    private final ObservableList<User> user_list = FXCollections.observableArrayList();

    public void read(TableView user_table, TableColumn user_id, TableColumn user_name, TableColumn user_mail) {
        user_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        user_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        user_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        dbCtrl.user_read(user_table, user_list);

    }

    public void create(int id, String name, String mail) {
        dbCtrl.user_create(id, name, mail);
    }

    public void update(int id, String name, String mail) {
        dbCtrl.user_update(id, name, mail);
    }

    public void delete(int id) {
        dbCtrl.user_delete(id);
    }


    public ObservableList<User> getIdAndNameUsers() {
        return dbCtrl.getIdAndNameUsers();
    }
}
