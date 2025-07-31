package com.jsp.roam_smart.controller;

import com.jsp.roam_smart.dto.WeatherDTO;
import com.jsp.roam_smart.service.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherDTO> getWeather(@PathVariable String city) {
        return ResponseEntity.ok(weatherService.getWeather(city));
    }
}
