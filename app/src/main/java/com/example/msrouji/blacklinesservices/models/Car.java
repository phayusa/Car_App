package com.example.msrouji.blacklinesservices.models;

import java.io.Serializable;

/**
 * Created by msrouji on 17/11/2017.
 */

public class Car implements Serializable {

    private Long model;
    private Long driver;

    private String registration;
    private String color;

    private String pos;

    private long revenues;
    private int empty_places;

    private long area;
    private long id;

    private String insurance;
    private String insurance_card;
    private String registration_card;
    private String front;
    private String back;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getModel() {
        return model;
    }

    public Long getDriver() {
        return driver;
    }

    public String getRegistration() {
        return registration;
    }

    public String getColor() {
        return color;
    }

    public String getPos() {
        return pos;
    }

    public long getRevenues() {
        return revenues;
    }

    public int getEmpty_places() {
        return empty_places;
    }

    public long getArea() {
        return area;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getInsurance_card() {
        return insurance_card;
    }

    public String getRegistration_card() {
        return registration_card;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }
}
