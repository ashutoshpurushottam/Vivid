package com.eigendaksh.vivid.network;


import com.eigendaksh.vivid.BuildConfig;
import com.eigendaksh.vivid.openweathermap.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public interface OpenWeatherService {

    String apiKey = BuildConfig.MY_API_KEY;
    @GET("forecast/daily?units=metric&cnt=15&apikey=" + apiKey)
    Observable<WeatherResponse> fetchWeatherForecasts(@Query("lon") double longitude, @Query("lat") double latitude);
}
