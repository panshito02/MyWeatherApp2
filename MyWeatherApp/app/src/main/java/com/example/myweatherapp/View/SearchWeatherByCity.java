package com.example.myweatherapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myweatherapp.Entities.WeatherCVEntity;
import com.example.myweatherapp.Presenter.ISearchWeatherByCityPresenter;
import com.example.myweatherapp.Presenter.SearchWeatherByCityPresenter;
import com.example.myweatherapp.Presenter.WeatherPresenter;
import com.example.myweatherapp.R;

public class SearchWeatherByCity extends AppCompatActivity implements ISearchWeatherByCity {
    EditText etCity;
    TextView twCity;
    TextView twHumidity;
    TextView twMinTemp;
    TextView twMaxTemp;
    TextView twDescriptiom;
    Button btnBuscar;
    LinearLayout linearLayout;
    SearchWeatherByCityPresenter searchWeatherByCityPresenter;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_weather_by_city);

        PrepareToolbar();

        twCity = findViewById(R.id.TwCitySearchManual);
        twHumidity = findViewById(R.id.TwHumiditySearchManual);
        twMinTemp = findViewById(R.id.TwMinTemperatureSearchManual);
        twMaxTemp = findViewById(R.id.TwMaxTemperatureSearchManual);
        twDescriptiom = findViewById(R.id.TwDescriptionSearchManual);
        linearLayout = findViewById(R.id.llWeatherManual);
        searchWeatherByCityPresenter = new SearchWeatherByCityPresenter(this);

        etCity = findViewById(R.id.EtCity);
        btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etCity.getText().toString().isEmpty()){
                    searchWeatherByCity(etCity.getText().toString());
                }
            }
        });
    }

    @Override
    public void searchWeatherByCity(String city) {
        if(networkStatus()){
            searchWeatherByCityPresenter.SearchWeatherByCity(city,this);
        }
        else{
            Toast.makeText(SearchWeatherByCity.this, "No Network Conecttion", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void showSearchRetult(WeatherCVEntity weatherCVEntity) {
        twCity.setText(weatherCVEntity.getCity());
        twHumidity.setText(String.valueOf(weatherCVEntity.getHumidity()));
        twMaxTemp.setText(String.valueOf(weatherCVEntity.getMaxTemperature()));
        twMinTemp.setText(String.valueOf(weatherCVEntity.getMinTemperature())+"");
        twDescriptiom.setText(weatherCVEntity.getDescription());
        linearLayout.setVisibility(View.VISIBLE);
    }


    private void PrepareToolbar() {
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle("Search City");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchWeatherByCity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean networkStatus(){
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}
