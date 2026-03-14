package com.epu.partimeconnect.service;

import com.epu.partimeconnect.config.TelegramProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramService {

    private final TelegramProperties telegramProperties;
    private final RestTemplate restTemplate = new RestTemplate();

    public void sendMessage(String message) {
        if (!telegramProperties.isEnabled()) {
            log.info("Telegram bot is disabled");
            return;
        }

        String token = telegramProperties.getToken();
        String chatId = telegramProperties.getChatId();

        if (token == null || token.isBlank() || chatId == null || chatId.isBlank()) {
            log.warn("Telegram config is missing");
            return;
        }

        String url = "https://api.telegram.org/bot" + token + "/sendMessage";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("chat_id", chatId);
        body.add("text", message);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        try {
            restTemplate.postForEntity(url, request, String.class);
            log.info("Telegram message sent successfully");
        } catch (Exception e) {
            log.error("Failed to send Telegram message: {}", e.getMessage(), e);
        }
    }
}