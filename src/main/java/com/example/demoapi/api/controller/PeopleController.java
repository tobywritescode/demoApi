package com.example.demoapi.api.controller;

import com.example.demoapi.exception.AgeCanNotBeLowerThanZero;
import com.example.demoapi.exception.UsersCanNotBeNullException;
import com.example.demoapi.model.people.Users;
import com.example.demoapi.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    UserService userService;

    @PostMapping("/getUsersOver/{age}")
    public ResponseEntity<Users> getUsersOver(@RequestBody Users users, @PathVariable Integer age) throws AgeCanNotBeLowerThanZero, UsersCanNotBeNullException {
        if(users == null || users.userList == null || users.userList.isEmpty()) {
            throw new UsersCanNotBeNullException("Users can not be null");
        }else if(age <= 0){
            throw new AgeCanNotBeLowerThanZero("Age can not be lower than zero.");
        }
        userService.getUsersOverAge(users, age);
        return ResponseEntity.ok(users);

    }

}
