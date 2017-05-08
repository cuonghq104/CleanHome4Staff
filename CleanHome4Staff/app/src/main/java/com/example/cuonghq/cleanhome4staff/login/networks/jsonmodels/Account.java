package com.example.cuonghq.cleanhome4staff.login.networks.jsonmodels;

/**
 * Created by Cuonghq on 4/26/2017.
 */

public class Account {
    private String username;

    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
