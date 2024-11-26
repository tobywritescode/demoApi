package com.example.demoapi.api.controller;

import com.example.demoapi.exception.IdsCanNotBeEmptyException;
import com.example.demoapi.model.entity.people.RickAndMortyCharacter;
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
import java.util.Map;

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

    @GetMapping("/getcharacters")
    public ResponseEntity<List<RickAndMortyCharacter>> getCharacters() throws JsonProcessingException {
        return ResponseEntity.ok(rickAndMortyService.getCharactersFromDb());
    }

    @GetMapping("/getlivingearthdwellers")
    public ResponseEntity<Map<String, List<String>>> getLivingEarthDwellersFromDb(){
        return ResponseEntity.ok(rickAndMortyService.getLivingEarthDwellersFromDb());
    }

    @GetMapping("/getstreams/{id}")
    public ResponseEntity<Map<String, Integer>> getStreamsFromDb(@PathVariable Long id){
        return ResponseEntity.ok(rickAndMortyService.getStreamsFromDb(id));
    }

    @GetMapping("/getstuff")
    public ResponseEntity<List<String>> getStreamsFromDb(){
        return ResponseEntity.ok(rickAndMortyService.getStreamsForEpisodes());
    }

    @GetMapping("/getgendercount")
    public ResponseEntity<Map<String, Long>> getGenderCountFromDb(){
        return ResponseEntity.ok(rickAndMortyService.getGroupedGender());
    }

    @GetMapping("/getricks")
    public ResponseEntity<Map<String, Map<Integer, List<String>>>> getRicksFromDb(){
        return ResponseEntity.ok(rickAndMortyService.getAllRicksEpisodeCount());
    }

//    @GetMapping("/getlocations")
//    public ResponseEntity<String> getLocations(){
//        rickAndMortyService.getAndSaveLocations();
//        return ResponseEntity.ok("did it");
//    }
//
//    @GetMapping("/savecharacters")
//    public ResponseEntity<String> saveCharacters(){
//        rickAndMortyService.getAndSaveCharacters();
//        return ResponseEntity.ok("did it");
//    }
}
