package com.example.e_cynic.entity;

import androidx.annotation.Nullable;

public class Address {
    public Integer addressId;
    public String firstLine;
    public String secondLine;
    public String thirdLine;
    public String city;
    public String state;
    public Integer postcode;

    public Address(@Nullable Integer addressId, String firstLine, String secondLine,
                   String thirdLine, String city, String state, Integer postcode) {
        this.addressId = addressId;
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.thirdLine = thirdLine;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + addressId +
                ", firstLine='" + firstLine + '\'' +
                ", secondLine='" + secondLine + '\'' +
                ", thirdLine='" + thirdLine + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postcode=" + postcode +
                '}';
    }
}