package com.jsp.roam_smart.service.custom_itinerary.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.jsp.roam_smart.service.custom_itinerary.CustomItinerayService;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class CustomItinerayServiceImpl implements CustomItinerayService {

    @Autowired
    private RestTemplate restTemplate;

    public String getCustomItineray(String place, Long budget, int days) {
        String url = "https://openrouter.ai/api/v1/chat/completions";
        String apiKey = "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";

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        headers.set("User-Agent", "https://roamsmart.ai");
        headers.set("X-Title", "RoamSmart Itinerary Generator");

        // JSON request body
        String requestBody = """
        {
          "model": "mistralai/mixtral-8x7b-instruct",
          "messages": [
            {
              "role": "user",
              "content": "Plan a %d-day trip to %s under ₹%d.\\n\\nFormat the output clearly in plain text, like:\\n\\nday1:\\n- travel\\n- stay\\n- places to visit (with entry fee)\\n- breakfast, lunch, dinner (with cost)\\n\\nday2:\\n...\\n\\nday3:\\n...\\n\\nThen give:\\nTotal Estimated Cost: ₹xxxx\\n\\nDo not return JSON, markdown, or any code block. Just plain text. Keep it clear and readable also mention total cost at the last ."
            }
          ]
        }
        """.formatted(days, place, budget);

        // Entity
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // API call
        ResponseEntity<Map> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            Map.class
        );

        Map<String, Object> body = response.getBody();
        if (body == null || !body.containsKey("choices")) return "No response received.";

        List<Map<String, Object>> choices = (List<Map<String, Object>>) body.get("choices");
        if (choices.isEmpty()) return "No content received.";

        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
        String content = (String) message.get("content");

        return content; 
    }
}