package com.example.demoapi.service;

import com.example.demoapi.model.people.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.demoapi.helpers.Helpers.getListSpecificOfUsers;
import static com.example.demoapi.helpers.Helpers.getListSpecificOfUsersOver30;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class UserServiceTests {

    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }
    @Test
    void UserServiceGetUsersOverAgeShouldReturnCorrectUsers(){
        Users users = getListSpecificOfUsers();
        Users expectedUsers = getListSpecificOfUsersOver30();
        Users actualUsers = userService.getUsersOverAge(users, 30);
        assertThat(actualUsers.userList.size(), equalTo(expectedUsers.userList.size()));
    }
}
