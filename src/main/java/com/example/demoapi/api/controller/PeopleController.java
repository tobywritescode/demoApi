package com.example.demoapi.api.controller;

import com.example.demoapi.model.people.User;
import com.example.demoapi.model.people.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @PostMapping("/transformPeople/{command}")
    public ResponseEntity<Users> trasnformPeople(@RequestBody Users users, @PathVariable String command){
        String test = null;
        return ResponseEntity.ok(users);

    }

}
