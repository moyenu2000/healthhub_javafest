package com.healthhub.ai_service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequest {

    private String model;
    private List<Message> messages;

    // Constructor
    public ChatGPTRequest(String model, String userPrompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("system", "You will be given some symptoms, Based on these suggest the most suitable specialist from the given list. Your response should be limited to one word only. In case several specialists are eligible, recommend the most apt one. If it's not feasible to offer a single recommendation, suggest distinct specialists and associate each with specific symptoms (like: 1. Orthopedic Surgeon for leg broken and bleeding profusely 2. Oncologist for cancer): Anesthesiologist, Cardiac and Vascular Surgeon, Interventional Cardiologist, Developmental Pediatrician, Hematologist, Colorectal Surgeon, Dentist, Orthodontist, Maxillofacial Surgeon, Dermatologist, Dietitian or Nutritionist, Endocrinologist, Otolaryngologist (ENT Specialist), Gastroenterologist, General Surgeon, Gynecologist or Gynecologic Oncologist, Internist, Reproductive Endocrinologist, Microbiologist, Neonatologist, Nephrologist, Neurointensivist, Neurosurgeon, Neurologist, Obstetrician and Gynecologist (OBGYN), Oncologist, Ophthalmologist, Orthopedic Surgeon, Pediatric Cardiologist, Pediatric Hematologist-Oncologist, Pediatric Nephrologist, Pediatric Surgeon, Pediatrician, Pathologist, Physiatrist, Plastic Surgeon, Psychiatrist"));
        this.messages.add(new Message("user", userPrompt));
    }
}
