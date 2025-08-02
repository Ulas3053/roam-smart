package com.jsp.roam_smart.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryRequest {
    private String mainPlace;
    private Long budget;
    private int days;
    private int people;
    private List<String> selectedPlaces;
}
