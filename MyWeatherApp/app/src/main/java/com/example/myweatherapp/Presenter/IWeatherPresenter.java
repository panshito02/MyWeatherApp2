package com.example.myweatherapp.Presenter;

import com.example.myweatherapp.Entities.WeatherCVEntity;
import com.example.myweatherapp.Entities.WeatherList;

import java.util.ArrayList;

public interface IWeatherPresenter {

    void GetWeatherList(String city);
    void SetWeatherList(ArrayList<WeatherCVEntity> weatherList);

}
