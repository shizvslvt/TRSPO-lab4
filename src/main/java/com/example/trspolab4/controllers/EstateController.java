package com.example.trspolab4.controllers;

import com.example.trspolab4.classes.Estate;
import com.example.trspolab4.classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;

public class EstateController {
    private final DatabaseController dbCtrl;
    public EstateController(DatabaseController dbCtrl) {
        this.dbCtrl = dbCtrl;
    }
    private final ObservableList<Estate> estate_list = FXCollections.observableArrayList();

    public void read(TableView estate_table, TableColumn estate_id, TableColumn estate_sellerName, TableColumn estate_title, TableColumn estate_cost, TableColumn estate_time) {
        estate_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        estate_sellerName.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        estate_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        estate_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        estate_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        dbCtrl.estate_read(estate_table, estate_list);
    }

    public void create(int id, User seller, String title, double cost, LocalDateTime time) {
        dbCtrl.estate_create(id, seller, title, cost, time);
    }

    public void update(int id, User seller, String title, double cost, LocalDateTime time) {
        dbCtrl.estate_update(id, seller, title, cost, time);
    }

    public void delete(int id) {
        dbCtrl.estate_delete(id);
    }
}
