package com.example.demoapi.service;

import com.example.demoapi.model.people.RickAndMortyCharacters;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ApiService {
    RickAndMortyCharacters getCharacters(Integer[] ids) throws JsonProcessingException;
}
