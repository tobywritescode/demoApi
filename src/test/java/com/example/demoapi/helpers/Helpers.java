package com.example.demoapi.helpers;

import com.example.demoapi.model.dto.episode.EpisodeDto;
import com.example.demoapi.model.entity.episode.Episode;
import com.example.demoapi.model.entity.location.Location;
import com.example.demoapi.model.entity.people.RickAndMortyCharacter;
import com.example.demoapi.model.entity.people.UserInfo;
import com.example.demoapi.model.entity.people.UserAgeGroups;
import com.example.demoapi.model.entity.people.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Helpers {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Users getListOfUsers() {
        List<UserInfo> userInfos = new ArrayList<>();
        Faker faker = new Faker();
        IntStream.range(0, 25).forEach(i -> userInfos.add(UserInfo.builder()
                .email(faker.name().username() + "@gmail.com")
                .age(25 + (int) (Math.random() * ((75 - 25) + 1)))
                .name(faker.name().fullName())
                .build()));
        return Users.builder()
                .userInfoList(userInfos).build();
    }

    public static Users getListSpecificOfUsers() {
        return Users.builder()
                .userInfoList(List.of(UserInfo.builder()
                                .email("test@gmail.com")
                                .age(25)
                                .name("fake name")
                                .build(),
                        UserInfo.builder()
                                .email("test@gmail.com")
                                .age(35)
                                .name("fake name")
                                .build(),
                        UserInfo.builder()
                                .email("test@gmail.com")
                                .age(36)
                                .name("fake name")
                                .build(),
                        UserInfo.builder()
                                .email("test@gmail.com")
                                .age(25)
                                .name("fake name")
                                .build(),
                        UserInfo.builder()
                                .email("test@gmail.com")
                                .age(15)
                                .name("fake name")
                                .build(),
                        UserInfo.builder()
                                .email("test@gmail.com")
                                .age(12)
                                .name("fake name")
                                .build())).build();
    }

    public static UserAgeGroups getUserAgeGroups() {
        UserAgeGroups userAgeGroups = new UserAgeGroups();
        userAgeGroups.setChildren(List.of(UserInfo.builder()
                        .email("test@gmail.com")
                        .age(15)
                        .name("fake name")
                        .build(),
                UserInfo.builder()
                        .email("test@gmail.com")
                        .age(12)
                        .name("fake name")
                        .build()));

        userAgeGroups.setYoungAdults(List.of(UserInfo.builder()
                        .email("test@gmail.com")
                        .age(25)
                        .name("fake name")
                        .build(),
                UserInfo.builder()
                        .email("test@gmail.com")
                        .age(25)
                        .name("fake name")
                        .build()));

        userAgeGroups.setAdults(List.of(UserInfo.builder()
                        .email("test@gmail.com")
                        .age(35)
                        .name("fake name")
                        .build(),
                UserInfo.builder()
                        .email("test@gmail.com")
                        .age(36)
                        .name("fake name")
                        .build()));

        return userAgeGroups;
    }

    public static Users getListSpecificOfUsersOver30() {
        return Users.builder()
                .userInfoList(List.of(
                        UserInfo.builder()
                                .email("test@gmail.com")
                                .age(35)
                                .name("fake name")
                                .build(),
                        UserInfo.builder()
                                .email("test@gmail.com")
                                .age(36)
                                .name("fake name")
                                .build())).build();
    }

    public static UserInfo returnAUser() {
        return UserInfo.builder()
                .email("new@new.com")
                .age(55)
                .name("test man")
                .build();

    }

    public static RickAndMortyCharacter returnRick(){
        return RickAndMortyCharacter.builder()
                .id(1L)
                .name("Rick Sanchez")
                .status("Alive")
                .species("Human")
                .type("")
                .gender("Male")
                .build();
    }

    public static RickAndMortyCharacter returnBrad(){
        return RickAndMortyCharacter.builder()
                .id(59L)
                .name("Brad Anderson")
                .status("Dead")
                .species("Human")
                .type("")
                .gender("Male")
                .origin(Location.builder().id(20L)
                        .name("Earth (Replacement Dimension)")
                        .url("https://rickandmortyapi.com/api/location/20").build())
                .location(Location.builder().id(20L)
                        .name("Earth (Replacement Dimension)")
                        .url("https://rickandmortyapi.com/api/location/20").build())
                .image("https://rickandmortyapi.com/api/character/avatar/59.jpeg")
                .episode(List.of(EpisodeDto.builder().id(7L).url("https://rickandmortyapi.com/api/episode/7").build()))
                .url("https://rickandmortyapi.com/api/character/59")
                .created("2017-11-05T11:41:38.964Z")
                .build();
    }


    public static RickAndMortyCharacter returnJerry(){
        return RickAndMortyCharacter.builder()
                .id(5L)
                .name("Jerry Smith")
                .status("Alive")
                .species("Human")
                .type("")
                .gender("Male")
                .build();
    }

    public static RickAndMortyCharacter[] getListOfRickAndMortyCharacters(){
        RickAndMortyCharacter rickSanchez = RickAndMortyCharacter.builder()
                .id(1L)
                .name("Rick Sanchez")
                .status("Alive")
                .species("Human")
                .type("")
                .gender("Male")
                .build();

        RickAndMortyCharacter mortySmith = RickAndMortyCharacter.builder()
                .id(2L)
                .name("Morty Smith")
                .status("Alive")
                .species("Human")
                .type("")
                .gender("Male")
                .build();

        RickAndMortyCharacter summerSmith = RickAndMortyCharacter.builder()
                .id(3L)
                .name("Summer Smith")
                .status("Alive")
                .species("Human")
                .type("")
                .gender("Female")
                .build();

        RickAndMortyCharacter bethSmith = RickAndMortyCharacter.builder()
                .id(4L)
                .name("Beth Smith")
                .status("Alive")
                .species("Human")
                .type("")
                .gender("Female")
                .build();

        return new RickAndMortyCharacter[]{rickSanchez,mortySmith, summerSmith, bethSmith};

    }
}
