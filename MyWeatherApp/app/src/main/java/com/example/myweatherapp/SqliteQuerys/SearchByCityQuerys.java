package com.example.myweatherapp.SqliteQuerys;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myweatherapp.Entities.WeatherCVEntity;

public class SearchByCityQuerys extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WeatherDB.db";

    public SearchByCityQuerys(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    String weatherTable = "Create table if not exists Weather(img integer,day integer,city text,humidity double,minTemperature double ,maxTemperature double,description text)";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(weatherTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
