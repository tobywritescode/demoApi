package com.example.demoapi.service;

import com.example.demoapi.model.people.UserInfo;
import com.example.demoapi.model.people.UserAgeGroups;
import com.example.demoapi.model.people.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class UserService {

    public Users getUsersOverAge(Users users, Integer age) {
        users.userInfoList = users.userInfoList.stream()
                .filter(user -> user.getAge() > age)
                .toList();
        return users;
    }

    public UserAgeGroups putUsersIntoAgeGroups(Users users) {

        Map<String, List<UserInfo>> usersByAgeRange = users.userInfoList.stream()
                .collect(Collectors.groupingBy(user -> {
                    if (user.getAge() < 18) {
                        return "Child";
                    } else if (user.getAge() < 30) {
                        return "Young Adult";
                    } else {
                        return "Adult";
                    }
                }));

        UserAgeGroups userAgeGroups = new UserAgeGroups();
        userAgeGroups.setAdults(usersByAgeRange.getOrDefault("Adult", new ArrayList<>()));
        userAgeGroups.setYoungAdults(usersByAgeRange.getOrDefault("Young Adult", new ArrayList<>()));
        userAgeGroups.setChildren(usersByAgeRange.getOrDefault("Child", new ArrayList<>()));

        return userAgeGroups;
    }

}
