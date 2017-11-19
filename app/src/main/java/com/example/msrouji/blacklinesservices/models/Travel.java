package com.example.msrouji.blacklinesservices.models;

import java.util.ArrayList;

/**
 * Created by msrouji on 19/11/2017.
 */

public class Travel {
    private Long id;
    private ArrayList<Long> bookings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Long> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Long> bookings) {
        this.bookings = bookings;
    }
}
