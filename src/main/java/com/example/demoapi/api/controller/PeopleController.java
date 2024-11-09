package com.example.demoapi.api.controller;

import com.example.demoapi.model.people.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @PostMapping("/transformPeople/${command}")
    public ResponseEntity<List<User>> trasnformPeople(List<User> userList, String command){
        return null;
    }

}
