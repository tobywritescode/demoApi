package com.example.demoapi.service;


import com.example.demoapi.model.people.RickAndMortyCharacter;
import com.example.demoapi.model.repo.RickAndMortyCharactersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RequiredArgsConstructor
@Service
public class RickAndMortyService implements ApiService {

    private final RestTemplate restTemplate;

    private final RickAndMortyCharactersRepository rickAndMortyCharactersRepository;

    @Value("${rm.get.characters.url}")
    private String url;

    public RickAndMortyCharacter[] getCharacters(Integer[] ids) throws JsonProcessingException {

        String idString = getIdsAsString(ids).toString();


        String response = restTemplate.getForObject(url+idString, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, RickAndMortyCharacter[].class);
    }

    private static StringBuilder getIdsAsString(Integer[] ids) {
        StringBuilder idString = new StringBuilder("");
        for (int i = 0; i < ids.length; i++) {
            idString.append(ids[i]);
            if (i != ids.length - 1) {
                idString.append(",");
            }
        }
        return idString;
    }

    @Cacheable(value = "characters")
    public List<RickAndMortyCharacter> getCharactersFromDb() {
        return rickAndMortyCharactersRepository.findAll();
    }
}
