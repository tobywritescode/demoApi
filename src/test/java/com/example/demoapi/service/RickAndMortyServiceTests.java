package com.example.demoapi.service;

import com.example.demoapi.model.people.RickAndMortyCharacter;
import com.example.demoapi.model.repo.RickAndMortyCharactersRepository;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import wiremock.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Objects;

import static com.example.demoapi.helpers.Helpers.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WireMockTest(httpPort = 8081)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("wiremock")
public class RickAndMortyServiceTests {

    @Value("${rm.get.characters.test.url}")
    private String GET_CHARACTERS_TEST_URL;

    @Value("${rm.get.jerry.test.url}")
    private String GET_JERRY_TEST_URL;

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private RickAndMortyService rickAndMortyService  = new RickAndMortyService(restTemplate);;


    @Test
    void rickAndMortyServiceShouldCorrectlyReturnListOfCharactersFromApi() throws IOException {
        getStub("/getCharactersApiResponse.json", GET_CHARACTERS_TEST_URL, HttpURLConnection.HTTP_OK);
        RickAndMortyCharacter[] expected = getListOfRickAndMortyCharacters();
        Integer[] ints = {1, 2, 3, 4};
        RickAndMortyCharacter[] actual = rickAndMortyService.getCharacters(ints);
        assertEquals(expected.length, actual.length);
        assertEquals(expected[0].getName(), actual[0].getName());
    }

    private void getStub(String s, String url, int code) throws IOException {
        String HtmlAsString = IOUtils.toString(Objects.requireNonNull(this.getClass().getResourceAsStream(s)), "UTF-8");
        stubFor(any(urlPathEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(code)
                        .withHeader("Content-Type", "text/html")
                        .withBody(HtmlAsString)));
    }

}
