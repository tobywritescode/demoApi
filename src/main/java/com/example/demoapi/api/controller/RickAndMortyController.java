package com.example.demoapi.api.controller;

import com.example.demoapi.exception.IdsCanNotBeEmptyException;
import com.example.demoapi.model.people.RickAndMortyCharacter;
import com.example.demoapi.service.RickAndMortyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rickandmorty")
public class RickAndMortyController {

    private final RickAndMortyService rickAndMortyService;

    @GetMapping("/getcharacters/{ids}")
    public ResponseEntity<RickAndMortyCharacter[]> getRickAndMortyCharacters(@PathVariable Integer[] ids) throws IdsCanNotBeEmptyException, JsonProcessingException {
        if(ids == null || ids.length == 0) {
            throw new IdsCanNotBeEmptyException("You must pass atleast one character Id");
        }
        return ResponseEntity.ok(rickAndMortyService.getCharacters(ids));
    }
}
