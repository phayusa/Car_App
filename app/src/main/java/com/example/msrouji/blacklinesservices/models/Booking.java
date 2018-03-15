package com.example.msrouji.blacklinesservices.models;

import java.util.Date;

/**
 * Created by msrouji on 19/11/2017.
 */

public class Booking {
    private Long id;
    private String airport;
    private String destination;
    private String destination_location;
    private Date date;
    private Long client;
    private Client client_obj;
    private Integer passengers;
    private Integer luggage_number;
    private Long distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }

    public Integer getLuggage_number() {
        return luggage_number;
    }

    public void setLuggage_number(Integer luggage_number) {
        this.luggage_number = luggage_number;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public String getDestination_location() {
        return destination_location;
    }

    public void setDestination_location(String destination_location) {
        this.destination_location = destination_location;
    }

    public Client getClient_obj() {
        return client_obj;
    }

    public void setClient_obj(Client client_obj) {
        this.client_obj = client_obj;
    }
}
