package com.healthhub.ai_service.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.healthhub.ai_service.dto.ChatGPTRequest;
import com.healthhub.ai_service.dto.ChatGptResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @PostMapping("/chat")
    public String chat(@RequestBody String prompt) {
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }

    @GetMapping("/diagnose")
    public ResponseEntity<String> diagnose(HttpServletRequest request) {
        Map<String, Object> claims = getClaims(request);
        String role = claims != null ? (String) claims.get("role") : "Unknown";

        String diagnosis = "Diagnosing based on symptoms for role: " + role;
        return ResponseEntity.ok(diagnosis);
    }

    @GetMapping("/specialist")
    public ResponseEntity<String> getSpecialist(@RequestParam String symptoms, HttpServletRequest request) {
        Map<String, Object> claims = getClaims(request);
        String role = claims != null ? (String) claims.get("role") : "Unknown";

        String specialist = "Recommending specialist based on symptoms: " + symptoms + " for role: " + role;
        return ResponseEntity.ok(specialist);
    }

    private Map<String, Object> getClaims(HttpServletRequest request) {
        Object claimsObj = request.getAttribute("claims");

        if (claimsObj instanceof Map) {
            return (Map<String, Object>) claimsObj;
        }
        return null;
    }
}
