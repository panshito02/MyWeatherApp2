package com.example.myweatherapp.Presenter;

import android.content.Context;

import com.example.myweatherapp.Entities.WeatherCVEntity;

import java.util.ArrayList;

public interface IHistoryPresenter {
    void searchHistoryWeather(Context context);
    void sendResultToView(ArrayList<WeatherCVEntity> weatherCVEntityArrayList);
}
