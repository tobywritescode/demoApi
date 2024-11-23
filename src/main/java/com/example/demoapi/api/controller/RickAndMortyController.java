package com.example.demoapi.api.controller;

import com.example.demoapi.exception.IdsCanNotBeEmptyException;
import com.example.demoapi.model.people.RickAndMortyCharacter;
import com.example.demoapi.model.repo.RickAndMortyCharactersRepository;
import com.example.demoapi.service.RickAndMortyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rickandmorty")
public class RickAndMortyController {

    private final RickAndMortyService rickAndMortyService;

    private final RickAndMortyCharactersRepository rickAndMortyCharactersRepository;

    @GetMapping("/getcharacters/{ids}")
    public ResponseEntity<RickAndMortyCharacter[]> getRickAndMortyCharacters(@PathVariable Integer[] ids) throws IdsCanNotBeEmptyException, JsonProcessingException {
        if(ids == null || ids.length == 0) {
            throw new IdsCanNotBeEmptyException("You must pass atleast one character Id");
        }
        return ResponseEntity.ok(rickAndMortyService.getCharacters(ids));
    }

    @GetMapping("/getallcharacters")
    public ResponseEntity<List<RickAndMortyCharacter>> getRickAndMortyCharactersFromDb() throws JsonProcessingException {

        List<RickAndMortyCharacter> characters = rickAndMortyService.getCharactersFromDb();

        return ResponseEntity.ok(characters);
    }

    @GetMapping("/getcharacterbyid/{id}")
    public ResponseEntity<RickAndMortyCharacter> getRickAndMortyCharacterFromDb(@PathVariable String id) throws IdsCanNotBeEmptyException {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IdsCanNotBeEmptyException("You must pass a character Id");
        }
        return rickAndMortyCharactersRepository
                .findById(Long.parseLong(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @GetMapping("/getlocations")
//    public ResponseEntity<String> getLocations(){
//        rickAndMortyService.getAndSaveLocations();
//        return ResponseEntity.ok("did it");
//    }

//    @GetMapping("/savecharacters")
//    public ResponseEntity<String> saveCharacters(){
//        rickAndMortyService.getAndSaveCharacters();
//        return ResponseEntity.ok("did it");
//    }
}
