package com.example.myweatherapp.Presenter;

import android.content.Context;

import com.example.myweatherapp.Entities.WeatherCVEntity;

public interface ISearchWeatherByCityPresenter {
    void SearchWeatherByCity(String city, Context context);
    void setResultFromSearchByCity(WeatherCVEntity weatherCVEntity);

}
