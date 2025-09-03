package com.electrolytej.network.api;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("/data/2.5/weather")
    Single<WeatherData> getCurrentWeatherDate(@Query("q") String city);

    /**
     * Call 5 day / 3 hour forecast data
     * By city name
     * api.openweathermap.org/data/2.5/forecast?q=London,us&mode=xml
     */
    @GET("/data/2.5/forecast")
    Observable<ListRes<WeatherData>> getFiveData(@Query("q") String city);


}
