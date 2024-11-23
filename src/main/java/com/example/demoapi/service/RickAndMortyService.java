package com.example.demoapi.service;


import com.example.demoapi.model.deserializers.CustomEpisodeDeserializer;
import com.example.demoapi.model.location.Location;
import com.example.demoapi.model.people.RickAndMortyCharacter;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;


@RequiredArgsConstructor
@Service
public class RickAndMortyService implements ApiService {

    private final RestTemplate restTemplate;

    private final RickAndMortyCharactersRepository rickAndMortyCharactersRepository;

//    private final LocationRepository locationRepository;
//
//    private final EpisodeRepository episodeRepository;

    @Value("${rm.get.characters.url}")
    private String url;

    public RickAndMortyCharacter[] getCharacters(Integer[] ids) throws JsonProcessingException {

        String idString = getIdsAsString(ids).toString();


        String response = restTemplate.getForObject(url+idString, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
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
    public List<RickAndMortyCharacter> getCharactersFromDb() throws JsonProcessingException {
        return rickAndMortyCharactersRepository.findAll();
    }

//    public void getAndSaveLocations() {
//        IntStream.rangeClosed(1,127).forEach(i -> {
//            String response = restTemplate.getForObject("https://rickandmortyapi.com/api/location/"+i, String.class);
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                Location location = objectMapper.readValue(response, Location.class);
//                locationRepository.save(location);
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }


//    public void getAndSaveCharacters() {
//        IntStream.rangeClosed(1,826).forEach(i -> {
//            String response = restTemplate.getForObject("https://rickandmortyapi.com/api/character/"+i, String.class);
//            ObjectMapper objectMapper = new ObjectMapper();
//            SimpleModule module = new SimpleModule();
//            module.addDeserializer(List.class, new CustomEpisodeDeserializer());
//            objectMapper.registerModule(module);
//            try {
//                RickAndMortyCharacter rickAndMortyCharacter = objectMapper.readValue(response, RickAndMortyCharacter.class);
//
//                rickAndMortyCharacter.getLocation().setId(getLocation(rickAndMortyCharacter.getLocation()));
//                rickAndMortyCharacter.getOrigin().setId(getLocation(rickAndMortyCharacter.getOrigin()));
//                rickAndMortyCharacter.getEpisode().forEach(episode -> episode.setId(episodeRepository.getEpisodeByUrl(episode.getUrl()).getId()));
//                rickAndMortyCharactersRepository.save(rickAndMortyCharacter);
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
//
//    private Long getLocation(Location location) {
//        Optional<Location> locationObj = Optional.ofNullable(location);
//        Optional<Long> optionalUrl = locationObj
//                .map(Location::getUrl)
//                .flatMap(url -> Optional.ofNullable(locationRepository.getLocationByUrl(url)))
//                .map(Location::getId);
//
//        return optionalUrl.orElse(1L);
//    }
}
