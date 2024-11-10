package com.example.demoapi.controller;

import com.example.demoapi.model.people.Users;
import com.example.demoapi.model.people.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static com.example.demoapi.helpers.Helpers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PeopleControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUsersOverAnAgeShouldReturnUsersOverAnAgeAndReturnOk() throws Exception {
        String content = asJsonString(getListSpecificOfUsers());
        String expectedContent = asJsonString(getListSpecificOfUsersOver30());
        this.mockMvc.perform(post("/people/getUsersOver/30")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(expectedContent));
    }

    @Test
    void getUsersOverAnAgeShouldThrowExceptionIfNullAge() throws Exception {
        this.mockMvc.perform(post("/people/getUsersOver/-56")
                        .content(asJsonString(getListOfUsers()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Age can not be lower than zero."));
    }

    @Test
    void getUsersOverAnAgeShouldThrowExceptionIfNullUsers() throws Exception {
        this.mockMvc.perform(post("/people/getUsersOver/30")
                        .content(asJsonString(Users.builder().build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUsersInAgeGroupsShouldReturnUsersOverAnAgeAndReturnOk() throws Exception {
        String content = asJsonString(getListSpecificOfUsers());
        String expectedContent = asJsonString(getUserAgeGroups());
        this.mockMvc.perform(post("/people/getUsersInAgeGroups")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(expectedContent));
    }

    @Test
    void getUsersInAgeGroupsShouldThrowExceptionIfNullUsers() throws Exception {
        this.mockMvc.perform(post("/people/getUsersInAgeGroups")
                        .content(asJsonString(Users.builder().build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
