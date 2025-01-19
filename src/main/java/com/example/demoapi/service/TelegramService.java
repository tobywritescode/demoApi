package com.example.demoapi.service;

import com.example.demoapi.model.telegram.TelegramPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
@Slf4j
public class TelegramService {

    private final RestTemplate restTemplate;
    private final String uri = "https://api.telegram.org/bot7247049361:AAHdoOiXelivThrVz8GFBqJ6HZEuC9V1klA/sendMessage";


    public String postTelegramMessage(String message){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        TelegramPayload tp = TelegramPayload.builder().chat_id(1647081321).text(message).build();
        HttpEntity<TelegramPayload> httpEntity = new HttpEntity<>(tp, headers);

        return restTemplate.postForObject(uri, httpEntity, String.class);
    }

    @Async
    public void postAsyncTelegramMessage(String message){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        TelegramPayload tp = TelegramPayload.builder().chat_id(1647081321).text(message).build();
        HttpEntity<TelegramPayload> httpEntity = new HttpEntity<>(tp, headers);
        restTemplate.postForObject(uri, httpEntity, String.class);
        log.info("The telegram service has been called.");
    }

}
