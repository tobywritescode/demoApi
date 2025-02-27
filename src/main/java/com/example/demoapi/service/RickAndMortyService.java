package com.example.demoapi.service;


import com.example.demoapi.model.deserializers.CustomEpisodeDeserializer;
import com.example.demoapi.model.dto.episode.EpisodeDto;
import com.example.demoapi.model.entity.episode.Episode;
import com.example.demoapi.model.entity.location.Location;
import com.example.demoapi.model.entity.people.RickAndMortyCharacter;
import com.example.demoapi.model.repo.EpisodeRepository;
import com.example.demoapi.model.repo.LocationRepository;
import com.example.demoapi.model.repo.RickAndMortyCharactersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;


@RequiredArgsConstructor
@Service
public class RickAndMortyService implements ApiService {

    private final RestTemplate restTemplate;

    private final RickAndMortyCharactersRepository rickAndMortyCharactersRepository;

    private final LocationRepository locationRepository;

    private final EpisodeRepository episodeRepository;

    private final MessagingService telegramService;

    @Value("${rm.get.characters.url}")
    private String url;

    public RickAndMortyCharacter[] getCharacters(Integer[] ids) throws JsonProcessingException {

        String idString = getIdsAsString(ids).toString();
        String response = restTemplate.getForObject(url + idString, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(List.class, new CustomEpisodeDeserializer());
        objectMapper.registerModule(module);
        return objectMapper.readValue(response, RickAndMortyCharacter[].class);
    }

    private static StringBuilder getIdsAsString(Integer[] ids) {
        StringBuilder idString = new StringBuilder();
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

    public Map<String, List<String>> getLivingEarthDwellersFromDb() {
        telegramService.postAsyncTelegramMessage("Someone is looking for earth dwellers.");
        List<RickAndMortyCharacter> characters = rickAndMortyCharactersRepository.findAll();
        return characters.stream()
                .filter(character -> character.getGender().equalsIgnoreCase("male")
                        && character.getStatus().equalsIgnoreCase("alive")
                        && character.getLocation().getId().equals(1L))
                .collect(Collectors.groupingBy(r -> r.getSpecies().equalsIgnoreCase("Human") ? "Human" : "Alien", mapping(RickAndMortyCharacter::getName, toList())));

    }

    public Map<String, Integer> getCharactersAndNumberOfEpisodes() {
        List<RickAndMortyCharacter> characters = rickAndMortyCharactersRepository.findAll();
        return characters.stream().collect(Collectors.toMap(RickAndMortyCharacter::getName, c -> c.getEpisode().size() , (first, second) -> first));
    }

    public List<String> getStreamsForEpisodes() {
        List<RickAndMortyCharacter> characters = rickAndMortyCharactersRepository.findAll();
        return characters.stream().filter(c -> c.getEpisode().stream().anyMatch(episodeDto -> episodeDto.getId() == 3)).map(RickAndMortyCharacter::getName).collect(Collectors.toList());
    }

    public Map<String, Long> getGroupedGender(){
        List<RickAndMortyCharacter> characters = rickAndMortyCharactersRepository.findAll();
        return characters.stream().collect(Collectors.groupingBy(RickAndMortyCharacter::getGender, Collectors.counting()));
    }

    public Map<String, List<EpisodeDto>> getReaccuringCharacters(){
        List<RickAndMortyCharacter> characters = rickAndMortyCharactersRepository.findAll();
        return characters.stream().filter(c -> c.getEpisode().size() > 1).collect(Collectors.toMap(r -> r.getName()+r.getId(), RickAndMortyCharacter::getEpisode));
    }

//    public List<String> getDifferentLocations(){
//        List<RickAndMortyCharacter> characters = rickAndMortyCharactersRepository.findAll();
//    }

    public Map<String, Map<Integer, List<String>>> getAllRicksEpisodeCount(){
        List<RickAndMortyCharacter> characters = rickAndMortyCharactersRepository.findAll();
        return characters.stream()
                        .filter(c -> c.getName().toLowerCase().contains("rick"))
                        .collect(Collectors.toMap(
                                r -> r.getName()+r.getId(),
                                r -> r.getEpisode().stream().collect(Collectors.groupingBy(
                                        e -> r.getEpisode().size(),
                                        Collectors.mapping(EpisodeDto::getUrl, Collectors.toList()))
                                )));

    }

    public void getAndSaveLocations() {
        IntStream.rangeClosed(1,127).forEach(i -> {
            String response = restTemplate.getForObject("https://rickandmortyapi.com/api/location/"+i, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Location location = objectMapper.readValue(response, Location.class);
                locationRepository.save(location);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void getAndSaveCharacters() {
        IntStream.rangeClosed(1,826).forEach(i -> {
            String response = restTemplate.getForObject("https://rickandmortyapi.com/api/character/"+i, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(List.class, new CustomEpisodeDeserializer());
            objectMapper.registerModule(module);
            try {
                RickAndMortyCharacter rickAndMortyCharacter = objectMapper.readValue(response, RickAndMortyCharacter.class);

                rickAndMortyCharacter.getLocation().setId(getLocation(rickAndMortyCharacter.getLocation()));
                rickAndMortyCharacter.getOrigin().setId(getLocation(rickAndMortyCharacter.getOrigin()));
                rickAndMortyCharacter.getEpisode().forEach(episode -> {
                    Episode ep = episodeRepository.getEpisodeByUrl(episode.getUrl());
                    episode.setId(ep.getId());
                    episode.setEpisode(ep.getEpisode());
                    episode.setName(ep.getName());
                    episode.setCreated(ep.getCreated());
                    episode.setAir_date(ep.getAir_date());

                });
                rickAndMortyCharactersRepository.save(rickAndMortyCharacter);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Long getLocation(Location location) {
        Optional<Location> locationObj = Optional.ofNullable(location);
        Optional<Long> optionalUrl = locationObj
                .map(Location::getUrl)
                .flatMap(url -> Optional.ofNullable(locationRepository.getLocationByUrl(url)))
                .map(Location::getId);

        return optionalUrl.orElse(1L);
    }
}
