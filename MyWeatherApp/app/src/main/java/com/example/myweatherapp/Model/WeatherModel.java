package com.example.myweatherapp.Model;

import android.widget.TextView;
import android.widget.Toast;

import com.example.myweatherapp.Entities.Temp;
import com.example.myweatherapp.Entities.Weather;
import com.example.myweatherapp.Entities.WeatherCVEntity;
import com.example.myweatherapp.Entities.WeatherList;
import com.example.myweatherapp.Entities.WebResponse;
import com.example.myweatherapp.Presenter.IWeatherPresenter;
import com.example.myweatherapp.ServicesCall.IWeatherCall;
import com.example.myweatherapp.ServicesCall.RetrofitInstance;
import com.example.myweatherapp.View.MainActivity;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherModel implements IWeatherModel {


    IWeatherPresenter iWeatherPresenter;

    public WeatherModel(IWeatherPresenter iWeatherPresenter){
        this.iWeatherPresenter = iWeatherPresenter;
    }


    @Override
    public void GetWeatherList(String city) {
        final Gson gson = new Gson();
        IWeatherCall iWeatherCall = RetrofitInstance.getRetrofitInstance("https://openweathermap.org/data/2.5/").create(IWeatherCall.class);

        Call<WebResponse> call = iWeatherCall.getWeatherCity(city,"b6907d289e10d714a6e88b30761fae22");

        call.enqueue(new Callback<WebResponse>() {
            @Override
            public void onResponse(Call<WebResponse> call, Response<WebResponse> response) {
                WebResponse webResponse = (WebResponse)response.body();
                ArrayList<WeatherCVEntity> weatherCVEntitiesList = new ArrayList<>();
                ArrayList<WeatherList> weatherLists = new ArrayList<>();

                for (int i = 0;i<webResponse.getArrayList().size();i++){
                    WeatherCVEntity weatherCVEntity = new WeatherCVEntity();
                    weatherCVEntity.setCity(webResponse.getCity().getName());
                    weatherCVEntity.setHumidity(webResponse.getArrayList().get(i).getHumidity());
                    weatherCVEntity.setMinTemperature(webResponse.getArrayList().get(i).getTemp().getMin().doubleValue());
                    weatherCVEntity.setMaxTemperature(webResponse.getArrayList().get(i).getTemp().getMax());
                    weatherCVEntity.setDescription(webResponse.getArrayList().get(i).getWeather().get(0).getDescription());
                    weatherCVEntitiesList.add(weatherCVEntity);
                }

                iWeatherPresenter.SetWeatherList(weatherCVEntitiesList);
            }

            @Override
            public void onFailure(Call<WebResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
