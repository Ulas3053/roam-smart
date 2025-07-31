package com.jsp.roam_smart.dto;

import lombok.Data;

@Data
public class WeatherDTO {
    private String name;
    private double lon;
    private double lat;
    private int timezone;
    private String description;
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private long sunrise;
    private long sunset;
    
    private int pressure;
    private int humidity;

    private double windSpeed;
    private int windDeg;
    private double windGust;


}
