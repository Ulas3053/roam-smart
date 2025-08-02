package com.jsp.roam_smart.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.jsp.roam_smart.dto.NewsArticleDTO;

@Service
public class NewsService {

    private final RestTemplate restTemplate;

    @Value("${newsapi.key}")
    private String apiKey;

    public NewsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<NewsArticleDTO> getNewsForCity(String city) {
    String url = "https://gnews.io/api/v4/search?q="+ city +"&lang=en&country=in&max=10&token=" + apiKey;

    System.out.println("🔍 Final URL: " + url);
    System.out.println("🔑 API Key: " + apiKey);

    HttpHeaders headers = new HttpHeaders();
    headers.set("User-Agent", "Mozilla/5.0");
        headers.set("Accept", "application/json");
    HttpEntity<String> entity = new HttpEntity<>(headers);

    try {
        ResponseEntity<JsonNode> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                JsonNode.class
        );

        List<NewsArticleDTO> list = new ArrayList<>();
        JsonNode articles = response.getBody().get("articles");
        if (articles != null && articles.isArray()) {
            for (JsonNode node : articles) {
                NewsArticleDTO dto = new NewsArticleDTO();
                dto.setTitle(node.get("title").asText());
                dto.setDescription(node.get("description").asText(null));
                dto.setUrl(node.get("url").asText());
                dto.setPublishedAt(node.get("publishedAt").asText());
                list.add(dto);
            }
        }
        return list;

    } catch (Exception ex) {
        System.err.println("❌ API Error: " + ex.getMessage());
        return List.of(); 
    }
}

    }

