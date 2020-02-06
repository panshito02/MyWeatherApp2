package com.example.myweatherapp.Presenter;

import android.content.Context;

import com.example.myweatherapp.Entities.WeatherCVEntity;
import com.example.myweatherapp.Model.HistoryModel;
import com.example.myweatherapp.View.IHistory;

import java.util.ArrayList;

public class HistoryPresenter implements IHistoryPresenter {


    IHistory iHistory;
    HistoryModel historyModel;
    public HistoryPresenter(IHistory iHistory){
        this.iHistory = iHistory;
        historyModel = new HistoryModel(this);
    }

    @Override
    public void searchHistoryWeather(Context context) {
        historyModel.searchHistoryWeather(context);
    }

    @Override
    public void sendResultToView(ArrayList<WeatherCVEntity> weatherCVEntityArrayList) {
        iHistory.showResultHistory(weatherCVEntityArrayList);
    }
}
