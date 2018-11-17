package com.example.user.app1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.app1.model.ForecastResponse;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView temperature;
    private TextView summary;
    private TextView timezone;

    private LatLng BISHKEK = new LatLng(42.865767, 74.582923);
    private LatLng DUBAI = new LatLng(25.216780, 55.299680);
    //private final  String URL  = "https://api.darksky.net/forecast/936a1500f9616e99be1467cd405bfb19/";
    private final String URL = "https://api.darksky.net/forecast/a3cb505c260f798feb1cc30829c37fe2/";

    private Gson gson = new GsonBuilder().create();

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(URL)
            .build();

    private Link service = retrofit.create(Link.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // service = retrofit .create(Link.class);
        temperature = (TextView) findViewById(R.id.temperature);
        summary = (TextView) findViewById(R.id.summary);
        timezone = (TextView) findViewById(R.id.timezone);
        getWeather(BISHKEK);

    }

    private void getWeather(LatLng location) {
        service.forecast(location.latitude, location.longitude).enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                ForecastResponse forecast = response.body();
                if (forecast != null) {
                    temperature.setText(forecast.currently.temperature + "");
                    summary.setText(forecast.currently.summary);
                    timezone.setText(forecast.timezone);
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
