package com.example.myweatherapp.Model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.myweatherapp.Entities.WeatherCVEntity;
import com.example.myweatherapp.Entities.WebResponse;
import com.example.myweatherapp.Entities.WebResponseForCity;
import com.example.myweatherapp.Presenter.ISearchWeatherByCityPresenter;
import com.example.myweatherapp.Presenter.SearchWeatherByCityPresenter;
import com.example.myweatherapp.ServicesCall.IWeatherCall;
import com.example.myweatherapp.ServicesCall.RetrofitInstance;
import com.example.myweatherapp.SqliteQuerys.SearchByCityQuerys;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchWeatherByCityModel implements ISearchWeatherByCityModel {

    ISearchWeatherByCityPresenter iSearchWeatherByCityPresenter;
    SearchByCityQuerys searchByCityQuerys;


    public SearchWeatherByCityModel(SearchWeatherByCityPresenter iSearchWeatherByCityPresenter){
        this.iSearchWeatherByCityPresenter = iSearchWeatherByCityPresenter;

    }

    @Override
    public void searchWeatherByCity(String city, final Context context) {
        IWeatherCall iWeatherCall = RetrofitInstance.getRetrofitInstance("https://openweathermap.org/data/2.5/weather").create(IWeatherCall.class);

        Call<WebResponseForCity> call = iWeatherCall.getWeatherByCity(city,"b6907d289e10d714a6e88b30761fae22");

        call.enqueue(new Callback<WebResponseForCity>() {
            @Override
            public void onResponse(Call<WebResponseForCity> call, Response<WebResponseForCity> response) {
                WebResponseForCity webResponse = (WebResponseForCity) response.body();
                WeatherCVEntity weatherCVEntity = new WeatherCVEntity();

                weatherCVEntity.setCity(webResponse.getName());
                weatherCVEntity.setHumidity(webResponse.getMain().getTemp());
                weatherCVEntity.setMinTemperature(webResponse.getMain().getTemp_min());
                weatherCVEntity.setMaxTemperature(webResponse.getMain().getTemp_max());
                weatherCVEntity.setDescription(webResponse.getWeather().get(0).getDescription());


                weatherInsert(weatherCVEntity,context);

                iSearchWeatherByCityPresenter.setResultFromSearchByCity(weatherCVEntity);
            }

            @Override
            public void onFailure(Call<WebResponseForCity> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void weatherInsert(WeatherCVEntity weatherCVEntity,Context context){
        try{
            searchByCityQuerys = new SearchByCityQuerys(context);
            SQLiteDatabase sqLiteDatabase =  searchByCityQuerys.getWritableDatabase();

             // String sSqlCommand = "Create table if not exists Weather(img integer,day integer,city text,humidity double,minTemperature double ,maxTemperature double,description text)";
            String sSqlCommand = "Insert into Weather(img,day,city, humidity, minTemperature, maxTemperature, description)values("
                    +weatherCVEntity.getImg()+","+weatherCVEntity.getDay()+",'"+weatherCVEntity.getCity()+"',"+weatherCVEntity.getHumidity()
                    +","+weatherCVEntity.getMinTemperature()+","+weatherCVEntity.getMaxTemperature()+",'"+weatherCVEntity.getDescription()+"');";
            sqLiteDatabase.execSQL(sSqlCommand);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }




    @Override
    public void sendResultToPresenter(WeatherCVEntity weatherCVEntity) {

    }
}
