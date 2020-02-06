package com.example.myweatherapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myweatherapp.Entities.WeatherCVEntity;
import com.example.myweatherapp.Entities.WeatherList;
import com.example.myweatherapp.Presenter.WeatherPresenter;
import com.example.myweatherapp.R;
import com.example.myweatherapp.ServicesCall.IWeatherCall;
import com.example.myweatherapp.ServicesCall.RetrofitInstance;
import com.example.myweatherapp.View.Adapters.WeatherAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IWeatherView {
    WeatherPresenter weatherPresenter;
    RecyclerView recyclerViewWeather;
    WeatherAdapter adapter;
    String city;
    int count = 0;
    Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherPresenter = new WeatherPresenter(this);
        recyclerViewWeather = findViewById(R.id.RvWeather);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerViewWeather.setLayoutManager(llm);

        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);




        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localization local = new Localization();
        local.setMainActivity(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1337);
        }
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) local);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        myToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.searchByCity:
                        Intent intent = new Intent(MainActivity.this,SearchWeatherByCity.class);
                        startActivity(intent);
                        break;
                    case R.id.History:
                        Intent intentH = new Intent(MainActivity.this,History.class);
                        startActivity(intentH);
                        break;
                }

                return false;
            }
        });
        return true;
    }

    public void setLocation(Location location){
        if(location.getLatitude() != 0.0 && location.getLongitude() != 0.0){
            try{
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());

                List<Address> list = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                if(!list.isEmpty()){
                    Address dircalle =list.get(0);

                    if(city != null && (city.compareTo(dircalle.getLocality().toString()) != 0 || count == 0)){
                        if(networkStatus())
                        {
                            city = dircalle.getLocality();
                            GetWeatherList(city);
                            count++;
                        }else{
                            Toast.makeText(MainActivity.this, "No Network Conecttion", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(count == 0){
                        city = dircalle.getLocality();
                    }

                }
            }catch (Exception ex){

            }
        }
    }

    public boolean networkStatus(){
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    @Override
    public void GetWeatherList(String city) {
        weatherPresenter.GetWeatherList(city);
    }

    @Override
    public void ShowResult(ArrayList<WeatherCVEntity> arrayList) {
        adapter = new WeatherAdapter(this,arrayList);
        recyclerViewWeather.setAdapter(adapter);
    }



    public class Localization implements LocationListener {

        MainActivity mainActivity;

        public void setMainActivity(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        public MainActivity getMainActivity(){return mainActivity;}

        @Override
        public void onLocationChanged(Location location) {
            location.getLatitude();
            location.getLongitude();

            this.mainActivity.setLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(MainActivity.this,"GPS Enable",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(MainActivity.this,"GPS Disable",Toast.LENGTH_LONG).show();

        }
    }
}
