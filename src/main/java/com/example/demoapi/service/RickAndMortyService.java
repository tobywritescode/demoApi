package com.example.demoapi.service;


import com.example.demoapi.model.people.RickAndMortyCharacter;
import com.example.demoapi.model.people.RickAndMortyCharacters;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class RickAndMortyService implements ApiService {

    private final RestTemplate restTemplate;

    @Value("${rm.get.characters.url}")
    private String url;

    public RickAndMortyCharacter[] getCharacters(Integer[] ids) throws JsonProcessingException {

        String response = restTemplate.getForObject(url+Arrays.toString(ids), String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, RickAndMortyCharacter[].class);
    }
}
