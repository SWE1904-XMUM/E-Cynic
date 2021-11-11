package com.example.e_cynic.entity;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class User {
    public Integer userId;
    public String username;
    public String email;
    public String password;
    public String name;
    public String phoneNumber;
    public ArrayList<Address> addresses;

    public User(@Nullable Integer userId, String username, @Nullable String email,
                @Nullable String password,
                @Nullable String name, @Nullable String phoneNumber, @Nullable ArrayList<Address> addresses) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
