package com.example.demoapi.service;

import com.example.demoapi.model.people.UserAgeGroups;
import com.example.demoapi.model.people.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.demoapi.helpers.Helpers.*;
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
        assertThat(actualUsers.userList.get(0).age, equalTo(expectedUsers.userList.get(0).age));
        assertThat(actualUsers.userList.get(1).age, equalTo(expectedUsers.userList.get(1).age));
    }

    @Test
    void UsersServicePutUsersIntoAgeGroupsShouldReturnCorrectUsers(){
        Users users = getListSpecificOfUsers();
        UserAgeGroups expectedUserAgeGroups = getUserAgeGroups();
        UserAgeGroups userAgeGroups = userService.putUsersIntoAgeGroups(users);

        //Check size of the expected lists
        assertThat(userAgeGroups.getAdults().size(), equalTo(expectedUserAgeGroups.getAdults().size()));
        assertThat(userAgeGroups.getYoungAdults().size(), equalTo(expectedUserAgeGroups.getYoungAdults().size()));
        assertThat(userAgeGroups.getChildren().size(), equalTo(expectedUserAgeGroups.getChildren().size()));

        //Check random entry for correct data
        assertThat(userAgeGroups.getAdults().get(1).age, equalTo(expectedUserAgeGroups.getAdults().get(1).age));
        assertThat(userAgeGroups.getAdults().get(1).age, equalTo(expectedUserAgeGroups.getAdults().get(1).age));
        assertThat(userAgeGroups.getAdults().get(1).age, equalTo(expectedUserAgeGroups.getAdults().get(1).age));
    }
}
