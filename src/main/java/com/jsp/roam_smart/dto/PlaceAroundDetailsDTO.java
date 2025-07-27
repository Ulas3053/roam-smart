package com.jsp.roam_smart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceAroundDetailsDTO {
    private String name;
    private String formattedAddress;
    private double lat;
    private double lon;
}
