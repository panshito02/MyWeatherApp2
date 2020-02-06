package com.example.myweatherapp.Entities;

import java.util.ArrayList;

public class WebResponse {
    private City city;
    private String cod;
    private String message;
    private String cnt;
    private ArrayList<WeatherList> list;

    public WebResponse(City city, String cod, String message, String cnt, ArrayList<WeatherList> arrayList) {
        this.city = city;
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = arrayList;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public ArrayList<WeatherList> getArrayList() {
        return list;
    }

    public void setArrayList(ArrayList<WeatherList> arrayList) {
        this.list = arrayList;
    }
}
