package com.jsp.roam_smart.service.weather;

import com.jsp.roam_smart.dto.WeatherDTO;

public interface WeatherService {
    String getWeatherByCity(String city);      
    WeatherDTO getWeather(String city);
}