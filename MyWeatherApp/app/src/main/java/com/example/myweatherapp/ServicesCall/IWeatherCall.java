package com.example.myweatherapp.ServicesCall;

import com.example.myweatherapp.Entities.WebResponse;
import com.example.myweatherapp.Entities.WebResponseForCity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWeatherCall {

    @GET("https://openweathermap.org/data/2.5/forecast/daily")
    Call<WebResponse> getWeatherCity(@Query("q") String city, @Query("appid")String apiKey);

    @GET("https://openweathermap.org/data/2.5/weather")
    Call<WebResponseForCity> getWeatherByCity(@Query("q") String city, @Query("appid")String apiKey);
}
