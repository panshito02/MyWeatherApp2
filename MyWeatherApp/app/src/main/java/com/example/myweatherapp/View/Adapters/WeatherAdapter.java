package com.example.myweatherapp.View.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherapp.Entities.WeatherCVEntity;
import com.example.myweatherapp.R;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    Activity activity;
    ArrayList<WeatherCVEntity> weatherList;
    public WeatherAdapter(Activity activity, ArrayList<WeatherCVEntity> weatherList){
        this.activity = activity;
        this.weatherList = weatherList;
    }
    @NonNull
    @Override
    public WeatherAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_cardview,parent,false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.WeatherViewHolder holder, int position) {
        holder.imageView.setImageResource(weatherList.get(position).getImg());
        holder.day.setText(weatherList.get(position).getDay());
        holder.city.setText(weatherList.get(position).getCity());
        holder.humidity.setText(String.valueOf(weatherList.get(position).getHumidity()));
        holder.minTemperature.setText(String.valueOf(weatherList.get(position).getMinTemperature()));
        holder.maxTemperature.setText(String.valueOf(weatherList.get(position).getMaxTemperature()));
        holder.description.setText(weatherList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView day;
        private TextView city;
        private  TextView humidity;
        private TextView minTemperature;
        private TextView maxTemperature;
        private TextView description;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.TwDay);
            city = (TextView) itemView.findViewById(R.id.TwCity);
            humidity = (TextView) itemView.findViewById(R.id.TwHumidity);
            minTemperature = (TextView) itemView.findViewById(R.id.TwMinTemperature);
            maxTemperature = (TextView) itemView.findViewById(R.id.TwMaxTemperature);
            description = (TextView) itemView.findViewById(R.id.TwDescription);
            imageView = (ImageView) itemView.findViewById(R.id.ImgWeather);
        }
    }
}
