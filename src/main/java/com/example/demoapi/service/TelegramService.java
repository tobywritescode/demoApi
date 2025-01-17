package com.example.demoapi.service;

import com.example.demoapi.model.telegram.TelegramPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class TelegramService {

    private final RestTemplate restTemplate;

    @Value("${telegram.bot.token}")
    private String botToken;


    public String postTelegramMessage(String message){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        TelegramPayload tp = TelegramPayload.builder().chat_id(1647081321).text(message).build();
        HttpEntity<TelegramPayload> httpEntity = new HttpEntity<>(tp, headers);

        String uri = "https://api.telegram.org/bot"+botToken+"/sendMessage";
        return restTemplate.postForObject(uri, httpEntity, String.class);
    }

}
