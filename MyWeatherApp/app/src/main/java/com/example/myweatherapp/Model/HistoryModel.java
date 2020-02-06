package com.example.myweatherapp.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myweatherapp.Entities.WeatherCVEntity;
import com.example.myweatherapp.Presenter.IHistoryPresenter;
import com.example.myweatherapp.SqliteQuerys.SearchByCityQuerys;

import java.util.ArrayList;

public class HistoryModel implements IHistoryModel {
    IHistoryPresenter iHistoryPresenter;
    public HistoryModel(IHistoryPresenter iHistoryPresenter){
        this.iHistoryPresenter = iHistoryPresenter;
    }

    @Override
    public void searchHistoryWeather(Context context) {
        try{
            SearchByCityQuerys searchByCityQuerys = new SearchByCityQuerys(context);

            SQLiteDatabase sqLiteDatabase =  searchByCityQuerys.getWritableDatabase();

            ArrayList<WeatherCVEntity> weatherCVEntityArrayList = new ArrayList<>();

            Cursor c = sqLiteDatabase.rawQuery(" SELECT city,humidity,minTemperature,maxTemperature ,description FROM Weather ", null);

            if(c.moveToFirst()){
                do{ WeatherCVEntity weatherCVEntity = new WeatherCVEntity();
                   weatherCVEntity.setCity(c.getString(c.getColumnIndex("city")));
                   weatherCVEntity.setHumidity(c.getDouble(c.getColumnIndex("humidity")));
                   weatherCVEntity.setMinTemperature(c.getDouble(c.getColumnIndex("minTemperature")));
                   weatherCVEntity.setMaxTemperature(c.getDouble(c.getColumnIndex("maxTemperature")));
                   weatherCVEntity.setDescription(c.getString(c.getColumnIndex("description")));

                   weatherCVEntityArrayList.add(weatherCVEntity);
                }while (c.moveToNext());
                iHistoryPresenter.sendResultToView(weatherCVEntityArrayList);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
