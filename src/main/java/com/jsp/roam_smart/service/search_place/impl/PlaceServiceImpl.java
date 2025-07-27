package com.jsp.roam_smart.service.search_place.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jsp.roam_smart.dto.LatLonDTO;
import com.jsp.roam_smart.service.search_place.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public LatLonDTO convertToCoordinates(String place) {
        String encodedPlace = URLEncoder.encode(place, StandardCharsets.UTF_8);     //------for removing space spacial ch etc.
        String url = "https://nominatim.openstreetmap.org/search?q=" + encodedPlace + "&format=json&limit=1";

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "RoamSmartTravelPlanner/1.0 (roamsmart@example.com)");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        List<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                List.class
        ).getBody();

        if (response == null || response.isEmpty()) {
            throw new IllegalArgumentException("No results found for: " + place + ". Please enter a valid location.");
        }

        Map<String, Object> responsePlace = response.get(0);
        LatLonDTO dto = new LatLonDTO();
        dto.setLat((String) responsePlace.get("lat"));
        dto.setLon((String) responsePlace.get("lon"));
        System.out.println("Coordinates for " + place + ": " + dto.getLat() + ", " + dto.getLon());
        return dto;
    }
}
