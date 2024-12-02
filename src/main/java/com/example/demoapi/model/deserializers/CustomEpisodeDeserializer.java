package com.example.demoapi.model.deserializers;

import com.example.demoapi.model.entity.episode.Episode;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomEpisodeDeserializer extends JsonDeserializer<List<Episode>> {

    @Override
    public List<Episode> deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = p.readValueAsTree();

        List<Episode> episodes = new ArrayList<>();
        for (JsonNode episodeNode : node) {
            episodes.add(Episode.builder().url(episodeNode.asText()).build());
        }
        return episodes;
    }
}
