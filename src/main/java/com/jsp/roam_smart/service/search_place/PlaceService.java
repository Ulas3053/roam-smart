package com.jsp.roam_smart.service.search_place;

import org.springframework.stereotype.Service;

import com.jsp.roam_smart.dto.LatLonDTO;

@Service
public interface PlaceService {
LatLonDTO convertToCoordinates(String place);
    
}