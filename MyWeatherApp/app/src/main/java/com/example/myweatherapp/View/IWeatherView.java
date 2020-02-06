package com.example.myweatherapp.View;

import com.example.myweatherapp.Entities.WeatherCVEntity;
import com.example.myweatherapp.Entities.WeatherList;

import java.util.ArrayList;

public interface IWeatherView {
    void GetWeatherList(String city);
    void ShowResult(ArrayList<WeatherCVEntity> arrayList);
}
