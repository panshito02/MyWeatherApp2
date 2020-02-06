package com.example.myweatherapp.View;

import com.example.myweatherapp.Entities.WeatherCVEntity;

import java.util.ArrayList;

public interface IHistory {
    void searchHistoryWeather();
    void showResultHistory(ArrayList<WeatherCVEntity> weatherCVEntityArrayList);
}
