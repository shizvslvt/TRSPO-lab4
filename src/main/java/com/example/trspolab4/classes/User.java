package com.example.trspolab4.classes;

public class User {
    private final int id;
    private final String name;
    private final String mail;

    public User(int id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }
    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.mail = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public String toString() {
        return id + " | "  + name;
    }

}
