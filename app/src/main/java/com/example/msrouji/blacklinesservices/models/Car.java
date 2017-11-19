package com.example.msrouji.blacklinesservices.models;

import com.example.msrouji.blacklinesservices.CarsListActivity;

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

    public void setModel(Long model) {
        this.model = model;
    }

    public Long getDriver() {
        return driver;
    }

    public void setDriver(Long driver) {
        this.driver = driver;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public long getRevenues() {
        return revenues;
    }

    public void setRevenues(long revenues) {
        this.revenues = revenues;
    }

    public int getEmpty_places() {
        return empty_places;
    }

    public void setEmpty_places(int empty_places) {
        this.empty_places = empty_places;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getInsurance_card() {
        return insurance_card;
    }

    public void setInsurance_card(String insurance_card) {
        this.insurance_card = insurance_card;
    }

    public String getRegistration_card() {
        return registration_card;
    }

    public void setRegistration_card(String registration_card) {
        this.registration_card = registration_card;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }
}
