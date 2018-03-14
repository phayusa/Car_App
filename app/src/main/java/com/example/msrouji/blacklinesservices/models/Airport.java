package com.example.msrouji.blacklinesservices.models;

/**
 * Created by msrouji on 14/03/2018.
 */

public class Airport {
    private long id;
    private String address;
    private String location;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
