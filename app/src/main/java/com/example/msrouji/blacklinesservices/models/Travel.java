package com.example.msrouji.blacklinesservices.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by msrouji on 19/11/2017.
 */

public class Travel implements Serializable{
    private Long id;
    private ArrayList<Long>  bookings;
    private ArrayList<Booking>  bookings_obj;
    private String start;
    private String end;
    private int airport;
    private Airport airport_obj;

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

    public int getAirport() {
        return airport;
    }

    public void setAirport(int airport) {
        this.airport = airport;
    }

    public Airport getAirport_obj() {
        return airport_obj;
    }

    public void setAirport_obj(Airport airport_obj) {
        this.airport_obj = airport_obj;
    }

    public ArrayList<Booking> getBookings_obj() {
        return bookings_obj;
    }

    public void setBookings_obj(ArrayList<Booking> bookings_obj) {
        this.bookings_obj = bookings_obj;
    }
}
