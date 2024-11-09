package com.example.demoapi.helpers;

import com.example.demoapi.model.people.User;
import com.example.demoapi.model.people.Users;
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
        List<User> users = new ArrayList<>();
        Faker faker = new Faker();
        IntStream.range(0, 25).forEach(i -> users.add(User.builder()
                .email(faker.name().username() + "@gmail.com")
                .age(25 + (int) (Math.random() * ((75 - 25) + 1)))
                .name(faker.name().fullName())
                .build()));
        return Users.builder()
                .userList(users).build();
    }

    public static Users getListSpecificOfUsers() {
        Faker faker = new Faker();
        return Users.builder()
                .userList(List.of(User.builder()
                                .email(faker.name().username() + "@gmail.com")
                                .age(25)
                                .name(faker.name().fullName())
                                .build(),
                        User.builder()
                                .email(faker.name().username() + "@gmail.com")
                                .age(35)
                                .name(faker.name().fullName())
                                .build(),
                        User.builder()
                                .email(faker.name().username() + "@gmail.com")
                                .age(36)
                                .name(faker.name().fullName())
                                .build(),
                        User.builder()
                                .email(faker.name().username() + "@gmail.com")
                                .age(25)
                                .name(faker.name().fullName())
                                .build())).build();
    }

    public static Users getListSpecificOfUsersOver30() {
        Faker faker = new Faker();
        return Users.builder()
                .userList(List.of(
                        User.builder()
                                .email(faker.name().username() + "@gmail.com")
                                .age(35)
                                .name(faker.name().fullName())
                                .build(),
                        User.builder()
                                .email(faker.name().username() + "@gmail.com")
                                .age(36)
                                .name(faker.name().fullName())
                                .build())).build();
    }

    public static User returnAUser() {
        return User.builder()
                .email("new@new.com")
                .age(55)
                .name("test man")
                .build();

    }
}
