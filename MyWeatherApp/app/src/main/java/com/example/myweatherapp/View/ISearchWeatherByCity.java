package com.example.myweatherapp.View;

import com.example.myweatherapp.Entities.WeatherCVEntity;

public interface ISearchWeatherByCity {

    void searchWeatherByCity(String city);
    void showSearchRetult(WeatherCVEntity weatherCVEntity);
}
