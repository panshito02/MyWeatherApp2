package com.example.myweatherapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myweatherapp.Entities.WeatherCVEntity;
import com.example.myweatherapp.Presenter.HistoryPresenter;
import com.example.myweatherapp.Presenter.IHistoryPresenter;
import com.example.myweatherapp.R;
import com.example.myweatherapp.View.Adapters.WeatherAdapter;

import java.util.ArrayList;

public class History extends AppCompatActivity implements IHistory{

    RecyclerView RwHistory;
    WeatherAdapter adapter;
    HistoryPresenter historyPresenter;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        PrepareToolbar();
        historyPresenter = new HistoryPresenter(this);

        RwHistory = findViewById(R.id.RvHistory);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        RwHistory.setLayoutManager(llm);

        searchHistoryWeather();

    }

    @Override
    public void searchHistoryWeather() {
        historyPresenter.searchHistoryWeather(this);
    }

    @Override
    public void showResultHistory(ArrayList<WeatherCVEntity> weatherCVEntityArrayList) {
        adapter = new WeatherAdapter(this,weatherCVEntityArrayList);
        RwHistory.setAdapter(adapter);
    }


    private void PrepareToolbar() {
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle("History");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
