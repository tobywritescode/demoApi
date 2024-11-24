package com.example.demoapi.service;

import com.example.demoapi.model.entity.people.RickAndMortyCharacter;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ApiService {
    RickAndMortyCharacter[] getCharacters(Integer[] ids) throws JsonProcessingException;
}
