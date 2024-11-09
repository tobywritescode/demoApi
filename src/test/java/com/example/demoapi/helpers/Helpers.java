package com.example.demoapi.helpers;

import com.example.demoapi.model.people.User;
import com.example.demoapi.model.people.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Helpers {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Users getListOfUsers() {
        return Users.builder()
                .userList(List.of(
                        User.builder()
                                .email("new@new.com")
                                .id("1234")
                                .name("test man")
                                .build())).build();
    }

    public static User returnAUser(){
        return User.builder()
                .email("new@new.com")
                .id("1234")
                .name("test man")
                .build();

    }}
