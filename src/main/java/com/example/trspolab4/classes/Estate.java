package com.example.trspolab4.classes;

import java.time.LocalDateTime;

public class Estate {
    private final int id;
    private final User seller;
    private final String title;
    private final double cost;
    private final LocalDateTime time;

    public Estate(int id, User seller, String title, double cost, LocalDateTime time) {
        this.id = id;
        this.seller = seller;
        this.title = title;
        this.cost = cost;
        this.time = time;
    }
    public int getId() {
        return id;
    }

    public User getSeller() {
        return seller;
    }
    public int getSellerId() {
        return seller.getId();
    }

    public String getSellerName() {
        return seller.getName();
    }


    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
