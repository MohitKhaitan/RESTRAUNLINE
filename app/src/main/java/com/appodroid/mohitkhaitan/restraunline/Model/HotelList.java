package com.appodroid.mohitkhaitan.restraunline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;


    public Integer getHotelId() {
        return id;
    }

    public void setHotelId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return name;
    }

    public void setHotelName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}