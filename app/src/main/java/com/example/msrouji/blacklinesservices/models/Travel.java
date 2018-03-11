package com.example.msrouji.blacklinesservices.models;

import java.util.ArrayList;

/**
 * Created by msrouji on 19/11/2017.
 */

public class Travel {
    private Long id;
    private ArrayList<Long> bookings;
    private String start;
    private String end;
    private String airport;

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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }
}
