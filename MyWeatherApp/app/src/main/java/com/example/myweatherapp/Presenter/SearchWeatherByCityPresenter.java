package com.example.myweatherapp.Presenter;


import android.content.Context;

import com.example.myweatherapp.Entities.WeatherCVEntity;
import com.example.myweatherapp.Model.ISearchWeatherByCityModel;
import com.example.myweatherapp.Model.SearchWeatherByCityModel;
import com.example.myweatherapp.View.ISearchWeatherByCity;

public class SearchWeatherByCityPresenter implements ISearchWeatherByCityPresenter {

    ISearchWeatherByCity iSearchWeatherByCity;
    SearchWeatherByCityModel SearchWeatherByCityModel;
    public SearchWeatherByCityPresenter(ISearchWeatherByCity iSearchWeatherByCity){
        this.iSearchWeatherByCity = iSearchWeatherByCity;

        SearchWeatherByCityModel = new SearchWeatherByCityModel(this);
    }

    @Override
    public void SearchWeatherByCity(String city, Context context) {
        SearchWeatherByCityModel.searchWeatherByCity(city,context);
    }

    @Override
    public void setResultFromSearchByCity(WeatherCVEntity weatherCVEntity) {
        iSearchWeatherByCity.showSearchRetult(weatherCVEntity);
    }
}
