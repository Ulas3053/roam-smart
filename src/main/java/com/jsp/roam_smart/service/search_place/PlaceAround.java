package com.jsp.roam_smart.service.search_place;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.roam_smart.dto.PlaceAroundDetailsDTO;
@Service
public interface PlaceAround {
    List<PlaceAroundDetailsDTO> getPlaceAround(String place);
}
