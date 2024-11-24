package com.example.demoapi.controller;

import com.example.demoapi.api.config.SecurityConfiguration;
import com.example.demoapi.api.controller.AuthController;
import com.example.demoapi.api.controller.RickAndMortyController;
import com.example.demoapi.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static com.example.demoapi.helpers.Helpers.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import({SecurityConfiguration.class, TokenService.class})
public class RickAndMortyControllerTests {



    @Autowired
    private MockMvc mockMvc;

    private String token;

    @BeforeEach
    void setUp() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/token")
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andReturn();

        this.token = result.getResponse().getContentAsString();
    }

    @Test
    void getRickAndMortyCharactersShouldReturnOkWithValidRequest() throws Exception {
        this.mockMvc.perform(get("/rickandmorty/getcharacters/1,2,3,4")
                .header("Authorization", "Bearer " + token)).andExpect(status().isOk());
    }

    @Test
    void getRickAndMortyCharactersShouldReturn401WithInvalidRequest() throws Exception {
        this.mockMvc.perform(get("/rickandmorty/getcharacters/1,2,3,4")
                .header("Authorization", "Bearer ")).andExpect(status().isUnauthorized());
    }

    @Test
    void getRickAndMortyCharacterByIdShouldReturnOkWithValidResponse() throws Exception {
        this.mockMvc.perform(get("/rickandmorty/getcharacterbyid/59")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(returnBrad())));
    }

//    @Test
//    void getRickAndMortyCharactersShouldReturnOkWithValidResponse() throws Exception {
//        this.mockMvc.perform(get("/rickandmorty/getallcharacters")
//                .header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk())
//                .andExpect(content().string(asJsonString(Arrays.stream(getListOfRickAndMortyCharacters()).toList())));
//    }
}
