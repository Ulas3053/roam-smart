package com.jsp.roam_smart.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.roam_smart.service.custom_itinerary.CustomItinerayService;
import com.jsp.roam_smart.service.search_place.PlaceAround;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/place-search")
@CrossOrigin(origins = "*")
public class PlaceSearchController {


    @Autowired
    private PlaceAround placeAPlaceAround;

    @Autowired
    private CustomItinerayService customItinerayService;
    
    @GetMapping("/explore")
    public ResponseEntity<Map<String, Object>> explorePlaces(@RequestParam String place) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("message", "List of places are displayed here");
        map.put("place", place);
        map.put("Places around :",placeAPlaceAround.getPlaceAround(place)); ;
        return ResponseEntity.status(200).body(map);
    }
    @GetMapping("/custom-itinerary")
    public String generateItinerary(@RequestParam String place,@RequestParam Long budget,@RequestParam int days) {
    return customItinerayService.getCustomItineray(place, budget, days);
}
    
}
