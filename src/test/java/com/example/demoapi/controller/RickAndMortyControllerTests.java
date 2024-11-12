package com.example.demoapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.example.demoapi.helpers.Helpers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RickAndMortyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRickAndMortyCharactersShouldReturnOkWithValidRequest() throws Exception {
        this.mockMvc.perform(get("/rickandmorty/getcharacters/1,2,3,4")).andExpect(status().isOk());
    }
    @Test
    void getRickAndMortyCharacterByIdShouldReturnOkWithValidResponse() throws Exception {
        this.mockMvc.perform(get("/rickandmorty/getcharacterbyid/1")).andExpect(status().isOk()).andExpect(content().string(asJsonString(returnRick())));
    }
    @Test
    void getRickAndMortyCharactersShouldReturnOkWithValidResponse() throws Exception {
        this.mockMvc.perform(get("/rickandmorty/getallcharacters")).andExpect(status().isOk()).andExpect(content().string(asJsonString(Arrays.stream(getListOfRickAndMortyCharacters()).toList())));
    }
}
