package com.jsp.roam_smart.service.weather.impl;

import com.jsp.roam_smart.service.weather.WeatherService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Override
    public String getWeatherByCity(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + 
                     city + "&appid=" + apiKey + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
