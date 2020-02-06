package com.example.myweatherapp.Model;

import android.content.Context;

import com.example.myweatherapp.Entities.WeatherCVEntity;

public interface ISearchWeatherByCityModel {
    void searchWeatherByCity(String city, Context context);
    void sendResultToPresenter(WeatherCVEntity weatherCVEntity);

}
