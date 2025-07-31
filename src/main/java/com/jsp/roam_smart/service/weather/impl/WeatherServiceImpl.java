package com.jsp.roam_smart.service.weather.impl;

import com.jsp.roam_smart.dto.WeatherDTO;
import com.jsp.roam_smart.service.weather.WeatherService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

     @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getWeatherByCity(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + 
                     city + "&appid=" + apiKey + "&units=metric";
        return restTemplate.getForObject(url, String.class);
    }
    @Override
    public WeatherDTO getWeather(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        Map body = response.getBody();

        WeatherDTO dto = new WeatherDTO();

        Map coord = (Map) body.get("coord");
        dto.setLon((Double) coord.get("lon"));
        dto.setLat((Double) coord.get("lat"));

        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) body.get("weather");
        if (!weatherList.isEmpty()) {
            dto.setDescription((String) weatherList.get(0).get("description"));
        }

        Map main = (Map) body.get("main");
        dto.setTemp(((Number) main.get("temp")).doubleValue());
        dto.setFeelsLike(((Number) main.get("feels_like")).doubleValue());
        dto.setTempMin(((Number) main.get("temp_min")).doubleValue());
        dto.setTempMax(((Number) main.get("temp_max")).doubleValue());
        dto.setPressure((Integer) main.get("pressure"));
        dto.setHumidity((Integer) main.get("humidity"));

        Map wind = (Map) body.get("wind");
        dto.setWindSpeed(((Number) wind.get("speed")).doubleValue());
        dto.setWindDeg((Integer) wind.get("deg"));
        dto.setWindGust(((Number) wind.get("gust")).doubleValue());

        Map sys = (Map) body.get("sys");
        dto.setSunrise(((Number) sys.get("sunrise")).longValue());
        dto.setSunset(((Number) sys.get("sunset")).longValue());

        dto.setTimezone((Integer) body.get("timezone"));
        dto.setName((String) body.get("name"));

        return dto;
    }

}
