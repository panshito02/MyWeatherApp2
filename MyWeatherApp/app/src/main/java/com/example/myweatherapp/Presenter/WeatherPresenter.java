package com.example.myweatherapp.Presenter;

import android.view.View;

import com.example.myweatherapp.Entities.WeatherCVEntity;
import com.example.myweatherapp.Entities.WeatherList;
import com.example.myweatherapp.Model.WeatherModel;
import com.example.myweatherapp.View.IWeatherView;
import com.example.myweatherapp.View.MainActivity;

import java.util.ArrayList;

public class WeatherPresenter implements IWeatherPresenter {

    WeatherModel weatherModel;
    private IWeatherView view;
    public WeatherPresenter(IWeatherView view){
        this.view = view;
        weatherModel = new WeatherModel(this);
    }

    @Override
    public void GetWeatherList(String city) {
        weatherModel.GetWeatherList(city);
    }

    @Override
    public void SetWeatherList(ArrayList<WeatherCVEntity> weatherList) {
        view.ShowResult(weatherList);
    }
}
