package com.jsp.roam_smart.service.custom_itinerary;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CustomItinerayService {
    public String getCustomItineray(String place, Long budget, int days, List<String> selectedPlaces);
}
