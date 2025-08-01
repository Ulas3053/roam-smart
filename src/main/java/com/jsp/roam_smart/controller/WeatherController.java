package com.jsp.roam_smart.controller;

import com.jsp.roam_smart.service.weather.WeatherService;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getWeather(@PathVariable String city) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("message", "Weather details for the city");
        map.put("city", city);
        map.put("weather", weatherService.getWeatherByCity(city));
        return ResponseEntity.status(200).body(map);
    }
}
