package com.jsp.roam_smart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.roam_smart.dto.NewsArticleDTO;
import com.jsp.roam_smart.service.NewsService;

@RestController
@RequestMapping("/api/updates")
public class CityUpdateController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/{city}")
    public ResponseEntity<List<NewsArticleDTO>> getCityUpdates(@PathVariable String city) {
        List<NewsArticleDTO> updates = newsService.getNewsForCity(city);
        return ResponseEntity.ok(updates);
    }
}

