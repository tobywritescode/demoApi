package com.example.demoapi.service;

import com.example.demoapi.model.people.Users;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public Users getUsersOverAge(Users users, Integer age) {

        return users;
    }
}
